package com.pez.audio_player_application.pojo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @Source : lastfm/lastfm-android
 */

public class Album {
    private String artist;
    private String title;
    private String mbid;
    private String url;
    private String cover_url;


    public Album(JSONObject albumJSON) {
        try {
            this.artist = albumJSON.getString("artist");
            this.title = albumJSON.getString("name");
            this.mbid = albumJSON.getString("mbid");
            this.url = albumJSON.getString("url");
            JSONArray images = albumJSON.getJSONArray("image");
            this.cover_url = ((JSONObject)images.get(images.length() - 1)).getString("#text");;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Album(String artist, String title, String mbid, String url, String cover_url) {
        this.artist = artist;
        this.title = title;
        this.mbid = mbid;
        this.url = url;
        this.cover_url = cover_url;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public String getMbid() {
        return mbid;
    }

    public String getUrl() {
        return url;
    }

}
