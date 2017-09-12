package list.notificationcreator;

import android.app.DialogFragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText title;
    private EditText text;
    private int id;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private SeekBar red, green, blue;
    private ImageView color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = (EditText) findViewById(R.id.contentTitle);
        text = (EditText) findViewById(R.id.contentText);
        color = (ImageView) findViewById(R.id.color);
        red = (SeekBar) findViewById(R.id.red);
        green = (SeekBar) findViewById(R.id.green);
        blue = (SeekBar) findViewById(R.id.blue);

        red.setProgressDrawable(getResources().getDrawable(R.drawable.red_seek_bar));
        green.setProgressDrawable(getResources().getDrawable(R.drawable.green_seek_bar));
        blue.setProgressDrawable(getResources().getDrawable(R.drawable.blue_seek_bar));

        red.setOnSeekBarChangeListener(new ColorSeekBarListener(this));
        green.setOnSeekBarChangeListener(new ColorSeekBarListener(this));
        blue.setOnSeekBarChangeListener(new ColorSeekBarListener(this));

        Context c = MainActivity.this;
        sharedPref = c.getSharedPreferences("details", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        id = sharedPref.getInt("id", 0);
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
        get the dater potater
    }

    //TODO: Sound mixer in notification bar

    public void createNotification(View view){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        //.setSmallIcon(android.R.drawable.ic_menu_sort_by_size)
                        //.setSmallIcon(android.R.drawable.ic_menu_selectall_holo_light)
                        .setSmallIcon(R.drawable.ic_star_border_white_24dp)
                        .setContentTitle(title.getText())
                        .setColor(((ColorDrawable) color.getBackground()).getColor())
                        .setContentText(text.getText());
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(id++, mBuilder.build());
        editor.putInt("id", id);
        editor.apply();
    }

    public void updateColor(){
        int r = (int) ((255.0 * red.getProgress()) / 100.0);
        int g = (int) ((255.0 * green.getProgress()) / 100.0);
        int b = (int) ((255.0 * blue.getProgress()) / 100.0);
        color.setBackgroundColor(Color.rgb(r, g, b));
    }
}
