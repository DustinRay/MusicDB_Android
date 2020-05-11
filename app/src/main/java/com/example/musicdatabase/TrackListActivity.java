package com.example.musicdatabase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.musicdatabase.adapters.TrackListRecyclerViewAdapter;
import com.example.musicdatabase.retrofit.APIClient;
import com.example.musicdatabase.retrofit.APIInterface;
import com.example.musicdatabase.retrofit.models.TrackModel;
import com.example.musicdatabase.viewholders.AlbumListViewHolder;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackListActivity extends AppCompatActivity {
    private RecyclerView tracksRecyclerView;
    private Context context = TrackListActivity.this;
    private Callback<TrackModel> getTracksCallback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.track_list_activity);

        tracksRecyclerView = findViewById(R.id.tracksRecyclerView);
        TextView albumName = findViewById(R.id.albumTitleTextView);

        getTracksCallback = new Callback<TrackModel>() {
            @Override
            public void onResponse(@NonNull Call<TrackModel> call, @NonNull Response<TrackModel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().album.tracks != null) {
                            if (response.body().album.tracks.trackInfo.size() > 0) {
                                TrackListRecyclerViewAdapter adapter = new TrackListRecyclerViewAdapter(response.body().album.tracks.trackInfo);
                                tracksRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                                tracksRecyclerView.setAdapter(adapter);
                            }
                        } else {
                            Log.e("tracks model is null", "album model is null");
                        }
                    } else {
                        Log.e("response body is null", "response body is null");
                    }
                } else {
                    Log.e("response not successful", "response not successful");
                }
            }

            @Override
            public void onFailure(@NonNull Call<TrackModel> call, @NonNull Throwable t) {
                Log.e("error", "error");
            }
        };

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        Intent intent = getIntent();
        String albumTitle = "";
        String artistName = "";

        if (intent.getExtras() != null &&
                intent.getExtras().containsKey(AlbumListViewHolder.ARTIST_NAME) &&
                intent.getExtras().getString(AlbumListViewHolder.ARTIST_NAME) != null &&
                !Objects.equals(intent.getExtras().getString(AlbumListViewHolder.ARTIST_NAME), "") &&
                intent.getExtras().containsKey(AlbumListViewHolder.ARTIST_NAME)) {
            artistName = intent.getExtras().getString(AlbumListViewHolder.ARTIST_NAME);
        }

        if (intent.getExtras() != null &&
                intent.getExtras().containsKey(AlbumListViewHolder.ALBUM_NAME) &&
                intent.getExtras().getString(AlbumListViewHolder.ALBUM_NAME) != null &&
                !Objects.equals(intent.getExtras().getString(AlbumListViewHolder.ALBUM_NAME), "") &&
                intent.getExtras().containsKey(AlbumListViewHolder.ALBUM_NAME)) {
            albumTitle = intent.getExtras().getString(AlbumListViewHolder.ALBUM_NAME);
        }

        albumName.setText(albumTitle);

        if (albumTitle != null && artistName != null && !artistName.equals("") && !albumTitle.equals("")) {
            apiInterface.getTracks(getString(R.string.last_fm_api_key), artistName, albumTitle, getString(R.string.response_format)).enqueue(getTracksCallback);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getTracksCallback = null;
    }
}
