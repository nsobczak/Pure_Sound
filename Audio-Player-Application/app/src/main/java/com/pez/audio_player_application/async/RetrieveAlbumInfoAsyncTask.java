package com.pez.audio_player_application.async;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.pez.audio_player_application.SongDetailsActivity;
import com.pez.audio_player_application.helpers.MetadataSongHelper;
import com.pez.audio_player_application.interfaces.AlbumInfoChangeListener;
import com.pez.audio_player_application.pojo.Album;
import com.pez.audio_player_application.pojo.Track;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auteur Baudouin
 * @Date 14/02/2017.
 */

public class RetrieveAlbumInfoAsyncTask extends AsyncTask<Track, Integer, List<Track>> {

    private ImageView imgView;

    public RetrieveAlbumInfoAsyncTask(ImageView imgView) {
        this.imgView = imgView;
    }
/*
    @Override
    protected List<Album> doInBackground(Album... params) {
        List<Album> albumList = new ArrayList<>();
        for(Album album : params) {
            String albumName = album.getTitle();
            String artistName = album.getArtist();
            albumList.add(MetadataSongHelper.getAlbumInfo(albumName, artistName));
        }
        return albumList;
    } */

    @Override
    protected List<Track> doInBackground(Track... params) {
        List<Track> tracksList = new ArrayList<>();
        Album gotAlbum = MetadataSongHelper.getAlbumInfo(params[0].getAlbum(), params[0].getArtist());
        if (gotAlbum != null) {
            params[0].setCover_url(gotAlbum.getCoverUrl());
        }
        tracksList.add(params[0]);
        return tracksList;
    }

    @Override
    protected void onPostExecute(List<Track> tracks) {
        super.onPostExecute(tracks);
        Log.d("", "info albums retrieved ");
        Track foundTrack = tracks.get(0);
        if (foundTrack != null && !foundTrack.getCover_url().equals("")) {
            DownloadImageAsyncTask getImgTsk = new DownloadImageAsyncTask(this.imgView, null);
            getImgTsk.execute(foundTrack.getCover_url());
        }
    }
}
