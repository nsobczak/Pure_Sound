package com.pez.audio_player_application.ui.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pez.audio_player_application.R;


//__________________________________________________________________________

/**
 * MainActivityFragmentSongs : A placeholder fragment containing a songs list view.
 */
public class MainActivityFragmentSongs extends Fragment
{

    public MainActivityFragmentSongs()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_main_songs, container, false);
    }
}
