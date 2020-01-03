package com.example.test;

public class Module {

    private String title;
    private Integer id;
    private int subject;

    public Module(String name, int subject, Integer id) {

        this.title = name;
        this.subject = subject;
        this.id = id;

    }

    public String getTitle() {
        return title;
    }
    public int getId() {
        return id;
    }
    public int getSubject() { return subject; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }
}
