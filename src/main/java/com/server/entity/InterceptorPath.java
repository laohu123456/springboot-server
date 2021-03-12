package com.server.entity;

import java.io.Serializable;

public class InterceptorPath implements Serializable {

    private static final long serialVersionUID = -3037253043837221242L;
    private int id;
    private String path;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Interceptor{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", status=" + status +
                '}';
    }
}
