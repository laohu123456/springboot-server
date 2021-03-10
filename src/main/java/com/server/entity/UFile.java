package com.server.entity;

import java.io.Serializable;

public class UFile implements Serializable {

    private static final long serialVersionUID = 5557143148830192553L;
    private String id;
    private String fileName;
    private String realName;
    private String userId;
    private String createTime;
    private String userName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UFile{" +
                "id='" + id + '\'' +
                ", fileName='" + fileName + '\'' +
                ", realName='" + realName + '\'' +
                ", userId='" + userId + '\'' +
                ", createTime=" + createTime +
                ", userName='" + userName + '\'' +
                '}';
    }
}
