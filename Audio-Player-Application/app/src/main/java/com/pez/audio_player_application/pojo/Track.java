package com.pez.audio_player_application.pojo;

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
public class Track
{
    private String name;
    private String artist;
    private String albumTitle;
    private int duration;


    //__________________________________________________________________________
    public Track(String name, int duration)
    {
        this.name = name;
        this.duration = duration;
    }

    public Track(String name, String artist, String albumTitle, int duration)
    {
        this.name = name;
        this.artist = artist;
        this.albumTitle = albumTitle;
        this.duration = duration;
    }


    public String getName()
    {
        return name;
    }

    public String getArtist()
    {
        return artist;
    }

    public String getAlbumTitle()
    {
        return albumTitle;
    }

    public int getDuration()
    {
        return duration;
    }


    /**
     * Convert duration to a String in minutes + seconds + eventually hours
     *
     * @return theConvertedDuration
     */
    public String getConvertedDuration()
    {
        String result = "";

        Long durationMs = Long.valueOf(this.duration);
        long durationBuff = durationMs / 1000;
        long h = durationBuff / 3600;
        long m = (durationBuff - h) / 60;
        long s = durationBuff - (h * 3600 + m * 60);

        if (h != 0)
        {
            result += String.valueOf(h) + ":";
        }
        result += String.valueOf(m) + "'" + String.valueOf(s) + '"';

        return result;
    }


    public Track(JSONObject current_track)
    {
        try
        {
            if (current_track.has("name") && current_track.has("duration"))
            {
                this.name = current_track.getString("name");
                this.duration = current_track.getInt("duration");
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
