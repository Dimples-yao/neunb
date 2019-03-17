package com.liuzg.jswebextra.plugins.excel;

import org.apache.poi.ss.usermodel.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzg on 2016/2/20.
 */
public class CellDataSource {

    Cell cell;
    int colindex;
    List<Object> dataSource = new ArrayList<>();

    public CellDataSource() {
    }

    public CellDataSource(Cell cell, int colindex, List<Object> dataSource) {
        this.cell = cell;
        this.colindex = colindex;
        this.dataSource = dataSource;
    }

    public int getColindex() {
        return colindex;
    }

    public void setColindex(int colindex) {
        this.colindex = colindex;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public List<Object> getDataSource() {
        return dataSource;
    }

    public void setDataSource(List<Object> dataSource) {
        this.dataSource = dataSource;
    }
}
