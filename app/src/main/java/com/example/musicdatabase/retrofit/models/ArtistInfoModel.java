package com.example.musicdatabase.retrofit.models;

import com.example.musicdatabase.SimilarArtistsFragment;
import com.google.gson.annotations.SerializedName;

import java.net.URL;
import java.util.List;

public class ArtistInfoModel {
    @SerializedName("artist")
    public ArtistBioModel artist;

    public static class ArtistBioModel {
        @SerializedName("name")
        public String name;

        @SerializedName("url")
        public URL url;

        @SerializedName("image")
        public List<ImageModel> image;

        public static class ImageModel {
            @SerializedName("#text")
            public String text;

            @SerializedName("size")
            public String size;
        }

        @SerializedName("streamable")
        public Integer streamable;

        @SerializedName("ontour")
        public Integer ontour;

        @SerializedName("stats")
        public StatsModel stats;

        public static class StatsModel {
            @SerializedName("listeners")
            public Integer listeners;

            @SerializedName("playcount")
            public Integer plays;
        }

        @SerializedName("bio")
        public BioModel bioModel;

        public static class BioModel {
            @SerializedName("links")
            public LinkModel links;

            public static class LinkModel {
                @SerializedName("#text")
                public String text;

                @SerializedName("rel")
                public String rel;

                @SerializedName("href")
                public String href;
            }

            @SerializedName("summary")
            public String summary;

            @SerializedName("content")
            public String content;
        }

        @SerializedName("similar")
        public SimilarArtistsModel similar;

        public static class SimilarArtistsModel {
            @SerializedName("artist")
            public List<ArtistModel> artist;

            public static class ArtistModel{
                @SerializedName("name")
                public String name;

                @SerializedName("url")
                public URL url;

                @SerializedName("image")
                public List<ImageModel> image;
            }
        }

    }
}

