package com.pez.audio_player_application.pojo;

import org.json.JSONException;
import org.json.JSONObject;


//__________________________________________________________________________

/**
 * @Auteur Baudouin
 * @Date 15/02/2017.
 */
public class Track {

    private String name;
    private int duration;

    public Track(String name, int duration) {
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

    public Track(JSONObject current_track) {
        try {
            if(current_track.has("name") && current_track.has("duration")) {
                this.name = current_track.getString("name");
                this.duration = current_track.getInt("duration");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
