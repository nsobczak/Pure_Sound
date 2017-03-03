package com.pez.audio_player_application.database;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * @Auteur Baudouin
 * @Date 26/02/2017.
 */
public class AlbumDatabaseContract implements BaseColumns {

    /* URI */
    public static String ALBUM_TABLE = "album";
    public static String ALBUM_DETAIL_TABLE = "album/#";
    public static final String CONTENT_PROVIDER_AUTHORITY = "pez.audio_player_application.AlbumAuthority";
    public static final Uri ALBUM_META_URI = Uri.parse("content://" +  CONTENT_PROVIDER_AUTHORITY + "/" + ALBUM_TABLE);

    /* Database columns */

    public static final String ARTIST    = "artist";
    public static final String TITLE     = "title";
    public static final String MBID      = "mbid";
    public static final String URL       = "url";
    public static final String TRACKS    = "tracks";
    public static final String COVER_URL = "cover_url";

    public static String CREATE_TABLE_IF_NOT_EXIST = "CREATE TABLE IF NOT EXISTS ";
    public static final String ALBUM_TABLE_SUFFIX = "(" + _ID + " INTEGER PRIMARY KEY, " +
            ARTIST + " TEXT NOT NULL, " +
            TITLE + " TEXT NOT NULL, " +
            MBID + " TEXT NOT NULL, " +
            URL + " TEXT NOT NULL, " +
            TRACKS + " VARCHAR NOT NULL, " +
            COVER_URL + " VARCHAR NOT NULL)";
    public static String CREATE_TABLE_ALBUMS = CREATE_TABLE_IF_NOT_EXIST + ALBUM_TABLE + ALBUM_TABLE_SUFFIX;
    public static String DELETE_ALBUM_MBID_EQUALS = MBID + " = ?";

    public static String ALBUMS_CONTENT_TYPE = "vnd.android.cursor.dir/vnd.metadata.albums";
    public static String ALBUM_DETAIL_CONTENT_TYPE = "vnd.android.cursor.dir/vnd.metadata.album_detail";

    public static final String[] PROJECTION_FULL = new String[]{
            ARTIST,
            TITLE,
            MBID,
            URL,
            TRACKS,
            COVER_URL
    };


}
