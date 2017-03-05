package com.pez.audio_player_application.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.provider.BaseColumns;
import android.support.annotation.RequiresApi;

/**
 * @Auteur Baudouin
 * @Date 26/02/2017.
 */

public class AlbumDatabaseHelper extends SQLiteOpenHelper implements BaseColumns {

    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "AlbumsInfo.db";

    public AlbumDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static AlbumDatabaseHelper instance;

    public static synchronized AlbumDatabaseHelper getHelper(Context context) {
        if (instance == null)
            instance = new AlbumDatabaseHelper(context);
        return instance;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(AlbumDatabaseContract.CREATE_TABLE_ALBUMS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + AlbumDatabaseContract.ALBUM_TABLE);
        onCreate(db);
    }
}
