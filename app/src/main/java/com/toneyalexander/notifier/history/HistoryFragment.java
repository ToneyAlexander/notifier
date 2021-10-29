package com.toneyalexander.notifier.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toneyalexander.notifier.notification.Notification;
import com.toneyalexander.notifier.notification.NotificationDataSingleton;
import com.toneyalexander.notifier.MainActivity;
import com.toneyalexander.notifier.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryFragment extends Fragment {

    RecyclerView mRecyclerView;
    HistoryAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private MainActivity activity;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = (MainActivity)this.getActivity();

        mRecyclerView = view.findViewById(R.id.notification_history);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new HistoryAdapter(this.getContext(), getString(R.string.channel_id), new Callback() {
            @Override
            public void copy(Notification notification) {
                activity.copyAction(notification);
            }
        }, NotificationDataSingleton.getInstance().getHistory(this.getContext()));
        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // END_INCLUDE(initializeRecyclerView)
    }

    public interface Callback {
        public void copy(Notification n);
    }

    public void notifyDataSetChanged(){
        if(mAdapter != null) {
            mAdapter.updateDataset(NotificationDataSingleton.getInstance().getHistory(this.getContext()));
            mAdapter.notifyDataSetChanged();
        }
    }
}