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
import java.util.stream.Collectors;


@Service
public class PoiServcieImpl implements PoiService {


    @Autowired
    private PoiMapper poiMapper;

    @Override
    public void writeExcel() throws IOException {
        long start = System.currentTimeMillis();
        String path = "D:\\POI";
        String fileName = "first.xls";
        String sheetName = System.currentTimeMillis() + "AAA";
        List<Field> orderList = order("com.server.entity.Poi");
        List<Poi> all = poiMapper.findAll();
        POIUtils<Poi> poiUtils = new POIUtils<Poi>();
        poiUtils.createExcel(all, path + "\\" + fileName, sheetName, orderList);
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

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list.stream()
                .filter(field -> field.isAnnotationPresent(ExcelPoi.class))  //增加对未标注注解的属性进行排除,以前版本未标注的容易出现空指针异常
                .filter(field -> field.getAnnotation(ExcelPoi.class).require())
                .sorted(new Comparator<Field>() {
                    @Override
                    public int compare(Field o1, Field o2) {
                        return o1.getAnnotation(ExcelPoi.class).order() - o2.getAnnotation(ExcelPoi.class).order();
                    }
                })
                //.sorted((o1, o2) -> o1.getAnnotation(ExcelPoi.class).order() - o2.getAnnotation(ExcelPoi.class).order())
                .collect(Collectors.toList());
    }


    @Override
    public List<Poi> producer(Integer offestNum) {
        return poiMapper.producer(offestNum);
    }



}
