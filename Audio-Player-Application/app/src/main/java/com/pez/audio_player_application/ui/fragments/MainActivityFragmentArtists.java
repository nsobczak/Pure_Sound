package com.pez.audio_player_application.ui.fragments;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pez.audio_player_application.R;


//__________________________________________________________________________

/**
 * MainActivityFragmentArtists : A placeholder fragment containing a songs list view.
 */
@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class MainActivityFragmentArtists extends Fragment
{

    public MainActivityFragmentArtists()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_main_artists, container, false);
    }


    //TODO: tri des artistes par ordre alphabétique
//    if (artists != null)
//    {
//        Collections.sort(artists, new Comparator<MediaStore.Audio.Artists>()
//        {
//            public int compare(MediaStore.Audio.Artists artistA, MediaStore.Audio.Artists artistB)
//            {
//                return artistA.getClass().getName().compareTo(artistB.getClass().getName());
//            }
//        });
//    }


}
