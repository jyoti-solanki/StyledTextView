package com.js.styledtextview.demo;

public class StickerInfo {
    private int COST;
    private String IMAGE_PATH;
    private String IMAGE_SERVER_PATH;
    private String IS_DOWNLOADED;
    private boolean IS_DOWNLOADING = false;
    private String IS_HOT;
    private String IS_UPDATED = "false";
    private String MAIN_CATEGORY;
    private int SEQUENCE;
    private long STICKER_ID;
    private String STICKER_NAME;
    private String SUB_CATEGORY;
    private String THUMB_PATH;
    private String THUMB_SERVER_PATH;

    public long getSTICKER_ID() {
        return this.STICKER_ID;
    }

    public void setSTICKER_ID(long STICKER_ID2) {
        this.STICKER_ID = STICKER_ID2;
    }

    public String getSTICKER_NAME() {
        return this.STICKER_NAME;
    }

    public void setSTICKER_NAME(String STICKER_NAME2) {
        this.STICKER_NAME = STICKER_NAME2;
    }

    public String getMAIN_CATEGORY() {
        return this.MAIN_CATEGORY;
    }

    public void setMAIN_CATEGORY(String MAIN_CATEGORY2) {
        this.MAIN_CATEGORY = MAIN_CATEGORY2;
    }

    public String getSUB_CATEGORY() {
        return this.SUB_CATEGORY;
    }

    public void setSUB_CATEGORY(String SUB_CATEGORY2) {
        this.SUB_CATEGORY = SUB_CATEGORY2;
    }

    public String IS_HOT() {
        return this.IS_HOT;
    }

    public void setIS_HOT(String IS_HOT2) {
        this.IS_HOT = IS_HOT2;
    }

    public int getCOST() {
        return this.COST;
    }

    public void setCOST(int COST2) {
        this.COST = COST2;
    }

    public String getTHUMB_PATH() {
        return this.THUMB_PATH;
    }

    public void setTHUMB_PATH(String THUMB_PATH2) {
        this.THUMB_PATH = THUMB_PATH2;
    }

    public String getIMAGE_PATH() {
        return this.IMAGE_PATH;
    }

    public void setIMAGE_PATH(String IMAGE_PATH2) {
        this.IMAGE_PATH = IMAGE_PATH2;
    }

    public String IS_DOWNLOADED() {
        return this.IS_DOWNLOADED;
    }

    public void setIS_DOWNLOADED(String IS_DOWNLOADED2) {
        this.IS_DOWNLOADED = IS_DOWNLOADED2;
    }

    public int getSEQUENCE() {
        return this.SEQUENCE;
    }

    public void setSEQUENCE(int SEQUENCE2) {
        this.SEQUENCE = SEQUENCE2;
    }

    public String getTHUMB_SERVER_PATH() {
        return this.THUMB_SERVER_PATH;
    }

    public void setTHUMB_SERVER_PATH(String THUMB_SERVER_PATH2) {
        this.THUMB_SERVER_PATH = THUMB_SERVER_PATH2;
    }

    public String getIMAGE_SERVER_PATH() {
        return this.IMAGE_SERVER_PATH;
    }

    public void setIMAGE_SERVER_PATH(String IMAGE_SERVER_PATH2) {
        this.IMAGE_SERVER_PATH = IMAGE_SERVER_PATH2;
    }

    public boolean isIS_DOWNLOADING() {
        return this.IS_DOWNLOADING;
    }

    public void setIS_DOWNLOADING(boolean IS_DOWNLOADING2) {
        this.IS_DOWNLOADING = IS_DOWNLOADING2;
    }

    public String getIS_UPDATED() {
        return this.IS_UPDATED;
    }

    public void setIS_UPDATED(String IS_UPDATED2) {
        this.IS_UPDATED = IS_UPDATED2;
    }
}
