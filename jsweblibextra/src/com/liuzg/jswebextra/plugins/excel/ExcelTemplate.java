package com.liuzg.jswebextra.plugins.excel;


import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.jxpath.JXPathContext;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by liuzg on 2016/2/20.
 */
public class ExcelTemplate {

    Workbook workbook;
    Map<String,Object> context = new HashMap<>();

    public ExcelTemplate(Workbook workbook) {
        this.workbook = workbook;
    }

    public ExcelTemplate(InputStream fin) {
        try {
            this.workbook = new XSSFWorkbook(fin);
        } catch (IOException e) {
        }
    }

    public void setContext(Map<String, Object> context) {
        this.context = context;
    }

    public void process(){
        Iterator<Sheet> sheetit = workbook.sheetIterator();
        while(sheetit.hasNext()){
            String sheetname = sheetit.next().getSheetName();
            Sheet sheet = workbook.getSheet(sheetname);

            updateVar(sheet,context);
            updateArray(sheet,context);

            sheet.setForceFormulaRecalculation(true);

        }
    }

    public void updateVar(Sheet sheet,Map<String,Object> context){
        Iterator<Row> rowit = sheet.rowIterator();
        while(rowit.hasNext()){
            int rowindex = rowit.next().getRowNum();
            Row row = sheet.getRow(rowindex);
            Iterator<Cell> cellit = row.cellIterator();
            while(cellit.hasNext()){
                int colindex= cellit.next().getColumnIndex();
                Cell cell = row.getCell(colindex);
                String cellcontent = cell.toString();
                if(isVarBindingExpression(cellcontent)){
                    Object value = evaluateVarExpression(cellcontent, context);
                    setCellValue(cell,value);
                }
            }
        }
    }

    public void updateArray(Sheet sheet,Map<String,Object> context){
        List<RowCells> list= new ArrayList<>();
        Iterator<Row> rowit = sheet.rowIterator();
        while(rowit.hasNext()){
            int rowindex = rowit.next().getRowNum();
            Row row = sheet.getRow(rowindex);
            RowCells rowCells = new RowCells();
            rowCells.setRow(row);
            rowCells.setRowindex(rowindex);
            Iterator<Cell> cellit = row.cellIterator();
            while(cellit.hasNext()){
                int colindex = cellit.next().getColumnIndex();
                Cell cell = row.getCell(colindex);
                String cellcontent = cell.toString();
                if(isArrayBindingExpression(cellcontent)){
                    List<Object> ds = evaluateArrayExpression(cellcontent, context);
                    rowCells.getCells().add(new CellDataSource(cell,colindex,ds));
                }
            }
            if(rowCells.getCells().size()>0) list.add(rowCells);
        }

        for (RowCells rowCells:list) {
            int count = dscount(rowCells);
            int rowindex = rowCells.getRow().getRowNum();
            if(rowindex+1<=sheet.getLastRowNum())
                sheet.shiftRows(rowindex+1,sheet.getLastRowNum(),count-1);
            for(int i=0;i<count;i++){
                Row row =null;
                if(i>0) row = cloneRowFormat(sheet,rowindex+i,rowCells.getRow());
                else row = rowCells.getRow();
                for (CellDataSource cellDataSource: rowCells.getCells()){
                    int colindex = cellDataSource.getColindex();
                    try{
                        Object value = null;
                        if(i<cellDataSource.getDataSource().size()){
                            value = cellDataSource.getDataSource().get(i);
                        }else{
                            value = "";
                        }

                        Cell c = null;
                        if(row.getCell(colindex)==null) c = row.createCell(colindex);
                        else c = row.getCell(colindex);
                        setCellValue(c,value);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private int dscount(RowCells rowCells) {
        int count =0 ;
        for (CellDataSource ds :rowCells.getCells()){
            if(ds.getDataSource().size()>count){
                count = ds.getDataSource().size();
            }
        }
        return count;
    }

    private Object evaluateVarExpression(String exp, Map<String, Object> context){
        try{
            exp = exp.substring(3,exp.length()-1);
            return JXPathContext.newContext(context).getValue(exp);
        }catch (Exception e){
            return null;
        }
    }

    private List<Object> evaluateArrayExpression(String exp, Map<String, Object> context){
        List<Object> list = new ArrayList<>();

        try{
            exp = exp.substring(3,exp.length()-1);
            JXPathContext ctx = JXPathContext.newContext(context);
            ctx.setLenient(true);
            Iterator it = ctx.iterate(exp);
            list = IteratorUtils.toList(it);
        }catch (Exception e){
        }

        return list;
    }

    private boolean isArrayBindingExpression(String exp){
        return exp.startsWith("&=[");
    }

    private boolean isVarBindingExpression(String exp){
        return exp.startsWith("&=(");
    }

    private void setCellValue(Cell cell, Object value) {
        if(value==null) {
            cell.setCellValue("");
            return;
        }
        try{
            if(value instanceof Integer|
                    value instanceof Double|
                    value instanceof Long|
                    value instanceof Short|
                    value instanceof Float){
                cell.setCellValue(Double.parseDouble(value.toString()));
            }else if(value instanceof String){
                cell.setCellValue((String) value);
            }else if(value instanceof Date){
                cell.setCellValue((Date) value);
            }else if(value instanceof Boolean){
                cell.setCellValue((Boolean) value);
            }else{
                cell.setCellValue(value.toString());
            }
        }catch (Exception e){
        }
    }

    private Row cloneRowFormat(Sheet sheet,int rowindex,Row sourceRow){
        Row row = sheet.createRow(rowindex);
        Iterator<Cell> it = sourceRow.cellIterator();
        while (it.hasNext()){
            Cell origioncell = it.next();
            Cell newcell = row.createCell(origioncell.getColumnIndex());
            newcell.setCellStyle(origioncell.getCellStyle());
        }
        return row;
    }


}
