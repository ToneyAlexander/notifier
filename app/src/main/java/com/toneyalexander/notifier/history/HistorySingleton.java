package com.toneyalexander.notifier.history;

import android.graphics.Color;

import com.toneyalexander.notifier.Notification;

import java.util.ArrayList;
import java.util.List;

public class HistorySingleton {
    private static final HistorySingleton HISTORY = new HistorySingleton();

    private final List<Notification> history = new ArrayList<>();

    private HistorySingleton(){}

    public List<Notification> getHistory(){

        String title = "Notification Title Here";
        String text = "Notification text here";
        this.history.add(new Notification(1121, 1, title, text));
        this.history.add(new Notification(1122, Color.rgb(255, 0, 0), title, text));
        this.history.add(new Notification(1123, Color.rgb(0, 255, 0), title, text));
        this.history.add(new Notification(1124, Color.rgb(0, 0, 255), title, text));
        this.history.add(new Notification(1125, Color.rgb(255, 255, 255), title, text));
        return this.history;
    }

    public static HistorySingleton getInstance(){
        return HISTORY;
    }
}
