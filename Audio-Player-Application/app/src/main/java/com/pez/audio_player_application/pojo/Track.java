package com.pez.audio_player_application.pojo;

import org.json.JSONException;
import org.json.JSONObject;


//__________________________________________________________________________

/**
 * @Auteur Baudouin
 * @Date 15/02/2017.
 */
public class Track {
    private String path;
    private String name;
    private String artist;
    private String album;
    private int duration;
    private String cover_url;

    public Track() { }

    public Track(String path, String name, String artist, String album, int duration) {
        this.path = path;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
        this.cover_url = "";
    }

    @Override
    public String toString() {
        return this.getName() + " -- " + getArtist();
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getAlbum() {
        return this.album;
    }

    public int getDuration() {
        return duration;
    }

    public String getPath() {
        return this.path;
    }

    public Track(JSONObject current_track) {
        try {
            if (current_track.has("name") && current_track.has("duration")) {
                this.name = current_track.getString("name");
                this.duration = current_track.getInt("duration");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }
}
