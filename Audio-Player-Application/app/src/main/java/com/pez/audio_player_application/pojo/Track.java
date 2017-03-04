package com.pez.audio_player_application.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;


//__________________________________________________________________________

/**
 * @Auteur Baudouin
 * @Date 15/02/2017.
 */
public class Track{
    private String path;
    private String name;
    private int duration;

    public Track(String path,String name, int duration)  {
        this.path = path;
        this.name = name;
        this.duration = duration;
    }

    public String getName()
    {
        return name;
    }

    public int getDuration()
    {
        return duration;
    }

    public String getPath(){
        return this.path;
    }

    public Track(JSONObject current_track) {
        try {
            if(current_track.has("name") && current_track.has("duration") && current_track.has("path")) {
                this.name = current_track.getString("name");
                this.duration = current_track.getInt("duration");
                this.path = current_track.getString("path");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
