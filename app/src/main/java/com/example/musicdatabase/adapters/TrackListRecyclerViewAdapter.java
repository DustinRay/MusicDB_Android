package com.example.musicdatabase.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicdatabase.R;
import com.example.musicdatabase.retrofit.models.TrackModel;
import com.example.musicdatabase.viewholders.TrackListViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TrackListRecyclerViewAdapter extends RecyclerView.Adapter {
    private List<TrackModel.AlbumModel.TracksModel.TrackInfoModel> mModels;

    public TrackListRecyclerViewAdapter(List<TrackModel.AlbumModel.TracksModel.TrackInfoModel> models) {
        mModels = models;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new TrackListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((TrackListViewHolder) holder).bindData(mModels.get(position));
    }

    @Override
    public int getItemCount() {
        return mModels.size();
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.track_list_view_holder;
    }
}
