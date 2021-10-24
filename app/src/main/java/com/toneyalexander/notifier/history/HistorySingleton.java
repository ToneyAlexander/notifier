package com.toneyalexander.notifier.history;

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
        this.history.add(new Notification(1, title, text));
        this.history.add(new Notification(1, title, text));
        this.history.add(new Notification(1, title, text));
        this.history.add(new Notification(1, title, text));
        this.history.add(new Notification(1, title, text));
        this.history.add(new Notification(1, title, text));
        return this.history;
    }

    public static HistorySingleton getInstance(){
        return HISTORY;
    }
}
