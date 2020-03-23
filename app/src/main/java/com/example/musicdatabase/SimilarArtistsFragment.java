package com.example.musicdatabase;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicdatabase.adapters.AlbumListRecyclerViewAdapter;
import com.example.musicdatabase.adapters.SimilarArtistsRecyclerViewAdapter;
import com.example.musicdatabase.retrofit.APIClient;
import com.example.musicdatabase.retrofit.APIInterface;
import com.example.musicdatabase.retrofit.models.AlbumsModel;
import com.example.musicdatabase.retrofit.models.ArtistInfoModel;
import com.example.musicdatabase.viewholders.SimilarArtistsViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SimilarArtistsFragment extends Fragment {

    private Callback<ArtistInfoModel> getSimilarArtistsCallback;
    private RecyclerView similarArtistsRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSimilarArtistsCallback = new Callback<ArtistInfoModel>() {
            @Override
            public void onResponse(@NonNull Call<ArtistInfoModel> call, @NonNull Response<ArtistInfoModel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().artist != null) {
                            if (response.body().artist.similar.artist.size() > 0) {
                                SimilarArtistsRecyclerViewAdapter adapter = new SimilarArtistsRecyclerViewAdapter(response.body().artist.similar.artist);
                                similarArtistsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                similarArtistsRecyclerView.setAdapter(adapter);
                            }
                        } else {
                            Log.e("album model is null", "album model is null");
                        }
                    } else {
                        Log.e("response body is null", "response body is null");
                    }
                } else {
                    Log.e("response not successful", "response not successful");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArtistInfoModel> call, @NonNull Throwable t) {
                Log.e("error", "error");
            }
        };
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.similar_artists_fragment, container, false);

        similarArtistsRecyclerView = view.findViewById(R.id.similarArtistsRecyclerView);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        Bundle args = getArguments();

        String searchQuery = "";
        if(args != null && args.containsKey(MainActivity.SEARCH_QUERY)){
            searchQuery = args.getString(MainActivity.SEARCH_QUERY);
        }

        if (!searchQuery.equals("")) {
            apiInterface.getArtistInfo(searchQuery, "5a3027525316600cd981c873f7dc54c1", "json").enqueue(getSimilarArtistsCallback);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getSimilarArtistsCallback = null;
    }
}
