package com.model;

import javafx.scene.chart.PieChart;

import java.util.Date;

/**
 * Created by NEUNB_Lisy on 2017/7/19.
 */
public class Email {
    private int e_id;
    private String title;
    private String content;
    private Date data;
    private int senderid;
    private int acceptorid;
    private int isread;

    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getSenderid() {
        return senderid;
    }

    public void setSenderid(int senderid) {
        this.senderid = senderid;
    }

    public int getAcceptorid() {
        return acceptorid;
    }

    public void setAcceptorid(int acceptorid) {
        this.acceptorid = acceptorid;
    }

    public int getIsread() {
        return isread;
    }

    public void setIsread(int isread) {
        this.isread = isread;
    }
}
