package com.pez.audio_player_application.ui.fragments;

import android.app.ActionBar;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.pez.audio_player_application.MainActivity;
import com.pez.audio_player_application.R;
import com.pez.audio_player_application.async.RetrieveTracksAsyncTask;
import com.pez.audio_player_application.interfaces.TrackListener;
import com.pez.audio_player_application.pojo.Track;


//__________________________________________________________________________

/**
 * MainActivityFragmentSongs : A placeholder fragment containing a songs list view.
 */
public class MainActivityFragmentSongs extends Fragment implements AdapterView.OnItemClickListener
{
    private RetrieveTracksAsyncTask retrieveTracksAsyncTask;
    private ListView songListView;
    private TrackListener trackListener;
//    private TweetsCursorAdapter mAdapter;


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
    public void onAttach(Context activity)
    {
        super.onAttach(activity);

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
        //inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_songs, container, false);
        this.songListView = (ListView) view.findViewById(R.id.songsListLayout);

        //Progress bar diplayed while loading
        final ProgressBar progressBar = new ProgressBar(getActivity());
        progressBar.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        progressBar.setIndeterminate(true);
        this.songListView.setEmptyView(progressBar);
        //Add the view in the content view
        ViewGroup root = (ViewGroup) view.findViewById(R.id.relativeLayout_SongsListFragment);
        root.addView(progressBar);

        //add a listener when an item is clicked
        this.songListView.setOnItemClickListener(this);
        Log.d("FragmentSongs", "onCreateView: " + this.songListView);

        return view;
    }


    //__________________________________________________________________________
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
    {
        final Track track = (Track) adapterView.getItemAtPosition(position);
        if (this.trackListener != null)
        {
            this.trackListener.onViewTrack(track);
        }

    }


}
