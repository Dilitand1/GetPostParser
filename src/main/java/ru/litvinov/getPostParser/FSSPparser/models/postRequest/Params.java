package ru.litvinov.getPostParser.FSSPparser.models.postRequest;


public abstract class Params {

    private String firstname;
    private String lastname;
    private String secondname;
    private String region;
    private String birthdate;
    private String name;
    private String address;
    private String number;

    public String getFirstname() { return firstname; }
    public void setFirstname(String value) { this.firstname = value; }

    public String getLastname() { return lastname; }
    public void setLastname(String value) { this.lastname = value; }

    public String getSecondname() { return secondname; }
    public void setSecondname(String value) { this.secondname = value; }

    public String getRegion() { return region; }
    public void setRegion(String value) { this.region = value; }

    public String getBirthdate() { return birthdate; }
    public void setBirthdate(String value) { this.birthdate = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getAddress() { return address; }
    public void setAddress(String value) { this.address = value; }

    public String getNumber() { return number; }
    public void setNumber(String value) { this.number = value; }
}
