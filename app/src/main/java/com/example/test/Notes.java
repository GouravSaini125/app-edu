package com.example.test;

import retrofit2.http.Url;

public class Notes {

    private String doc;
    private Integer id;
    private int module;

    public Notes(String doc, Integer id, int module) {
        this.doc = doc;
        this.id = id;
        this.module = module;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getModule() {
        return module;
    }

    public void setModule(int module) {
        this.module = module;
    }
}
