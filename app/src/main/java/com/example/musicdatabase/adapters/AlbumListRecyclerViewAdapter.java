package com.example.musicdatabase.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicdatabase.R;
import com.example.musicdatabase.retrofit.models.AlbumsModel;
import com.example.musicdatabase.viewholders.AlbumListViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AlbumListRecyclerViewAdapter extends RecyclerView.Adapter {
    private List<AlbumsModel.TopAlbumsModel.AlbumModel> mModels;

    public AlbumListRecyclerViewAdapter(List<AlbumsModel.TopAlbumsModel.AlbumModel> models) {
        mModels = models;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new AlbumListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((AlbumListViewHolder) holder).bindData(mModels.get(position));
    }

    @Override
    public int getItemCount() {
        return mModels.size();
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.album_list_viewholder;
    }
}
