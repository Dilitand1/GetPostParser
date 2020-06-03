package ru.litvinov.getPostParser.FSSPparser.models.postRequest;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Params {
    private String firstname;
    private String lastname;
    private String secondname;
    private String region;
    private String birthdate;
    private String number;

    @JsonProperty("firstname")
    public String getFirstname() { return firstname; }
    @JsonProperty("firstname")
    public void setFirstname(String value) { this.firstname = value; }

    @JsonProperty("lastname")
    public String getLastname() { return lastname; }
    @JsonProperty("lastname")
    public void setLastname(String value) { this.lastname = value; }

    @JsonProperty("secondname")
    public String getSecondname() { return secondname; }
    @JsonProperty("secondname")
    public void setSecondname(String value) { this.secondname = value; }

    @JsonProperty("region")
    public String getRegion() { return region; }
    @JsonProperty("region")
    public void setRegion(String value) { this.region = value; }

    @JsonProperty("birthdate")
    public String getBirthdate() { return birthdate; }
    @JsonProperty("birthdate")
    public void setBirthdate(String value) { this.birthdate = value; }

    @JsonProperty("number")
    public String getNumber() { return number; }
    @JsonProperty("number")
    public void setNumber(String value) { this.number = value; }

    @Override
    public String toString() {
        return  (firstname == null ? "" : firstname) + "~" +
                (lastname == null ? "" : lastname) + "~" +
                (secondname == null ? "" : secondname) + "~" +
                (region == null ? "" : region) + "~" +
                (birthdate == null ? "" : birthdate) + "~" +
                (number == null ? "" : number);
    }
}
