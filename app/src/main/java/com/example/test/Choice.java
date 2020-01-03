package com.example.test;

public class Choice {

    private String a;
    private String b;
    private String c;
    private String d;
    private String answer;
    private int question;
    private Integer id;

    public Choice(String a,String b,String c,String d, String answer, int question, Integer id){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.answer = answer;
        this.question = question;
        this.id = id;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }

    public void setA(String a) {
        this.a = a;
    }

    public void setB(String b) {
        this.b = b;
    }

    public void setC(String c) {
        this.c = c;
    }

    public void setD(String d) {
        this.d = d;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getD() {
        return d;
    }

    public String getAnswer() {
        return answer;
    }

}
