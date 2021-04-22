package com.server.entity;

import java.io.Serializable;

public class EmailUser implements Serializable {


    private static final long serialVersionUID = 2391877209005716557L;
    private Integer id;
    private String  emailName;
    private String  emailPasswd;

    public EmailUser() {
    }

    public EmailUser(String emailName, String emailPasswd) {
        this.emailName = emailName;
        this.emailPasswd = emailPasswd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmailName() {
        return emailName;
    }

    public void setEmailName(String emailName) {
        this.emailName = emailName;
    }

    public String getEmailPasswd() {
        return emailPasswd;
    }

    public void setEmailPasswd(String emailPasswd) {
        this.emailPasswd = emailPasswd;
    }

    @Override
    public String toString() {
        return "EmailUser{" +
                "id=" + id +
                ", emailName='" + emailName + '\'' +
                ", emailPasswd='" + emailPasswd + '\'' +
                '}';
    }


}
