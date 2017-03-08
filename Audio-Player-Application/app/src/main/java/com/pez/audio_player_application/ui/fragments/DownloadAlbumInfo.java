package com.pez.audio_player_application.ui.fragments;

import android.os.AsyncTask;

import com.pez.audio_player_application.pojo.Track;

public class DownloadAlbumInfo extends MainActivityFragmentAlbums {

    private AsyncTask downloadTask;

    public void retrieveAlbumsInfo(Track... tracks) {
        //downloadTask = new RetrieveAlbumInfoAsyncTask(this).execute(tracks);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(downloadTask != null) {
            downloadTask.cancel(true);
        }
    }
}
