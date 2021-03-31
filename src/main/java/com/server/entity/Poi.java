package com.server.entity;

import com.server.annotation.poi.ExcelPoi;

import java.io.Serializable;

public class Poi implements Serializable {

    @ExcelPoi(name = "编号", require = true, order = 0, value = "id")
    private String id;

    @ExcelPoi(name = "用户名", require = true, order = 1, value = "name")
    private String name;

    @ExcelPoi(name = "密码", require = true, order = 3, value = "password")
    private String password;

    @ExcelPoi(name = "备注", require = true, order = 2, value = "remark")
    private String remark;

    @ExcelPoi(name = "备注a", require = false, order = 5, value = "remarka")
    private String remarka;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Poi{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
