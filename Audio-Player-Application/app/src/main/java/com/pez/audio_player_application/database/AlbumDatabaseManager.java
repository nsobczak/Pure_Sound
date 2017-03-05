package com.pez.audio_player_application.database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;

import com.pez.audio_player_application.AudioPlayerApplication;
import com.pez.audio_player_application.MainActivity;
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
        if (c != null) {
            final Album album = new Album();
            album.setTracks(new ArrayList<Track>());

            // artist
            int artist_index = c.getColumnIndex(AlbumDatabaseContract.ARTIST);
            if (artist_index >= 0) {
                String artist_name = c.getString(artist_index);
                album.setArtist(artist_name);
            }

            // title
            if (c.getColumnIndex(AlbumDatabaseContract.TITLE) >= 0) {
                album.setTitle(c.getString(c.getColumnIndex(AlbumDatabaseContract.TITLE)));
            }

            // mbid
            if (c.getColumnIndex(AlbumDatabaseContract.MBID) >= 0) {
                album.setMbid(c.getString(c.getColumnIndex(AlbumDatabaseContract.MBID)));
            }

            // url
            if (c.getColumnIndex(AlbumDatabaseContract.URL) >= 0) {
                album.setUrl(c.getString(c.getColumnIndex(AlbumDatabaseContract.URL)));
            }

            // tracks
            if (c.getColumnIndex(AlbumDatabaseContract.TRACKS) >= 0) {
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
            if (c.getColumnIndex(AlbumDatabaseContract.COVER_URL) >= 0) {
                album.setCoverUrl(c.getString(c.getColumnIndex(AlbumDatabaseContract.COVER_URL)));
            }
            return album;
        }
        return null;
    }

    public static ContentValues albumToContentValues(Album album) {
        final ContentValues values = new ContentValues();

        if (!TextUtils.isEmpty(album.getArtist())) {
            values.put(AlbumDatabaseContract.ARTIST, album.getArtist());
        }

        values.put(AlbumDatabaseContract.TITLE, album.getTitle());
        values.put(AlbumDatabaseContract.MBID, album.getMbid());
        values.put(AlbumDatabaseContract.URL, album.getUrl());
        values.put(AlbumDatabaseContract.TRACKS, "TODO"); // TODO
        values.put(AlbumDatabaseContract.COVER_URL, album.getCoverUrl());

        return values;
    }

    public static void saveAlbum(Album album) {
        try {
            final ContentValues contentValues = albumToContentValues(album);
            AudioPlayerApplication.getContext().getContentResolver().insert(AlbumDatabaseContract.ALBUM_META_URI, contentValues);
        } catch (Exception e) {
            Log.e("", e.getMessage());
        }
    }

    public static void deleteAlbum(Album album) {
        AudioPlayerApplication.getContext().getContentResolver().delete(AlbumDatabaseContract.ALBUM_META_URI,
                AlbumDatabaseContract.DELETE_ALBUM_MBID_EQUALS, new String[]{album.getMbid()});
    }

    public static void testContentProvider() {
        Context context = AudioPlayerApplication.getContext();
        if (context != null) {
            Cursor cursor = context.getContentResolver().query(AlbumDatabaseContract.ALBUM_META_URI,
                    AlbumDatabaseContract.PROJECTION_FULL, null, null, null);
            while (cursor.moveToNext()) {
                Album albumtest = AlbumDatabaseManager.albumFromCursor(cursor);
                Log.i("manager", "ok");
            }
        }
    }
}
