package com.example.test;

public class Topic {
    private String title;
    private Integer id;
    private int module;

    public Topic(String name, int module, Integer id) {

        this.title = name;
        this.module = module;
        this.id = id;

    }

    public String getTitle() {
        return title;
    }
    public int getId() {
        return id;
    }
    public int getModule() { return module; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setModule(int module) {
        this.module = module;
    }
}
