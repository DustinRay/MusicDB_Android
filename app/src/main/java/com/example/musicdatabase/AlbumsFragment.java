package com.example.musicdatabase;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.musicdatabase.adapters.AlbumListRecyclerViewAdapter;
import com.example.musicdatabase.retrofit.APIClient;
import com.example.musicdatabase.retrofit.APIInterface;
import com.example.musicdatabase.retrofit.models.AlbumsModel;
import com.example.musicdatabase.retrofit.models.ArtistInfoModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumsFragment extends Fragment {

    private RecyclerView albumsRecyclerView;
    private Callback<AlbumsModel> getAlbumsCallback;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getAlbumsCallback = new Callback<AlbumsModel>() {
            @Override
            public void onResponse(@NonNull Call<AlbumsModel> call, @NonNull Response<AlbumsModel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().topAlbums.albumModel != null) {
                            if (response.body().topAlbums.albumModel.size() > 0) {
                                AlbumListRecyclerViewAdapter adapter = new AlbumListRecyclerViewAdapter(response.body().topAlbums.albumModel);
                                albumsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                albumsRecyclerView.setAdapter(adapter);
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
            public void onFailure(@NonNull Call<AlbumsModel> call, @NonNull Throwable t) {
                Log.e("error", "error");
            }
        };
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.albums_fragment, container, false);
        albumsRecyclerView = view.findViewById(R.id.albumsRecyclerView);

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
            apiInterface.getAlbums(searchQuery, "5a3027525316600cd981c873f7dc54c1", "json").enqueue(getAlbumsCallback);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getAlbumsCallback = null;
    }
}
