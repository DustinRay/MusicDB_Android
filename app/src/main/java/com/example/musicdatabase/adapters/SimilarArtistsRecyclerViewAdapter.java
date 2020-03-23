package com.example.musicdatabase.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicdatabase.R;
import com.example.musicdatabase.retrofit.models.ArtistInfoModel;
import com.example.musicdatabase.viewholders.SimilarArtistsViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SimilarArtistsRecyclerViewAdapter  extends RecyclerView.Adapter {
    private List<ArtistInfoModel.ArtistBioModel.SimilarArtistsModel.ArtistModel> mModels;

    public SimilarArtistsRecyclerViewAdapter(List<ArtistInfoModel.ArtistBioModel.SimilarArtistsModel.ArtistModel> models) {
        mModels = models;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new SimilarArtistsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((SimilarArtistsViewHolder) holder).bindData(mModels.get(position));
    }

    @Override
    public int getItemCount() {
        return mModels.size();
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.similar_artists_viewholder;
    }
}
