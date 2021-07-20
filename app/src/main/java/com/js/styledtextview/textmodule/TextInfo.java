package com.js.styledtextview.textmodule;

public class TextInfo {
    private int BG_ALPHA = 255;
    private int BG_COLOR = 0;
    private String BG_DRAWABLE = "0";
    int CurveRotateProg;
    private String FONT_NAME = "";
    private int HEIGHT;
    private int ORDER;
    private float POS_X = 0.0f;
    private float POS_Y = 0.0f;
    private float ROTATION;
    private int SHADOW_COLOR = 0;
    private int SHADOW_PROG = 0;
    private int TEMPLATE_ID;
    private String TEXT = "";
    private int TEXT_ALPHA = 100;
    private int TEXT_COLOR = -16777216;
    private int TEXT_ID;
    private String TYPE = "";
    private int WIDTH;
    int XRotateProg;
    int YRotateProg;
    int ZRotateProg;

    public TextInfo() {
    }

    public TextInfo(int TEMPLATE_ID2, String TEXT2, String FONT_NAME2, int TEXT_COLOR2, int TEXT_ALPHA2, int SHADOW_COLOR2, int SHADOW_PROG2, String BG_DRAWABLE2, int BG_COLOR2, int BG_ALPHA2, float POS_X2, float POS_Y2, int WIDTH2, int HEIGHT2, float ROTATION2, String TYPE2, int ORDER2, int XRotateProg2, int YRotateProg2, int ZRotateProg2, int CurveRotateProg2) {
        this.TEMPLATE_ID = TEMPLATE_ID2;
        this.TEXT = TEXT2;
        this.FONT_NAME = FONT_NAME2;
        this.TEXT_COLOR = TEXT_COLOR2;
        this.TEXT_ALPHA = TEXT_ALPHA2;
        this.SHADOW_COLOR = SHADOW_COLOR2;
        this.SHADOW_PROG = SHADOW_PROG2;
        this.BG_DRAWABLE = BG_DRAWABLE2;
        this.BG_COLOR = BG_COLOR2;
        this.BG_ALPHA = BG_ALPHA2;
        this.POS_X = POS_X2;
        this.POS_Y = POS_Y2;
        this.WIDTH = WIDTH2;
        this.HEIGHT = HEIGHT2;
        this.ROTATION = ROTATION2;
        this.TYPE = TYPE2;
        this.ORDER = ORDER2;
        this.XRotateProg = XRotateProg2;
        this.YRotateProg = YRotateProg2;
        this.ZRotateProg = ZRotateProg2;
        this.CurveRotateProg = CurveRotateProg2;
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

    public String getFONT_NAME() {
        return this.FONT_NAME;
    }

    public void setFONT_NAME(String FONT_NAME2) {
        this.FONT_NAME = FONT_NAME2;
    }

    public String getTEXT() {
        return this.TEXT;
    }

    public void setTEXT(String TEXT2) {
        this.TEXT = TEXT2;
    }

    public int getTEXT_COLOR() {
        return this.TEXT_COLOR;
    }

    public void setTEXT_COLOR(int TEXT_COLOR2) {
        this.TEXT_COLOR = TEXT_COLOR2;
    }

    public int getTEXT_ALPHA() {
        return this.TEXT_ALPHA;
    }

    public void setTEXT_ALPHA(int TEXT_ALPHA2) {
        this.TEXT_ALPHA = TEXT_ALPHA2;
    }

    public int getSHADOW_PROG() {
        return this.SHADOW_PROG;
    }

    public void setSHADOW_PROG(int SHADOW_PROG2) {
        this.SHADOW_PROG = SHADOW_PROG2;
    }

    public int getSHADOW_COLOR() {
        return this.SHADOW_COLOR;
    }

    public void setSHADOW_COLOR(int SHADOW_COLOR2) {
        this.SHADOW_COLOR = SHADOW_COLOR2;
    }

    public String getBG_DRAWABLE() {
        return this.BG_DRAWABLE;
    }

    public void setBG_DRAWABLE(String BG_DRAWABLE2) {
        this.BG_DRAWABLE = BG_DRAWABLE2;
    }

    public int getBG_COLOR() {
        return this.BG_COLOR;
    }

    public void setBG_COLOR(int BG_COLOR2) {
        this.BG_COLOR = BG_COLOR2;
    }

    public int getBG_ALPHA() {
        return this.BG_ALPHA;
    }

    public void setBG_ALPHA(int BG_ALPHA2) {
        this.BG_ALPHA = BG_ALPHA2;
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

    public float getROTATION() {
        return this.ROTATION;
    }

    public void setROTATION(float ROTATION2) {
        this.ROTATION = ROTATION2;
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

    public int getTEXT_ID() {
        return this.TEXT_ID;
    }

    public void setTEXT_ID(int TEXT_ID2) {
        this.TEXT_ID = TEXT_ID2;
    }

    public int getTEMPLATE_ID() {
        return this.TEMPLATE_ID;
    }

    public void setTEMPLATE_ID(int TEMPLATE_ID2) {
        this.TEMPLATE_ID = TEMPLATE_ID2;
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

    public int getCurveRotateProg() {
        return this.CurveRotateProg;
    }

    public void setCurveRotateProg(int CurveRotateProg2) {
        this.CurveRotateProg = CurveRotateProg2;
    }
}
