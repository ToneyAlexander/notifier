package com.toneyalexander.notifier;

import android.graphics.Color;

public class Notification {
    private int id;
    private int color;
    private String title;
    private String text;

    public Notification(int id, int color, String title, String text){
        this.id = id;
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

    public int getId(){
        return id;
    }
}
