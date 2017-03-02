package com.pez.audio_player_application.async;

import android.os.AsyncTask;
import android.util.Log;

import com.pez.audio_player_application.helpers.RetrieveTrackHelper;
import com.pez.audio_player_application.interfaces.TrackChangeListener;
import com.pez.audio_player_application.pojo.Track;

import java.util.List;


//__________________________________________________________________________

/**
 * @author nicolas
 * @date 28/02/17
 */
public class RetrieveTracksAsyncTask extends AsyncTask<Track, Void, List<Track>>
{
    private TrackChangeListener trackChangeListener;

    public RetrieveTracksAsyncTask(TrackChangeListener trackChangeListener)
    {
        this.trackChangeListener = trackChangeListener;
    }


    //__________________________________________________________________________
    @Override
    protected List<Track> doInBackground(Track... tracks)
    {
        Log.d("TrackAsyncTask", "doInBackground: ");
        return RetrieveTrackHelper.getSongList();
    }


    @Override
    protected void onPostExecute(List<Track> trackList)
    {
        super.onPostExecute(trackList);

        if (this.trackChangeListener != null)
        {
            this.trackChangeListener.onTrackRetrieved(trackList);
        }
    }


}
