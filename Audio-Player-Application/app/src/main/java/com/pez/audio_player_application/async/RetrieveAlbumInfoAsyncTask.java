package com.pez.audio_player_application.async;

import android.os.AsyncTask;

import com.pez.audio_player_application.SongDetailsActivity;
import com.pez.audio_player_application.helpers.MetadataSongHelper;
import com.pez.audio_player_application.pojo.Album;

/**
 * @Auteur Baudouin
 * @Date 14/02/2017.
 */

public class RetrieveAlbumInfoAsyncTask extends AsyncTask<String, Integer, Album> {
    // TODO : gérer le thread

    @Override
    protected Album doInBackground(String... params) {
        if(params.length >= 2) {
            String albumName = params[0];
            String artistName = params[1];
            // TODO : A enlever
             Album album = MetadataSongHelper.getAlbumInfo(albumName, artistName);
             MetadataSongHelper.getAlbumsFromArtist(artistName);

        }
        return null;
    }

    @Override
    protected void onPostExecute(Album album) {
        super.onPostExecute(album);
    }
}
