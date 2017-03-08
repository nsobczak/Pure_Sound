package com.pez.audio_player_application.ui.fragments;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.pez.audio_player_application.AudioPlayerApplication;
import com.pez.audio_player_application.MainActivity;
import com.pez.audio_player_application.R;
import com.pez.audio_player_application.adapters.TracksAdapter;
import com.pez.audio_player_application.async.RetrieveTracksAsyncTask;
import com.pez.audio_player_application.interfaces.TrackChangeListener;
import com.pez.audio_player_application.interfaces.TrackListener;
import com.pez.audio_player_application.pojo.Album;
import com.pez.audio_player_application.pojo.Track;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


//__________________________________________________________________________

/**
 * MainActivityFragmentSongs : A placeholder fragment containing a songs list view.
 */
@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class MainActivityFragmentSongs extends Fragment implements TrackChangeListener, AdapterView.OnItemClickListener
{
    private DownloadAlbumInfo downloadAlbumInfo;
    private RetrieveTracksAsyncTask retrieveTracksAsyncTask;
    private ListView songListView;
    private TrackListener trackListener;


    //__________________________________________________________________________
    public MainActivityFragmentSongs()
    {
    }

    /**
     * Bind the activity in Fragment
     *
     * @param activity
     */
    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);

        Log.e("SongsFragment", "onAttach: ");
        if (activity instanceof MainActivity)
        {
            this.trackListener = (MainActivity) activity;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB) //To have the progress bar
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_main_songs, container, false);
        this.songListView = (ListView) view.findViewById(R.id.songsListLayout);

        //Set a Progress Bar as empty view, and display it while loading (set adapter with no elements))
        final ProgressBar progressBar = new ProgressBar(getActivity());
        progressBar.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        progressBar.setIndeterminate(true);
        this.songListView.setEmptyView(progressBar);
        //Add the view in the content view
        ViewGroup root = (ViewGroup) view.findViewById(R.id.relativeLayout_SongsListFragment);
        root.addView(progressBar);

        // Set adapter with no elements to let the ListView display the empty view
        this.songListView.setAdapter(new ArrayAdapter<Track>(getActivity(), android.R.layout.simple_list_item_1, new ArrayList<Track>()));

        //add a listener when an item is clicked
        this.songListView.setOnItemClickListener(this);

        return view;
    }


    @Override
    public void onStart()
    {
        super.onStart();

        this.retrieveTracksAsyncTask = new RetrieveTracksAsyncTask(this);
        this.retrieveTracksAsyncTask.execute();
    }


    @Override
    public void onTrackRetrieved(List<Track> tracks)
    {
        // On récupère les métadonnées
        //downloadAlbumInfo = new DownloadAlbumInfo();
        //downloadAlbumInfo.retrieveAlbumsInfo(tracks.toArray(new Track[tracks.size()]));

        //trie des chansons par ordre alphabétique
        if (tracks != null)
        {
            Collections.sort(tracks, new Comparator<Track>()
            {
                public int compare(Track trackA, Track trackB)
                {
                    return trackA.getName().compareTo(trackB.getName());
                }
            });
        }

        final TracksAdapter tracksAdapter = new TracksAdapter(tracks);
        tracksAdapter.setTrackListener(this.trackListener);
        this.songListView.setAdapter(tracksAdapter);
        Toast.makeText(AudioPlayerApplication.getContext(), "Tracks retrieved !", Toast.LENGTH_SHORT).show();

        this.retrieveTracksAsyncTask.cancel(true);
        this.retrieveTracksAsyncTask = null;
    }


    @Override
    public void onStop()
    {
        super.onStop();

        // If we have an AsyncTask running, close it
        if (this.retrieveTracksAsyncTask != null)
        {
            this.retrieveTracksAsyncTask.cancel(true);
        }
    }


    //__________________________________________________________________________
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
    {
        if (this.trackListener != null)
        {
            final Track track = (Track) adapterView.getItemAtPosition(position);
            this.trackListener.onViewTrack(track);
        }
    }


}