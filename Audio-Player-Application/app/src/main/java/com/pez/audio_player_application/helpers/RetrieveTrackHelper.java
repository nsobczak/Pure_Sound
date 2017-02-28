package com.pez.audio_player_application.helpers;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.pez.audio_player_application.pojo.Track;

import java.util.ArrayList;

/**
 * Created by nicolas on 28/02/17.
 */

public class RetrieveTrackHelper
{

    public ArrayList<Track> getSongList()
    {
        ArrayList<Track> resultSongList = new ArrayList<Track>();

//        //retrieve song info
//        ContentResolver musicResolver = getContentResolver();
//        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//        Cursor trackCursor = musicResolver.query(musicUri, null, null, null, null);
//
//        if (trackCursor != null && trackCursor.moveToFirst())
//        {
//            //get columns indexes
//            int titleColumn = trackCursor.getColumnIndex(android.provider.MediaStore.Audio.Media.TITLE);
//            int durationColumn = trackCursor.getColumnIndex(android.provider.MediaStore.Audio.Media.DURATION);
//            //add songs to list
//            do
//            {
//                resultSongList.add(new Track(trackCursor.getString(titleColumn), trackCursor.getInt(durationColumn)));
//            }
//            while (trackCursor.moveToNext());
//        }
//
////        Toast.makeText(AudioPlayerApplication.getContext(), "Tracks retrieved !", Toast.LENGTH_SHORT).show();

        return resultSongList;

    }


}
