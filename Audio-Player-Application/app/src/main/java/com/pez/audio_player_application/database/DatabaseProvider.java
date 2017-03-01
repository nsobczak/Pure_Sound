package com.pez.audio_player_application.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @Auteur Baudouin
 * @Date 01/03/2017.
 */

public class DatabaseProvider extends ContentProvider {

    private AlbumDatabaseHelper albumHelper;
    private UriMatcher uriMatcher;
    private final int ALBUMS_CODE = 1;
    private final int ALBUM_DETAIL_CODE = 2;

    /**
     * It's possible to call the database through this ContentProvider
     * URI :  /album and /album/album_id
     * @return
     */
    @Override
    public boolean onCreate() {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AlbumDatabaseContract.CONTENT_PROVIDER_AUTHORITY, AlbumDatabaseContract.ALBUM_TABLE, ALBUMS_CODE);
        uriMatcher.addURI(AlbumDatabaseContract.CONTENT_PROVIDER_AUTHORITY, AlbumDatabaseContract.ALBUM_DETAIL_TABLE, ALBUM_DETAIL_CODE);
        return true;
    }

    // TODO
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        if (uriMatcher.match(uri) == ALBUMS_CODE) {
            return AlbumDatabaseContract.ALBUMS_CONTENT_TYPE;
        } else if (uriMatcher.match(uri) == ALBUM_DETAIL_CODE) {
            return AlbumDatabaseContract.ALBUM_DETAIL_CONTENT_TYPE;
        }
        throw new IllegalArgumentException("Unknow URI : " + uri);
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = albumHelper.getWritableDatabase();
        final long rowId = db.insert(AlbumDatabaseContract.ALBUM_TABLE, "", values);
        if (rowId > 0) {
            final Uri applicationUri = ContentUris.withAppendedId(AlbumDatabaseContract.ALBUM_META_URI, rowId);
            getContext().getContentResolver().notifyChange(applicationUri, null);
            return applicationUri;
        }
        throw new SQLException("Failed to add a new album into " + uri);
    }

    // TODO
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    // TODO
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

}
