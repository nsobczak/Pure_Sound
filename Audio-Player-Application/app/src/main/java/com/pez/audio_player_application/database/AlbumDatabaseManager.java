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
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * @Auteur Baudouin
 * @Date 01/03/2017.
 * @Description : This class "serialize" data inputs in the order to manipulate them with database
 */

public class AlbumDatabaseManager {

    /* public static Album albumFromCursor(Cursor c) {
        if (c != null) {
            final Album album = new Album();
            album.setTracks(new ArrayList<Track>());

            // artist
            if (c.getColumnIndex(AlbumDatabaseContract.ARTIST) >= 0) {
                String artist_name = c.getString(c.getColumnIndex(AlbumDatabaseContract.ARTIST));
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

            // cover_url
            if (c.getColumnIndex(AlbumDatabaseContract.COVER_URL) >= 0) {
                album.setCoverUrl(c.getString(c.getColumnIndex(AlbumDatabaseContract.COVER_URL)));
            }
            return album;
        }
        return null;
    }
    */

    public static Track trackFromCursor(Cursor c) {
        if (c != null) {
            final Track track = new Track();

            // artist
            if (c.getColumnIndex(AlbumDatabaseContract.ARTIST) >= 0) {
                String artist_name = c.getString(c.getColumnIndex(AlbumDatabaseContract.ARTIST));
                track.setArtist(artist_name);
            }

            // title
            if (c.getColumnIndex(AlbumDatabaseContract.TITLE) >= 0) {
                track.setName(c.getString(c.getColumnIndex(AlbumDatabaseContract.TITLE)));
            }

            // title
            if (c.getColumnIndex(AlbumDatabaseContract.ALBUM) >= 0) {
                track.setAlbum(c.getString(c.getColumnIndex(AlbumDatabaseContract.ALBUM)));
            }

            // cover_url
            if (c.getColumnIndex(AlbumDatabaseContract.COVER_URL) >= 0) {
                track.setCover_url(c.getString(c.getColumnIndex(AlbumDatabaseContract.COVER_URL)));
            }
            return track;
        }
        return null;
    }

    /*
    public static ContentValues albumToContentValues(Album album) {
        final ContentValues values = new ContentValues();

        if (!TextUtils.isEmpty(album.getArtist())) {
            values.put(AlbumDatabaseContract.ARTIST, album.getArtist());
        }

        values.put(AlbumDatabaseContract.TITLE, album.getTitle());
        values.put(AlbumDatabaseContract.MBID, album.getMbid());
        values.put(AlbumDatabaseContract.URL, album.getUrl());
        values.put(AlbumDatabaseContract.COVER_URL, album.getCoverUrl());

        return values;
    } */

    public static ContentValues trackToContentValues(Track track) {
        final ContentValues values = new ContentValues();

        values.put(AlbumDatabaseContract.ARTIST, track.getArtist());
        values.put(AlbumDatabaseContract.TITLE, track.getName());
        values.put(AlbumDatabaseContract.ALBUM, track.getAlbum());
        values.put(AlbumDatabaseContract.COVER_URL, track.getCover_url());

        return values;
    }
/*
    @Deprecated
    public static void saveAlbum(Album album) {
        try {
            // We will not insert the album twice
            if(getAlbumFromDatabase(album.getTitle(), album.getArtist()) == null) {
                final ContentValues contentValues = trackToContentValues(album);
                AudioPlayerApplication.getContext().getContentResolver().insert(AlbumDatabaseContract.ALBUM_META_URI, contentValues);
                Log.i("AlbumDatabaseManager", "Successful insertion of the album: " + album.toString());
            }
        } catch (Exception e) {
            Log.e("", e.getMessage());
        }
    } */

    public static void saveTrack(Track track) {
        try {
            // We will not insert the album twice
            if(getTrackFromDatabase(track.getName(), track.getArtist()) == null) {
                final ContentValues contentValues = trackToContentValues(track);
                AudioPlayerApplication.getContext().getContentResolver().insert(AlbumDatabaseContract.ALBUM_META_URI, contentValues);
                Log.i("AlbumDatabaseManager", "Successful insertion of the track: " + track.toString());
            }
        } catch (Exception e) {
            Log.e("", e.getMessage());
        }
    }

    public static void deleteAlbum(Album album) {
        AudioPlayerApplication.getContext().getContentResolver().delete(AlbumDatabaseContract.ALBUM_META_URI,
                AlbumDatabaseContract.DELETE_ALBUM_MBID_EQUALS, new String[]{album.getMbid()});
    }

    /* public static Album getAlbumFromDatabase(String albumName, String artistName) {
        Context context = AudioPlayerApplication.getContext();
        if (context != null && !TextUtils.isEmpty(albumName) && !TextUtils.isEmpty(artistName)) {
            Cursor albumCursor = context.getContentResolver().query(AlbumDatabaseContract.ALBUM_META_URI,
                    AlbumDatabaseContract.PROJECTION_FULL,
                    AlbumDatabaseContract.COVER_URL_CLAUSE,
                    new String[] { albumName, artistName },
                    null
                    );
            if(albumCursor.moveToFirst())
                return AlbumDatabaseManager.albumFromCursor(albumCursor);
        }
        return null;
    }
*/

    public static Track getTrackFromDatabase(String trackName, String artistName) {
        Context context = AudioPlayerApplication.getContext();
        if (context != null && !TextUtils.isEmpty(trackName) && !TextUtils.isEmpty(artistName)) {
            Cursor albumCursor = context.getContentResolver().query(AlbumDatabaseContract.ALBUM_META_URI,
                    AlbumDatabaseContract.PROJECTION_FULL,
                    AlbumDatabaseContract.COVER_URL_CLAUSE,
                    new String[]{trackName, artistName},
                    null
            );
            if (albumCursor.moveToFirst()) {
                Track trackFind = AlbumDatabaseManager.trackFromCursor(albumCursor);
                if(trackFind.getName().equals(trackName) && trackFind.getArtist().equals(artistName)) {
                    return trackFind;
                }
            }
        }
        return null;
    }

    public static void testContentProvider() {
        Context context = AudioPlayerApplication.getContext();
        if (context != null) {
            Cursor cursor = context.getContentResolver().query(AlbumDatabaseContract.ALBUM_META_URI,
                    AlbumDatabaseContract.PROJECTION_FULL, null, null, null);
            while (cursor.moveToNext()) {
                Track track = AlbumDatabaseManager.trackFromCursor(cursor);
                Log.i("AlbumDatabaseManager", "Retrieved 1 track: " + track.toString() + " -- " + track.getCover_url());
            }
        }
    }
}
