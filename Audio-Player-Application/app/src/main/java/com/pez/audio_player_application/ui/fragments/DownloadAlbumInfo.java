package com.pez.audio_player_application.ui.fragments;

import android.os.AsyncTask;

import com.pez.audio_player_application.async.RetrieveAlbumInfoAsyncTask;
import com.pez.audio_player_application.pojo.Album;

public class DownloadAlbumInfo extends MainActivityFragmentAlbums {

    private AsyncTask downloadTask;

    public void retrieveAlbumsInfo(Album... albums) {
        downloadTask = new RetrieveAlbumInfoAsyncTask(this).execute(albums);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(downloadTask != null) {
            downloadTask.cancel(true);
        }
    }
}
