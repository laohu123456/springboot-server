package com.server.config;

public class AllException extends Exception{

    private Integer code;

    private String message;

    public AllException(){}

    public AllException(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AllException{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
