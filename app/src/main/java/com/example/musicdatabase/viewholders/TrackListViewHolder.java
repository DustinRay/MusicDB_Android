package com.example.musicdatabase.viewholders;

import android.view.View;
import android.widget.TextView;

import com.example.musicdatabase.R;
import com.example.musicdatabase.retrofit.models.TrackModel;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TrackListViewHolder extends RecyclerView.ViewHolder {

    private TextView trackNameTextView;
    private TextView duration;

    public TrackListViewHolder(@NonNull View itemView) {
        super(itemView);
        trackNameTextView = itemView.findViewById(R.id.trackName);
        duration = itemView.findViewById(R.id.durationTextView);
    }

    public void bindData(final TrackModel.AlbumModel.TracksModel.TrackInfoModel viewModel) {
        trackNameTextView.setText(viewModel.name);
        duration.setText(viewModel.duration);

    }
}