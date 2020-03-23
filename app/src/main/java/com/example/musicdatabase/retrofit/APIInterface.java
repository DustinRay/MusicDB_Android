package com.example.musicdatabase.retrofit;
import com.example.musicdatabase.retrofit.models.AlbumsModel;
import com.example.musicdatabase.retrofit.models.ArtistInfoModel;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("?method=artist.getinfo")
    Call<ArtistInfoModel> getArtistInfo(@Query("artist") @NonNull String inArtist,
                                        @Query("api_key") @NonNull String inKey,
                                        @Query("format") @NonNull String inFormat);

    @GET("?method=artist.getTopAlbums")
    Call<AlbumsModel> getAlbums(@Query("artist") @NonNull String inArtist,
                                @Query("api_key") @NonNull String inKey,
                                @Query("format") @NonNull String inFormat);
}
