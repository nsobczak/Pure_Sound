package com.pez.audio_player_application.interfaces;


import com.pez.audio_player_application.pojo.Track;

import java.util.List;

/**
 * @author nicolas
 * @date 28/02/17.
 */
public interface TrackChangeListener
{
    public void onTrackRetrieved(List<Track> tweets);
}
