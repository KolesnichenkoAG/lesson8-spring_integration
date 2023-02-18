package ru.geekbrains.integration.subscriber;

public class Subscriber {

    private Long id;
    private String name;

    private String interest;

    public Subscriber(Long id, String name, String interest) {
        this.id = id;
        this.name = name;
        this.interest = interest;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String toString() {
        return id + " " + name + " " + interest;
    }

    public void startMessage() {
        System.out.println("set_topic php");
    }
}
