package ru.litvinov.getPostParser.notariatParser.models;

public class Client {
    private String name;
    private String birth_date;
    private String death_date;

    public Client() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getDeath_date() {
        return death_date;
    }

    public void setDeath_date(String death_date) {
        this.death_date = death_date;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", birth_date='" + birth_date + '\'' +
                ", death_date='" + death_date + '\'' +
                '}';
    }
}
