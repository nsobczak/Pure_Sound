package com.pez.audio_player_application.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.pez.audio_player_application.pojo.Album;
import com.pez.audio_player_application.pojo.Track;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @Auteur Baudouin
 * @Date 01/03/2017.
 * @Description : This class "serialize" data inputs in the order to manipulate them with database
 */

public class AlbumDatabaseManager {

    public static Album albumFromCursor(Cursor c) {
        if(c != null) {
            final Album album = new Album();
            album.setTracks(new ArrayList<Track>());

            // artist
            if(c.getColumnIndex(AlbumDatabaseContract.ARTIST) >= 0) {
                album.setArtist(c.getString(c.getColumnIndex(AlbumDatabaseContract.ARTIST)));
            }

            // title
            if(c.getColumnIndex(AlbumDatabaseContract.TITLE) >= 0) {
                album.setTitle(c.getString(c.getColumnIndex(AlbumDatabaseContract.TITLE)));
            }

            // mbid
            if(c.getColumnIndex(AlbumDatabaseContract.MBID) >= 0) {
                album.setMbid(c.getString(c.getColumnIndex(AlbumDatabaseContract.MBID)));
            }

            // url
            if(c.getColumnIndex(AlbumDatabaseContract.URL) >= 0) {
                album.setUrl(c.getString(c.getColumnIndex(AlbumDatabaseContract.URL)));
            }

            // tracks
            if(c.getColumnIndex(AlbumDatabaseContract.TRACKS) >= 0) {
                try {
                    ArrayList<Track> tracks = new ArrayList<>();
                    JSONObject tracks_obj = new JSONObject(c.getString(c.getColumnIndex(AlbumDatabaseContract.TRACKS)));
                    JSONArray tracklist = tracks_obj.getJSONArray("track");
                    for (int i = 0; i < tracklist.length(); i++) {
                        JSONObject current_track = tracklist.getJSONObject(i);
                        tracks.add(new Track(current_track));
                    }
                    album.setTracks(tracks);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            // cover_url
            if(c.getColumnIndex(AlbumDatabaseContract.COVER_URL) >= 0) {
                album.setCoverUrl(c.getString(c.getColumnIndex(AlbumDatabaseContract.COVER_URL)));
            }

        }
        return null; // TODO
    }

    public static ContentValues albumToContentValues(Album album) {
        
        return null; // TODO
    }

}
