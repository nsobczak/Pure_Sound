package com.pez.audio_player_application.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import android.provider.MediaStore;
import android.util.Log;
import android.util.LongSparseArray;

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

    public Track(String path, String name, String artist, String album, int duration) {
        this.path = path;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
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
}
