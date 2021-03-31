package com.server.service.imp;

import com.server.annotation.poi.ExcelPoi;
import com.server.dao.PoiMapper;
import com.server.entity.Poi;
import com.server.service.PoiService;
import com.server.utils.POIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


@Service
public class PoiServcieImpl implements PoiService {


    @Autowired
    private PoiMapper poiMapper;

    @Override
    public void writeExcel() throws IOException {
        long start = System.currentTimeMillis();
        String path = "D:\\POI";
        String fileName = "first.xls";
        String sheetName = "AAA";
        List<Field> orderList = order("com.server.entity.Poi");
        String[] header = new String[orderList.size()];
        int i = 0;
        for(Field field:orderList){
            header[i] = field.getAnnotation(ExcelPoi.class).name();
            i++;
        }
        System.out.println(Arrays.toString(header));
        List<Poi> all = poiMapper.findAll();
        POIUtils poiUtils = new POIUtils();
        poiUtils.createExcel(header, all, path + "\\" + fileName, sheetName);
        long end = System.currentTimeMillis();
        System.out.println("耗时:" + (end - start) /1000);
    }

    @Override
    public void readExecl() {

    }

    public List<Field> order(String pojoPath){
        List<Field> list = new ArrayList<>();
        try {
            Class<?> aClass = Class.forName(pojoPath);
            Field[] fields = aClass.getDeclaredFields();
            list = Arrays.asList(fields);
            list.stream().filter(field -> field.getAnnotation(ExcelPoi.class).require() == true)
                    .sorted(new Comparator<Field>() {
                @Override
                public int compare(Field o1, Field o2) {
                    return o1.getAnnotation(ExcelPoi.class).order() - o2.getAnnotation(ExcelPoi.class).order();
                }
            });
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}
