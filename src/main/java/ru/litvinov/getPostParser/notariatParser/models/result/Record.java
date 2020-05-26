package ru.litvinov.getPostParser.notariatParser.models.result;


import java.util.*;
import com.fasterxml.jackson.annotation.*;

public class Record {
    private String fio;
    private String birthDate;
    private String deathDate;
    private String deathActDate;
    private String deathActNumber;
    private String address;
    private String caseIndex;
    private String caseNumber;
    private String caseDate;
    private String caseCloseDate;
    private String notaryID;
    private String notaryName;
    private String notaryStatus;
    private String districtName;
    private String contactName;
    private String contactAddress;
    private String contactPhone;
    private String chamberID;
    private String chamberName;
    private String caseID;
    private String caseIDDate;

    @JsonProperty("Fio")
    public String getFio() { return fio; }
    @JsonProperty("Fio")
    public void setFio(String value) { this.fio = value; }

    @JsonProperty("BirthDate")
    public String getBirthDate() { return birthDate; }
    @JsonProperty("BirthDate")
    public void setBirthDate(String value) { this.birthDate = value; }

    @JsonProperty("DeathDate")
    public String getDeathDate() { return deathDate; }
    @JsonProperty("DeathDate")
    public void setDeathDate(String value) { this.deathDate = value; }

    @JsonProperty("DeathActDate")
    public String getDeathActDate() { return deathActDate; }
    @JsonProperty("DeathActDate")
    public void setDeathActDate(String value) { this.deathActDate = value; }

    @JsonProperty("DeathActNumber")
    public String getDeathActNumber() { return deathActNumber; }
    @JsonProperty("DeathActNumber")
    public void setDeathActNumber(String value) { this.deathActNumber = value; }

    @JsonProperty("Address")
    public String getAddress() { return address; }
    @JsonProperty("Address")
    public void setAddress(String value) { this.address = value; }

    @JsonProperty("CaseIndex")
    public String getCaseIndex() { return caseIndex; }
    @JsonProperty("CaseIndex")
    public void setCaseIndex(String value) { this.caseIndex = value; }

    @JsonProperty("CaseNumber")
    public String getCaseNumber() { return caseNumber; }
    @JsonProperty("CaseNumber")
    public void setCaseNumber(String value) { this.caseNumber = value; }

    @JsonProperty("CaseDate")
    public String getCaseDate() { return caseDate; }
    @JsonProperty("CaseDate")
    public void setCaseDate(String value) { this.caseDate = value; }

    @JsonProperty("CaseCloseDate")
    public String getCaseCloseDate() { return caseCloseDate; }
    @JsonProperty("CaseCloseDate")
    public void setCaseCloseDate(String value) { this.caseCloseDate = value; }

    @JsonProperty("NotaryId")
    public String getNotaryID() { return notaryID; }
    @JsonProperty("NotaryId")
    public void setNotaryID(String value) { this.notaryID = value; }

    @JsonProperty("NotaryName")
    public String getNotaryName() { return notaryName; }
    @JsonProperty("NotaryName")
    public void setNotaryName(String value) { this.notaryName = value; }

    @JsonProperty("NotaryStatus")
    public String getNotaryStatus() { return notaryStatus; }
    @JsonProperty("NotaryStatus")
    public void setNotaryStatus(String value) { this.notaryStatus = value; }

    @JsonProperty("DistrictName")
    public String getDistrictName() { return districtName; }
    @JsonProperty("DistrictName")
    public void setDistrictName(String value) { this.districtName = value; }

    @JsonProperty("ContactName")
    public String getContactName() { return contactName; }
    @JsonProperty("ContactName")
    public void setContactName(String value) { this.contactName = value; }

    @JsonProperty("ContactAddress")
    public String getContactAddress() { return contactAddress; }
    @JsonProperty("ContactAddress")
    public void setContactAddress(String value) { this.contactAddress = value; }

    @JsonProperty("ContactPhone")
    public String getContactPhone() { return contactPhone; }
    @JsonProperty("ContactPhone")
    public void setContactPhone(String value) { this.contactPhone = value; }

    @JsonProperty("ChamberId")
    public String getChamberID() { return chamberID; }
    @JsonProperty("ChamberId")
    public void setChamberID(String value) { this.chamberID = value; }

    @JsonProperty("ChamberName")
    public String getChamberName() { return chamberName; }
    @JsonProperty("ChamberName")
    public void setChamberName(String value) { this.chamberName = value; }

    @JsonProperty("CaseId")
    public String getCaseID() { return caseID; }
    @JsonProperty("CaseId")
    public void setCaseID(String value) { this.caseID = value; }

    @JsonProperty("CaseIDDate")
    public String getCaseIDDate() { return caseIDDate; }
    @JsonProperty("CaseIDDate")
    public void setCaseIDDate(String value) { this.caseIDDate = value; }
}