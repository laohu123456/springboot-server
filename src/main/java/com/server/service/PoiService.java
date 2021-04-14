package com.server.service;


import com.server.entity.Poi;

import java.io.IOException;
import java.util.List;

public interface PoiService {


    public void writeExcel() throws IOException;

    public void readExecl();

    public List<Poi> producer(Integer offestNum);

}
