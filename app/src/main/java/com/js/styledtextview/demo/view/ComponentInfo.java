package com.js.styledtextview.demo.view;

import android.graphics.Bitmap;
import android.net.Uri;

public class ComponentInfo {
    private Bitmap BITMAP;
    private String COLORTYPE;
    private int COMP_ID;
    private int HEIGHT;
    private int ORDER;
    private float POS_X;
    private float POS_Y;
    private String RES_ID;
    private Uri RES_URI;
    private float ROTATION;
    private int STC_COLOR;
    private int STC_OPACITY;
    int ScaleProg;
    private int TEMPLATE_ID;
    private String TYPE = "";
    private int WIDTH;
    int XRotateProg;
    int YRotateProg;
    private float Y_ROTATION;
    int ZRotateProg;

    public ComponentInfo() {
    }

    public ComponentInfo(int TEMPLATE_ID2, float POS_X2, float POS_Y2, int WIDTH2, int HEIGHT2, float ROTATION2, float Y_ROTATION2, String RES_ID2, Uri res_uri, Bitmap BITMAP2, String TYPE2, int ORDER2, int STC_COLOR2, String COLORTYPE2, int STC_OPACITY2, int XRotateProg2, int YRotateProg2, int ZRotateProg2, int ScaleProg2) {
        this.TEMPLATE_ID = TEMPLATE_ID2;
        this.POS_X = POS_X2;
        this.POS_Y = POS_Y2;
        this.WIDTH = WIDTH2;
        this.HEIGHT = HEIGHT2;
        this.ROTATION = ROTATION2;
        this.Y_ROTATION = Y_ROTATION2;
        this.RES_ID = RES_ID2;
        this.RES_URI = res_uri;
        this.BITMAP = BITMAP2;
        this.TYPE = TYPE2;
        this.ORDER = ORDER2;
        this.STC_COLOR = STC_COLOR2;
        this.COLORTYPE = COLORTYPE2;
        this.STC_OPACITY = STC_OPACITY2;
        this.XRotateProg = XRotateProg2;
        this.YRotateProg = YRotateProg2;
        this.ZRotateProg = ZRotateProg2;
        this.ScaleProg = ScaleProg2;
    }

    public int getCOMP_ID() {
        return this.COMP_ID;
    }

    public void setCOMP_ID(int COMP_ID2) {
        this.COMP_ID = COMP_ID2;
    }

    public int getTEMPLATE_ID() {
        return this.TEMPLATE_ID;
    }

    public void setTEMPLATE_ID(int TEMPLATE_ID2) {
        this.TEMPLATE_ID = TEMPLATE_ID2;
    }

    public float getPOS_X() {
        return this.POS_X;
    }

    public void setPOS_X(float POS_X2) {
        this.POS_X = POS_X2;
    }

    public float getPOS_Y() {
        return this.POS_Y;
    }

    public void setPOS_Y(float POS_Y2) {
        this.POS_Y = POS_Y2;
    }

    public String getRES_ID() {
        return this.RES_ID;
    }

    public void setRES_ID(String RES_ID2) {
        this.RES_ID = RES_ID2;
    }

    public String getTYPE() {
        return this.TYPE;
    }

    public void setTYPE(String TYPE2) {
        this.TYPE = TYPE2;
    }

    public int getORDER() {
        return this.ORDER;
    }

    public void setORDER(int ORDER2) {
        this.ORDER = ORDER2;
    }

    public float getROTATION() {
        return this.ROTATION;
    }

    public void setROTATION(float ROTATION2) {
        this.ROTATION = ROTATION2;
    }

    public int getWIDTH() {
        return this.WIDTH;
    }

    public void setWIDTH(int WIDTH2) {
        this.WIDTH = WIDTH2;
    }

    public int getHEIGHT() {
        return this.HEIGHT;
    }

    public void setHEIGHT(int HEIGHT2) {
        this.HEIGHT = HEIGHT2;
    }

    public float getY_ROTATION() {
        return this.Y_ROTATION;
    }

    public void setY_ROTATION(float y_ROTATION) {
        this.Y_ROTATION = y_ROTATION;
    }

    public Uri getRES_URI() {
        return this.RES_URI;
    }

    public void setRES_URI(Uri RES_URI2) {
        this.RES_URI = RES_URI2;
    }

    public Bitmap getBITMAP() {
        return this.BITMAP;
    }

    public void setBITMAP(Bitmap BITMAP2) {
        this.BITMAP = BITMAP2;
    }

    public int getSTC_COLOR() {
        return this.STC_COLOR;
    }

    public void setSTC_COLOR(int STC_COLOR2) {
        this.STC_COLOR = STC_COLOR2;
    }

    public String getCOLORTYPE() {
        return this.COLORTYPE;
    }

    public void setCOLORTYPE(String COLORTYPE2) {
        this.COLORTYPE = COLORTYPE2;
    }

    public int getSTC_OPACITY() {
        return this.STC_OPACITY;
    }

    public void setSTC_OPACITY(int STC_OPACITY2) {
        this.STC_OPACITY = STC_OPACITY2;
    }

    public int getXRotateProg() {
        return this.XRotateProg;
    }

    public void setXRotateProg(int XRotateProg2) {
        this.XRotateProg = XRotateProg2;
    }

    public int getYRotateProg() {
        return this.YRotateProg;
    }

    public void setYRotateProg(int YRotateProg2) {
        this.YRotateProg = YRotateProg2;
    }

    public int getZRotateProg() {
        return this.ZRotateProg;
    }

    public void setZRotateProg(int ZRotateProg2) {
        this.ZRotateProg = ZRotateProg2;
    }

    public int getScaleProg() {
        return this.ScaleProg;
    }

    public void setScaleProg(int ScaleProg2) {
        this.ScaleProg = ScaleProg2;
    }
}
