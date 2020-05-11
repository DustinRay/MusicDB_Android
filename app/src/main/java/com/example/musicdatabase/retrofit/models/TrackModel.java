package com.example.musicdatabase.retrofit.models;

import com.google.gson.annotations.SerializedName;

import java.net.URL;
import java.util.List;

public class TrackModel {
    @SerializedName("album")
    public AlbumModel album;

    public static class AlbumModel {
        @SerializedName("name")
        public String name;

        @SerializedName("artist")
        public String artist;

        @SerializedName("id")
        public String id;

        @SerializedName("mbid")
        public String mbid;

        @SerializedName("url")
        public URL url;

        @SerializedName("wiki")
        public WikiModel wiki;

        public static class WikiModel {
            @SerializedName("published")
            public String published;

            @SerializedName("summary")
            public String summary;

            @SerializedName("content")
            public String content;
        }

        @SerializedName("listeners")
        public Integer listeners;

        @SerializedName("playcount")
        public Integer playcount;

        @SerializedName("tracks")
        public TracksModel tracks;

        public static class TracksModel {

            @SerializedName("track")
            public List<TrackInfoModel> trackInfo;

            public static class TrackInfoModel {
                @SerializedName("name")
                public String name;

                @SerializedName("url")
                public URL url;

                @SerializedName("duration")
                public String duration;

                @SerializedName("artist")
                public ArtistModel artist;

                public static class ArtistModel{
                    @SerializedName("name")
                    public String artistName;

                    @SerializedName("mbid")
                    public String mbid;

                    @SerializedName("url")
                    public URL url;
                }
            }
        }

        @SerializedName("image")
        public List<ImageModel> image;

        public static class ImageModel {
            @SerializedName("#text")
            public String text;

            @SerializedName("size")
            public String size;
        }
    }
}
