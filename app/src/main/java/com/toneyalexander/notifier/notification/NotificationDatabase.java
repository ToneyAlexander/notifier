package com.toneyalexander.notifier.notification;

import com.toneyalexander.notifier.notification.Notification;
import com.toneyalexander.notifier.notification.NotificationDAO;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = Notification.class, version = 1, exportSchema = false)
abstract class NotificationDatabase extends RoomDatabase {
    public abstract NotificationDAO notificationDAO();
}
