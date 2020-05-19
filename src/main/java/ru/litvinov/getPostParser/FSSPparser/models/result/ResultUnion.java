package ru.litvinov.getPostParser.FSSPparser.models.result;

import java.util.*;
import java.io.IOException;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.*;

@JsonDeserialize(using = ResultUnion.Deserializer.class)
@JsonSerialize(using = ResultUnion.Serializer.class)
public class ResultUnion {
    private ResultResult[] successResult;
    private ErrorResult errorResult;

    static class Deserializer extends JsonDeserializer<ResultUnion> {
        @Override
        public ResultUnion deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            ResultUnion value = new ResultUnion();
            switch (jsonParser.getCurrentToken()) {
                case START_ARRAY:
                    value.successResult = jsonParser.readValueAs(ResultResult[].class);
                    break;
                case START_OBJECT:
                    value.errorResult = jsonParser.readValueAs(ErrorResult.class);
                    break;
                default: throw new IOException("Cannot deserialize ResultUnion");
            }
            return value;
        }
    }

    static class Serializer extends JsonSerializer<ResultUnion> {
        @Override
        public void serialize(ResultUnion obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (obj.successResult != null) {
                jsonGenerator.writeObject(obj.successResult);
                return;
            }
            if (obj.errorResult != null) {
                jsonGenerator.writeObject(obj.errorResult);
                return;
            }
            throw new IOException("ResultUnion must not be null");
        }
    }

    public ResultResult[] getSuccessResult() {
        return successResult;
    }

    public ErrorResult getErrorResult() {
        return errorResult;
    }
}