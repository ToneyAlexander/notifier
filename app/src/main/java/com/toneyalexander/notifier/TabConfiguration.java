package com.toneyalexander.notifier;

import com.toneyalexander.notifier.history.HistoryFragment;

import androidx.fragment.app.Fragment;

public final class TabConfiguration {

    private static final Tab CREATE = new Tab("Create", new CreateFragment());
    private static final Tab HISTORY = new Tab("History", new HistoryFragment());

    public static Tab getTab(int position) {
        switch(position){
            case 1:
                return HISTORY;
            default:
                return CREATE;
        }
    }

    public static class Tab {
        String title;
        Fragment body;

        public Tab(String title, Fragment body){
            this.title = title;
            this.body = body;
        }

        public Fragment getBody(){
            return this.body;
        }

        public  String getTitle(){
            return this.title;
        }
    }

}
