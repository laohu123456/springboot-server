package com.server.entity;

import java.io.Serializable;

public class ExceptionEntity implements Serializable {


    private static final long serialVersionUID = -1359185672528481262L;

    private Integer status;
    private String message;
    private Integer marker;

    public ExceptionEntity() {
    }

    public ExceptionEntity(Integer status, String message, Integer marker) {
        this.status = status;
        this.message = message;
        this.marker = marker;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getMarker() {
        return marker;
    }

    public void setMarker(Integer marker) {
        this.marker = marker;
    }

    @Override
    public String toString() {
        return "ExceptionEntity{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", marker=" + marker +
                '}';
    }
}
