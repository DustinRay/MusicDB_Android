package com.example.musicdatabase.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.musicdatabase.R;
import com.example.musicdatabase.retrofit.models.ArtistInfoModel;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SimilarArtistsViewHolder  extends RecyclerView.ViewHolder {

    private TextView nameTextView;
    private ImageView imageView;

    public SimilarArtistsViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.artistImage);
        nameTextView = itemView.findViewById(R.id.artistName);
    }

    public void bindData(final ArtistInfoModel.ArtistBioModel.SimilarArtistsModel.ArtistModel viewModel) {
        nameTextView.setText(viewModel.name);
        if (viewModel.image.get(2) != null && viewModel.image.get(2) != null && !viewModel.image.get(2).text.isEmpty()) {
            Picasso.get().load(viewModel.image.get(2).text).into(imageView);
        }
    }
}
