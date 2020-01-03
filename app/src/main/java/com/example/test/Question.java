package com.example.test;

public class Question {

    private String title;
    private Integer id;
    private int topic;

    public Question(String title, Integer id, int topic) {
        this.title = title;
        this.id = id;
        this.topic = topic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTopic() {
        return topic;
    }

    public void setTopic(int topic) {
        this.topic = topic;
    }
}
