package com.server.utils;


import com.server.entity.Poi;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class POIUtils {

    public void createExcel(String[] header, List<Poi> list, String path, String sheetName) throws IOException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();

        int listSize = list.size();
        if(listSize > 10000){
            int j = listSize / 10000;
            for (int i = 0; i < j; i++) {
                create(header, list, hssfWorkbook, i, sheetName);
            }
        }else{
            create(header, list, hssfWorkbook, 0, sheetName);
        }
        FileOutputStream out = new FileOutputStream(path);
        hssfWorkbook.write(out);
        out.close();
    }

    private void create(String[] header, List<Poi> list, HSSFWorkbook hssfWorkbook, int index ,String sheetName){
        HSSFSheet sheet = hssfWorkbook.createSheet();
        createHeader(sheet, header);
        createContent(sheet, list, header, index);
        hssfWorkbook.setSheetName(index,sheetName + index);
    }

    private void createHeader(HSSFSheet sheet, String[] header){
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < header.length; i++) {
            row.createCell(i).setCellValue(header[i]);
        }
    }

    private void createContent(HSSFSheet sheet, List<Poi> list, String[] header ,int index){
        int j = 0;
        for (int i = index * 10000; i < (index + 1) * 10000; i++) {
            HSSFRow row = sheet.createRow(j+1);
            row.createCell(0).setCellValue(list.get(i).getId());
            row.createCell(1).setCellValue(list.get(i).getName());
            row.createCell(2).setCellValue(list.get(i).getPassword());
            row.createCell(3).setCellValue(list.get(i).getRemark());
            j++;
        }
    }



}
