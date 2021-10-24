package com.toneyalexander.notifier;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

public class CreateFragment extends Fragment {

    private EditText title;
    private EditText text;

    private Button create;

    private MainActivity activity;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = (MainActivity)this.getActivity();

        view.findViewById(R.id.button_history).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.next();
                //NavHostFragment.findNavController(CreateFragment.this)
                //        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        title = (EditText) view.findViewById(R.id.titleEditText);
        text = (EditText) view.findViewById(R.id.contentEditText);

        create = (Button) view.findViewById(R.id.re_create_button);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notification note = new Notification(Color.rgb(255, 0, 0), title.getText().toString(), text.getText().toString());
                createNotification(note);
            }
        });
    }

//TODO: Sound mixer in notification bar
    //TODO: color picker
    //TODO: joseph features?

    public void createNotification(Notification notification){
        /*
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        //.setSmallIcon(android.R.drawable.ic_menu_sort_by_size)
                        //.setSmallIcon(android.R.drawable.ic_menu_selectall_holo_light)
                        .setSmallIcon(R.drawable.ic_star_border_white_24dp)
                        .setColor(((ColorDrawable) color.getBackground()).getColor())
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(id++, mBuilder.build());
        editor.putInt("id", id);
        editor.apply();
        */

        //color pciker ui
        //backlog of notififcations

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), getString(R.string.channel_id))
                .setSmallIcon(R.drawable.ic_star_border_black_24dp)
                .setColor(notification.getColor())
                .setContentTitle(notification.getTitle())
                .setContentText(notification.getText())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(100, builder.build());

        //craashes???
    }
}