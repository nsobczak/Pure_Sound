package com.pez.audio_player_application.async;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.pez.audio_player_application.SongDetailsActivity;
import com.pez.audio_player_application.helpers.MetadataSongHelper;
import com.pez.audio_player_application.interfaces.AlbumInfoChangeListener;
import com.pez.audio_player_application.pojo.Album;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auteur Baudouin
 * @Date 14/02/2017.
 */

public class RetrieveAlbumInfoAsyncTask extends AsyncTask<Album, Integer, List<Album>> {

    AlbumInfoChangeListener listener;

    @Override
    protected List<Album> doInBackground(Album... params) {
        List<Album> albumList = new ArrayList<>();
        for(Album album : params) {
            String albumName = album.getTitle();
            String artistName = album.getArtist();
            albumList.add(MetadataSongHelper.getAlbumInfo(albumName, artistName));
        }
        return albumList;
    }

    @Override
    protected void onPostExecute(List<Album> albums) {
        super.onPostExecute(albums);
        Log.d("", "info albums retrieved ");
        listener.onAlbumInfoRetrieved(albums);
    }


}
