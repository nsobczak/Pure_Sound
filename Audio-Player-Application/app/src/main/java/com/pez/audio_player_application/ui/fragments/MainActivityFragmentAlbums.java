package com.pez.audio_player_application.ui.fragments;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pez.audio_player_application.R;
import com.pez.audio_player_application.database.AlbumDatabaseManager;
import com.pez.audio_player_application.interfaces.AlbumInfoChangeListener;
import com.pez.audio_player_application.pojo.Track;

import java.util.List;


//__________________________________________________________________________

/**
 * MainActivityFragmentAlbums : A placeholder fragment containing a songs list view.
 */
@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class MainActivityFragmentAlbums extends Fragment implements AlbumInfoChangeListener
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_main_albums, container, false);
    }

    @Override
    public void onAlbumInfoRetrieved(List<Track> tracks) {
        // We store the track metadata (if necessary)
        for(Track track : tracks)
            AlbumDatabaseManager.saveTrack(track);
    }


        //TODO: tri des albums par ordre alphabétique
//    if (albums != null)
//    {
//        Collections.sort(albums, new Comparator<Album>()
//        {
//            public int compare(Album albumA, Album albumB)
//            {
//                return albumA.getTitle().compareTo(albumB.getTitle());
//            }
//        });
//    }

}
