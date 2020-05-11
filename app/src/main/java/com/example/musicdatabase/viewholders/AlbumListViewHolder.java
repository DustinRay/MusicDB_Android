package com.example.musicdatabase.viewholders;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.musicdatabase.R;
import com.example.musicdatabase.TrackListActivity;
import com.example.musicdatabase.retrofit.models.AlbumsModel;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AlbumListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView albumNameTextView;
    private RelativeLayout layout;
    private ImageView imageView;
    private String artistName;
    private String albumName;
    private Context context;
    public static final String ALBUM_NAME = "ALBUM_NAME";
    public static final String ARTIST_NAME = "ARTIST_NAME";

    public AlbumListViewHolder(Context context, @NonNull View itemView) {
        super(itemView);
        this.context = context;
        albumNameTextView = itemView.findViewById(R.id.albumName);
        imageView = itemView.findViewById(R.id.albumCoverImage);
        layout = itemView.findViewById(R.id.albumListLayout);
    }

    public void bindData(final AlbumsModel.TopAlbumsModel.AlbumModel viewModel) {
        if (viewModel != null && !viewModel.name.isEmpty() && !viewModel.name.equals("(null)")) {
            albumNameTextView.setText(viewModel.name);
            albumName = viewModel.name;
            artistName = viewModel.artist.name;
            if (viewModel.image != null && viewModel.image.get(2) != null && !viewModel.image.get(2).text.isEmpty()) {
                Picasso.get().load(viewModel.image.get(2).text).into(imageView);
            }
        }
        layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(context, TrackListActivity.class);
        i.putExtra(ALBUM_NAME, albumName);
        i.putExtra(ARTIST_NAME, artistName);

        context.startActivity(i);
    }
}
