package com.toneyalexander.notifier;

import android.graphics.Color;

public class Notification {
    private String title;
    private String text;
    private int color;

    public Notification(int color, String title, String text){
        this.color = color;
        this.title = title;
        this.text = text;
    }

    public int getColor(){
        return color;
    }

    public String getTitle(){
        return title;
    }

    public String getText(){
        return text;
    }
}
