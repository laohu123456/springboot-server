package com.sjms.gczms;

import java.util.Observable;

public class Question  {

    private String providerName;
    private String providerQuestion;

    public Question() {
    }

    public Question(String providerName, String providerQuestion) {
        this.providerName = providerName;
        this.providerQuestion = providerQuestion;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderQuestion() {
        return providerQuestion;
    }

    public void setProviderQuestion(String providerQuestion) {
        this.providerQuestion = providerQuestion;
    }

    @Override
    public String toString() {
        return "Question{" +
                "providerName='" + providerName + '\'' +
                ", providerQuestion='" + providerQuestion + '\'' +
                '}';
    }
}
