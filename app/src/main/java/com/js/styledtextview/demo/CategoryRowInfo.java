package com.js.styledtextview.demo;

public class CategoryRowInfo {
    private int CATEGORY_ID = 0;
    private String CATEGORY_NAME = "";
    private int SEQUENCE = 0;
    private int TOTAL_ITEMS = 0;

    public CategoryRowInfo(String CATEGORY_NAME2, int SEQUENCE2, int TOTAL_ITEMS2) {
        this.CATEGORY_NAME = CATEGORY_NAME2;
        this.SEQUENCE = SEQUENCE2;
        this.TOTAL_ITEMS = TOTAL_ITEMS2;
    }

    public int getCATEGORY_ID() {
        return this.CATEGORY_ID;
    }

    public void setCATEGORY_ID(int CATEGORY_ID2) {
        this.CATEGORY_ID = CATEGORY_ID2;
    }

    public String getCATEGORY_NAME() {
        return this.CATEGORY_NAME;
    }

    public void setCATEGORY_NAME(String CATEGORY_NAME2) {
        this.CATEGORY_NAME = CATEGORY_NAME2;
    }

    public int getSEQUENCE() {
        return this.SEQUENCE;
    }

    public void setSEQUENCE(int SEQUENCE2) {
        this.SEQUENCE = SEQUENCE2;
    }

    public int getTOTAL_ITEMS() {
        return this.TOTAL_ITEMS;
    }

    public void setTOTAL_ITEMS(int TOTAL_ITEMS2) {
        this.TOTAL_ITEMS = TOTAL_ITEMS2;
    }
}
