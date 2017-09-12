package list.notificationcreator;

import android.graphics.Color;
import android.media.Image;
import android.widget.ImageView;
import android.widget.SeekBar;

/**
 * Created by Alexander on 2/5/2016.
 */
public class ColorSeekBarListener implements SeekBar.OnSeekBarChangeListener {
    private MainActivity parent;

    public ColorSeekBarListener(MainActivity pparent){
        super();
        parent = pparent;
    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        parent.updateColor();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
