package com.toneyalexander.notifier.notification;

import android.content.Context;

import com.toneyalexander.notifier.R;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.room.Room;

public class NotificationDataSingleton {
    private NotificationDataSingleton(){}

    private static final NotificationDataSingleton SINGLETON = new NotificationDataSingleton();

    private static NotificationDatabase db;

    public static NotificationDataSingleton getInstance(){
        return SINGLETON;
    }

    private NotificationDatabase getDatabase(Context context){
        if(db == null){
            db = Room.databaseBuilder(context,
                    NotificationDatabase.class, "notification-db")
                    .allowMainThreadQueries()
                    .build();
        }
        return db;
    }

    public List<Notification> getHistory(Context context){
        return getDatabase(context).notificationDAO().getAll();
    }

    public void writeNotification(Context context, Notification... notifications){
        getDatabase(context).notificationDAO().insertAll(notifications);
    }

    public void deleteNotififcation(Context context, Notification notification){
        ExecutorService exec = Executors.newSingleThreadExecutor();
        class DeleteRunnable implements Runnable {
            Context context;
            Notification notification;
            public DeleteRunnable(Context p_context, Notification p_notification){
                context = p_context;
                notification = p_notification;
            }
            @Override
            public void run() {
                getDatabase(context).notificationDAO().delete(notification);
            }
        }
        exec.submit(new DeleteRunnable(context, notification));
    }

    //TODO: Sound mixer in notification bar
    //todo: games in notif?

    //TODO app icon
    //TODO: make this async - as it is the UI thread will hang on this so...
    // Use a local storage layer? all app ops happen on local level, with threaded changes on back

    public static void createNotification(Context context, String channel, Notification notification){
        /*
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        //.setSmallIcon(android.R.drawable.ic_menu_sort_by_size)
                        //.setSmallIcon(android.R.drawable.ic_menu_selectall_holo_light)
                        .setSmallIcon(R.drawable.ic_star_border_white_24dp)
                        .setColor(((ColorDrawable) color.getBackground()).getColor())
        */

        //    <string name="channel_id">NOTIFIER_ID</string>
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channel)
                .setSmallIcon(R.drawable.ic_star_border_black_24dp)
                .setColor(notification.getColor())
                .setContentTitle(notification.getTitle())
                .setContentText(notification.getText())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(notification.getId(), builder.build());
    }
}
