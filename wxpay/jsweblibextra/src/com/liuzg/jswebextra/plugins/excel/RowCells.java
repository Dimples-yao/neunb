package com.liuzg.jswebextra.plugins.excel;

import org.apache.poi.ss.usermodel.Row;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzg on 2016/2/20.
 */
public class RowCells {

    int rowindex;
    Row row;
    List<CellDataSource> cells = new ArrayList<>();

    public RowCells() {
    }

    public int getRowindex() {
        return rowindex;
    }

    public void setRowindex(int rowindex) {
        this.rowindex = rowindex;
    }

    public Row getRow() {
        return row;
    }

    public void setRow(Row row) {
        this.row = row;
    }

    public List<CellDataSource> getCells() {
        return cells;
    }

    public void setCells(List<CellDataSource> cells) {
        this.cells = cells;
    }
}
