package com.toneyalexander.notifier;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

public class CreateFragment extends Fragment {

    private EditText title;
    private EditText text;

    private ImageView preview;
    private ImageView colorPicker;

    private Button create;

    private MainActivity activity;

    private int color = Color.rgb(255, 0, 0);

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private final String NOTIFICATION_ID = "notificationId";

    private int notificationId;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create, container, false);
    }

    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = (MainActivity)this.getActivity();

        preferences = activity.getSharedPreferences("Notifier", Context.MODE_PRIVATE);
        editor = preferences.edit();

        notificationId = preferences.getInt(NOTIFICATION_ID, 0);

        title = (EditText) view.findViewById(R.id.titleEditText);
        text = (EditText) view.findViewById(R.id.contentEditText);

        preview = (ImageView) view.findViewById(R.id.color_preview);
        updatePreview();

        colorPicker = (ImageView) view.findViewById(R.id.color_picker);
        final Bitmap picker_bitmap = ((BitmapDrawable)colorPicker.getDrawable()).getBitmap();
        colorPicker.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        int x = (int)((event.getX()/colorPicker.getWidth()) * picker_bitmap.getWidth());
                        int y = (int)((event.getY()/colorPicker.getHeight()) * picker_bitmap.getHeight());
                        Log.e("press", "x: " + x + "/" + picker_bitmap.getWidth());
                        Log.e("press", "y: " + y + "/" + picker_bitmap.getHeight());

                        color = picker_bitmap.getPixel(x,y);
                        updatePreview();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e("drag", "x: " + event.getX() + "/" + picker_bitmap.getWidth());
                        Log.e("drag", "y: " + event.getY() + "/" + picker_bitmap.getHeight());

                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return true;
            }
        });

        create = (Button) view.findViewById(R.id.re_create_button);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notification note = new Notification(notificationId++, color, title.getText().toString(), text.getText().toString());
                editor.putInt(NOTIFICATION_ID, notificationId);
                editor.apply();
                createNotification(note);
            }
        });
    }

    private void updatePreview(){
        preview.setColorFilter(color);
    }

    //TODO: Sound mixer in notification bar

    //TODO: Pairity: saving
    //TODO: unique id
    //todo: games in notif?

    //TODO: Color - last HSV slider
    //TODO app icon
    //color pciker ui
    //backlog of notififcations

    public void createNotification(Notification notification){
        /*
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        //.setSmallIcon(android.R.drawable.ic_menu_sort_by_size)
                        //.setSmallIcon(android.R.drawable.ic_menu_selectall_holo_light)
                        .setSmallIcon(R.drawable.ic_star_border_white_24dp)
                        .setColor(((ColorDrawable) color.getBackground()).getColor())
        */



        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), getString(R.string.channel_id))
                .setSmallIcon(R.drawable.ic_star_border_black_24dp)
                .setColor(notification.getColor())
                .setContentTitle(notification.getTitle())
                .setContentText(notification.getText())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(notification.getId(), builder.build());
    }
}