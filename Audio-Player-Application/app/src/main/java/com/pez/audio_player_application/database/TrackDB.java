package com.pez.audio_player_application.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * @Auteur Baudouin
 * @Date 26/02/2017.
 */

public class TrackDB {

    protected SQLiteDatabase database;
    private AlbumDatabaseHelper dbHelper;
    private Context mContext;

    public TrackDB(Context context) {
        this.mContext = context;
        dbHelper = AlbumDatabaseHelper.getHelper(mContext);
        open();
    }

    public void open() throws SQLException {
        if(dbHelper == null)
            dbHelper = AlbumDatabaseHelper.getHelper(mContext);
        database = dbHelper.getWritableDatabase();
    }

}
