package com.example.musicdatabase;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.musicdatabase.retrofit.APIClient;
import com.example.musicdatabase.retrofit.APIInterface;
import com.example.musicdatabase.retrofit.models.ArtistInfoModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BioFragment extends Fragment {
    private TextView bioTextView;
    private TextView artistNameText;
    private Callback<ArtistInfoModel> getBioCallback;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getBioCallback = new Callback<ArtistInfoModel>() {
            @Override
            public void onResponse(@NonNull Call<ArtistInfoModel> call, @NonNull Response<ArtistInfoModel> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().artist != null) {
                            if (response.body().artist.bioModel != null) {
                                artistNameText.setText(response.body().artist.name);
                                bioTextView.setText(response.body().artist.bioModel.content);
                            } else {
                                Log.e("bio model is null", "bio model is null");
                            }
                        } else {
                            Log.e("artist model is null", "artist model is null");
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
        View view = inflater.inflate(R.layout.bio_fragment, container, false);
        bioTextView = view.findViewById(R.id.bioText);
        artistNameText = view.findViewById(R.id.artistNameText);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        Bundle args = getArguments();
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        String searchQuery = "";
        if (args != null && args.containsKey(MainActivity.SEARCH_QUERY)) {
            searchQuery = args.getString(MainActivity.SEARCH_QUERY);
        }

        if (!searchQuery.equals("")) {
            apiInterface.getArtistInfo(searchQuery, "5a3027525316600cd981c873f7dc54c1", "json").enqueue(getBioCallback);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getBioCallback = null;
    }
}
