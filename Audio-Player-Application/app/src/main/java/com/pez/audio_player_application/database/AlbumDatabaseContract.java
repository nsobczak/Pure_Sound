package com.pez.audio_player_application.database;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * @Auteur Baudouin
 * @Date 26/02/2017.
 */
public class AlbumDatabaseContract implements BaseColumns {

    public static String CREATE_TABLE_ALBUMS;
    public static String ALBUM_TABLE = "album";
    public static String ALBUM_DETAIL_TABLE = "album/#";

    public static String ALBUMS_CONTENT_TYPE = "vnd.android.cursor.dir/vnd.metadata.albums";
    public static String ALBUM_DETAIL_CONTENT_TYPE = "vnd.android.cursor.dir/vnd.metadata.album_detail";


    public static final String CONTENT_PROVIDER_AUTHORITY = "pez.audio_player_application.AlbumAuthority";
    public static final Uri ALBUM_META_URI = Uri.parse("content://" +  CONTENT_PROVIDER_AUTHORITY + "/" + ALBUM_TABLE);


    public static final String ARTIST    = "artist";
    public static final String TITLE     = "title";
    public static final String MBID      = "mbid";
    public static final String URL       = "url";
    public static final String TRACKS    = "tracks";
    public static final String COVER_URL = "cover_url";

    public static final String[] PROJECTION_FULL = new String[]{
            _ID,
            ARTIST,
            TITLE,
            MBID,
            URL,
            TRACKS,
            COVER_URL
    };


}
