package com.liuzg.jswebextra.plugins;


import com.liuzg.jswebextra.plugins.excel.ExcelTemplate;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzg on 2017/5/5.
 */
public class ExcelPlugin {

    public boolean exportExcelTemplateToFile(Map<String,Object> datacontext,String templatefilepath,String outfilepath){
        try{
            FileInputStream fin = new FileInputStream(templatefilepath);
            XSSFWorkbook workbook = new XSSFWorkbook(fin);
            fin.close();
            ExcelTemplate template = new ExcelTemplate(workbook);
            template.setContext(datacontext);
            template.process();

            FileOutputStream fout = new FileOutputStream(outfilepath);
            workbook.write(fout);
            fout.flush();
            fout.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean exportExcelToResponse(HttpServletResponse response, Map<String,Object> datacontext, String templatefilepath,String outfilename){

        try{
            if(outfilename==null||"".equals(outfilename)) outfilename="export.xlsx";
            response.addHeader("Content-Disposition", "attachment; filename=\"" + new String(outfilename.getBytes("UTF-8"),"iso8859-1") + "\"");
            response.setContentType("application/octet-stream");

            FileInputStream fin = new FileInputStream(templatefilepath);
            XSSFWorkbook workbook = new XSSFWorkbook(fin);
            fin.close();
            ExcelTemplate template = new ExcelTemplate(workbook);
            template.setContext(datacontext);
            template.process();

            ServletOutputStream rout = response.getOutputStream();
            workbook.write(rout);
            rout.flush();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public List<Object> readRows(String excelfilepath,int sheetindex,int colnum, int start,int end){
        try{
            List<Object> result = new ArrayList<>();
            XSSFWorkbook workbook = new XSSFWorkbook(excelfilepath);
            XSSFSheet sheet = workbook.getSheetAt(sheetindex);
            for(int i=start;i<=end&&i<=sheet.getLastRowNum();i++){
                XSSFRow row = sheet.getRow(i);
                XSSFCell cell = row.getCell(colnum);
                String value = cell.toString();
                result.add(value);
            }
            workbook.close();
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Object> readColumns(String excelfilepath,int sheetindex,int rownumber,int start,int end) {
        try{
            List<Object> result = new ArrayList<>();
            XSSFWorkbook workbook = new XSSFWorkbook(excelfilepath);
            XSSFSheet sheet = workbook.getSheetAt(sheetindex);
            XSSFRow row = sheet.getRow(rownumber);
            for(int i=start;i<=end&&i<=row.getLastCellNum();i++){
                XSSFCell cell = row.getCell(i);
                String value = cell.getRawValue();
                result.add(value);
            }
            workbook.close();
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
