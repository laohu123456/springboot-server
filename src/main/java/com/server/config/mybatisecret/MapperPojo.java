package com.server.config.mybatisecret;

import lombok.Data;

@Data
@NeedSecret
public class MapperPojo {


    private Integer id;

    @NeedMapperSecret
    private String name;

    @NeedMapperSecret
    private String remark;

    private String address;



}
