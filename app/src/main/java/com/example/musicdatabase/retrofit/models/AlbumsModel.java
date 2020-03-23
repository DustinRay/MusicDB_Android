package com.example.musicdatabase.retrofit.models;

import com.google.gson.annotations.SerializedName;

import java.net.URL;
import java.util.List;

public class AlbumsModel {
    @SerializedName("topalbums")
    public TopAlbumsModel topAlbums;

    public static class TopAlbumsModel {
        @SerializedName("album")
        public List<AlbumModel> albumModel;

        public static class AlbumModel {
            @SerializedName("name")
            public String name;

            @SerializedName("playcount")
            public Integer playcount;

            @SerializedName("url")
            public URL url;

            @SerializedName("artist")
            public ArtistModel artist;

            @SerializedName("image")
            public List<ImageModel> image;

            public static class ArtistModel {
                @SerializedName("name")
                public String name;

                @SerializedName("url")
                public URL url;
            }

            public static class ImageModel {
                @SerializedName("#text")
                public String text;

                @SerializedName("size")
                public String size;
            }
        }
    }
}
