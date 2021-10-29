package com.toneyalexander.notifier.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.toneyalexander.notifier.notification.NotificationDataSingleton;
import com.toneyalexander.notifier.notification.Notification;
import com.toneyalexander.notifier.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private HistoryFragment.Callback callback;
    private List<Notification> localDataSet;
    private Context context;
    private String channel;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView icon;
        private final TextView title;
        private final TextView text;

        private final Button createButton;
        private final Button copyButton;
        private final Button deleteButton;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            icon = (ImageView) view.findViewById(R.id.notification_icon);
            title = (TextView) view.findViewById(R.id.notification_title);
            text = (TextView) view.findViewById(R.id.notification_text);

            createButton = (Button) view.findViewById(R.id.re_create_button);
            copyButton = (Button) view.findViewById(R.id.copy_button);
            deleteButton = (Button) view.findViewById(R.id.delete_button);
        }

        public void setContents(Notification notification){
            icon.setColorFilter(notification.getColor());
            title.setText(notification.getTitle());
            text.setText(notification.getText());
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet containing the data to populate views to be used
     * by RecyclerView.
     */
    public HistoryAdapter(Context context, String channel, HistoryFragment.Callback callback, List<Notification> dataSet) {
        this.context = context;
        this.channel = channel;
        this.callback = callback;
        this.localDataSet = dataSet;
    }

    public void updateDataset(List<Notification> dataSet){
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.notification_history_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.setContents(localDataSet.get(position));
        viewHolder.createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationDataSingleton.createNotification(context, channel, localDataSet.get(position));
            }
        });
        viewHolder.copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.copy(localDataSet.get(position));
            }
        });
        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notification removed = localDataSet.remove(position);
                NotificationDataSingleton.getInstance().deleteNotififcation(context, removed);
                notifyDataSetChanged();
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
