package com.pez.audio_player_application.pojo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Album {
    private String artist;
    private String title;
    private String mbid;
    private String url;
    private String cover_url;
    private List<Track> tracks;

    public Album() {
        this("","","","","");
    }

    public Album(String artist, String title) {
        this(artist, title, "", "", "");
    }

    public Album(String artist, String title, String mbid, String url, String cover_url) {
        this.artist = artist;
        this.title = title;
        this.mbid = mbid;
        this.url = url;
        this.cover_url = cover_url;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public Album(JSONObject albumJSON) {
        try {
            this.artist = albumJSON.getString("artist");
            if(albumJSON.has("name"))
                this.title = albumJSON.getString("name");
            if(albumJSON.has("mbid"))
                this.mbid = albumJSON.getString("mbid");
            if(albumJSON.has("url"))
                this.url = albumJSON.getString("url");
            JSONArray images = albumJSON.getJSONArray("image");
            this.cover_url = ((JSONObject)images.get(images.length() - 1)).getString("#text");
            this.tracks = new ArrayList<>();
            if(albumJSON.has("tracks")) {
                JSONArray tracklist = albumJSON.getJSONObject("tracks").getJSONArray("track");
                for (int i = 0; i < tracklist.length(); i++) {
                    JSONObject current_track = tracklist.getJSONObject(i);
                    this.tracks.add(new Track(current_track));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;

    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCoverUrl(String cover_url) {
        this.cover_url = cover_url;
    }

    public String getMbid() {
        return mbid;
    }

    public String getUrl() {
        return url;
    }

}
