package com.example.musicdatabase.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.musicdatabase.R;
import com.example.musicdatabase.retrofit.models.AlbumsModel;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AlbumListViewHolder extends RecyclerView.ViewHolder {

    private TextView albumNameTextView;
    private ImageView imageView;

    public AlbumListViewHolder(@NonNull View itemView) {
        super(itemView);
        albumNameTextView = itemView.findViewById(R.id.albumName);
        imageView = itemView.findViewById(R.id.albumCoverImage);
    }

    public void bindData(final AlbumsModel.TopAlbumsModel.AlbumModel viewModel) {
        albumNameTextView.setText(viewModel.name);
        if (viewModel.image != null && viewModel.image.get(2) != null && !viewModel.image.get(2).text.isEmpty()) {
            Picasso.get().load(viewModel.image.get(2).text).into(imageView);
        }
    }
}
