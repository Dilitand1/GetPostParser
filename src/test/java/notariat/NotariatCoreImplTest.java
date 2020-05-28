package notariat;

import org.junit.*;

import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import ru.litvinov.getPostParser.notariatParser.core.CoreImpl;
import ru.litvinov.getPostParser.notariatParser.models.Client;
import ru.litvinov.getPostParser.notariatParser.models.result.GetResult;
import ru.litvinov.getPostParser.utils.jsonUtils.JsonUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class NotariatCoreImplTest {

    private Logger logger;

    CoreImpl core;

    @Before
    public void init(){
        logger = Mockito.mock(Logger.class);
        core = new CoreImpl();
        core.setLogger(logger);
        core.setInputFile("testNotariatInput.txt");
        core.setOutputFile("testNotariatOutput.txt");
    }

    @Test
    public void loadClients() throws IOException {
        doNothing().when(logger).log(anyObject(),anyString());
        String testClientString = "ИВАНОВ ИВАН ИВАНОВИЧ;30.06.1959;null";
        Files.write(Paths.get("testNotariatInput.txt"),testClientString.getBytes());
        core.loadClients();

        Assert.assertEquals(core.getClients().get(0).getName(),"ИВАНОВ ИВАН ИВАНОВИЧ");
        verify(logger,atLeastOnce());

        Files.deleteIfExists(Paths.get("testNotariatInput.txt"));
    }

    @Test
    public void resultProcessor(){
        doNothing().when(logger).log(anyObject(),anyString());
        String expectedString = "ИВАНОВ ИВАН ИВАНОВИЧ~30.06.1959~NULL~1~2019-03-15~17019911003900227000~Краснодарский край, Мостовский район, ст-ца Андрюки~1428~142/2012~2039-02-12~NULL~01600127~Д С Григорьевна~1~Мостовский~Д С Григорьевна~352574,пос.Мостовской\"А\"~86192~016~Нотариальная палата Краснодарского края~643673~2019-03-21 10:44:01\n";

        Client client = new Client();
        client.setName("ИВАНОВ ИВАН ИВАНОВИЧ");
        client.setBirth_date("19590630");
        client.setDeath_date("null");
        String responseJson = "{\"count\":1,\"records\":[{\"Fio\":\"ИВАНОВ ИВАН ИВАНОВИЧ\",\"BirthDate\":\"19590630\",\"DeathDate\":\"20190304\",\"DeathActDate\":\"2019-03-15\",\"DeathActNumber\":\"17019911003900227000\",\"Address\":\"Краснодарский край, Мостовский район, ст-ца Андрюки\",\"CaseIndex\":\"1428\",\"CaseNumber\":\"142\\/2012\",\"CaseDate\":\"2039-02-12\",\"CaseCloseDate\":\"NULL\",\"NotaryId\":\"01600127\",\"NotaryName\":\"Д С Григорьевна\",\"NotaryStatus\":\"1\",\"DistrictName\":\"Мостовский\",\"ContactName\":\"Д С Григорьевна\",\"ContactAddress\":\"352574,пос.Мостовской\\\"А\\\"\",\"ContactPhone\":\"86192\",\"ChamberId\":\"016\",\"ChamberName\":\"Нотариальная палата Краснодарского края\",\"CaseId\":\"643673\",\"CaseIDDate\":\"2019-03-21 10:44:01\"}]}";
        GetResult result = (GetResult) JsonUtils.jsonToObject(responseJson,GetResult.class);
        String realString = core.resultProcessor(client,result);
        Assert.assertEquals("Тестим обработчик результата",expectedString,realString);
    }

    @Test
    @Ignore
    public void getResponseTest(){
        String input = "ДОБРЫНИНА ТАТЬЯНА ВИКТОРОВНА;30.06.1959;null";
    }
}
