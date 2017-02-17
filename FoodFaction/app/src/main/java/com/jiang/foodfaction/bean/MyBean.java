package com.jiang.foodfaction.bean;

/**
 * Created by dllo on 17/2/13.
 */

public class MyBean {
    private int leftId;
    private String text;
    private int rightId;
    public MyBean() {
    }

    public int getLeftId() {
        return leftId;
    }

    public void setLeftId(int leftId) {
        this.leftId = leftId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRightId() {
        return rightId;
    }

    public void setRightId(int rightId) {
        this.rightId = rightId;
    }

    public MyBean(int leftId, String text, int rightId) {

        this.leftId = leftId;
        this.text = text;
        this.rightId = rightId;
    }
}
