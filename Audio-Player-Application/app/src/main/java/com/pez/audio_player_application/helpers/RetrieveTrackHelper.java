package com.pez.audio_player_application.helpers;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.pez.audio_player_application.AudioPlayerApplication;
import com.pez.audio_player_application.pojo.Track;

import java.util.ArrayList;

//__________________________________________________________________________

/**
 * @author nicolas
 * @date 28/02/17.
 */

public class RetrieveTrackHelper {

    public static ArrayList<Track> getSongList() {
        ArrayList<Track> resultSongList = new ArrayList<Track>();

        //retrieve song info
        ContentResolver musicResolver = AudioPlayerApplication.getContext().getContentResolver();
        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor trackCursor = musicResolver.query(musicUri, null, null, null, null);

        if (trackCursor != null && trackCursor.moveToFirst()) {
            //get columns indexes
            int pathColumn = trackCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            int titleColumn = trackCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int artistColumn = trackCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int albumColumn = trackCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
            int durationColumn = trackCursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
            //add songs to list
            do {
                resultSongList.add(new Track(trackCursor.getString(pathColumn),
                        trackCursor.getString(titleColumn),
                        trackCursor.getString(artistColumn),
                        trackCursor.getString(albumColumn),
                        trackCursor.getInt(durationColumn)));
            }
            while (trackCursor.moveToNext());
        }

        return resultSongList;
    }


}
