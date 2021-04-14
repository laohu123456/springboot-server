package com.server.utils;


import com.server.annotation.poi.ExcelPoi;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class POIUtils<T> {

    public void createExcel(List<T> list, String path, String sheetName, List<Field> orderList) throws IOException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();

        int listSize = list.size();
        if(listSize > 10000){
            int j = listSize / 10000;
            for (int i = 0; i < j; i++) {
                create(list, hssfWorkbook, i, sheetName, orderList);
            }
        }else{
            create(list, hssfWorkbook, 0, sheetName, orderList);
        }
        FileOutputStream out = new FileOutputStream(path);
        hssfWorkbook.write(out);
        out.close();
    }

    private void create(List<T> list, HSSFWorkbook hssfWorkbook, int index ,String sheetName, List<Field> orderList){
        HSSFSheet sheet = hssfWorkbook.createSheet();
        createHeader(sheet, orderList);
        createContent(sheet, list, index, orderList);
        hssfWorkbook.setSheetName(index,sheetName + index);
    }

    private void createHeader(HSSFSheet sheet, List<Field> orderList){
        HSSFRow row = sheet.createRow(0);
        int i = 0;
        for(Field field:orderList){
            row.createCell(i).setCellValue(field.getAnnotation(ExcelPoi.class).name());
            i++;
        }
    }

    private void createContent(HSSFSheet sheet, List<T> list,int index, List<Field> orderList){
        List<String> list1 = new LinkedList<>();
        for(Field field:orderList){
            list1.add(field.getName());
        }
        int j = 0;
        for (int i = index * 10000; i < (index + 1) * 10000; i++) {
            HSSFRow row = sheet.createRow(j+1);
            Field[] fields = list.get(i).getClass().getDeclaredFields();
            Object poi = list.get(i);
            for (int l = 0; l < fields.length; l++) {
                try {
                    if(!fields[l].isAccessible()){
                        fields[l].setAccessible(true);
                    }
                    int index1 = list1.indexOf(fields[l].getName());
                    if(index1 != -1){
                        row.createCell(index1).setCellValue(String.valueOf(fields[l].get(poi)));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            j++;
        }
    }


    private void hb(HSSFSheet sheet, int firstRow, int lastRow, int firstCol, int lastCol){
        //合并日期占两行(4个参数，分别为起始行，结束行，起始列，结束列)
        CellRangeAddress region = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
        sheet.addMergedRegion(region);
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<?> aClass = Class.forName("com.server.entity.User");
        Field a = aClass.getDeclaredField("userId");
        Object o = aClass.newInstance();
        System.out.println(a);
        Method setUserId = aClass.getDeclaredMethod("setUserId", String.class);
        setUserId.invoke(o, "skfasjfsklajkfld");
        Method getUserId = aClass.getDeclaredMethod("getUserId", null);
        Object invoke = getUserId.invoke(o, null);
        System.out.println(invoke);
    }


}
