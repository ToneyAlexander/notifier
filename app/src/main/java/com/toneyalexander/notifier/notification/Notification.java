package com.toneyalexander.notifier.notification;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Notification {
    @PrimaryKey
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
