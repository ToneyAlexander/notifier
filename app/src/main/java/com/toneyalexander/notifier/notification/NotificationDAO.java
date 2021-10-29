package com.toneyalexander.notifier.notification;

import com.toneyalexander.notifier.notification.Notification;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
interface NotificationDAO {
    @Query("SELECT * FROM notification ORDER BY id DESC;")
    List<Notification> getAll();

    @Insert
    void insertAll(Notification... notifications);

    @Delete
    void delete(Notification notification);
}
