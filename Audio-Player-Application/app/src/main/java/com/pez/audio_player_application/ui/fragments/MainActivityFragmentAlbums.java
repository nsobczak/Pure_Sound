package com.pez.audio_player_application.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pez.audio_player_application.R;


//__________________________________________________________________________

/**
 * MainActivityFragmentAlbums : A placeholder fragment containing a songs list view.
 */
public class MainActivityFragmentAlbums extends Fragment
{

    public MainActivityFragmentAlbums()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_main_albums, container, false);
    }
}
