package com.pez.audio_player_application.async;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.pez.audio_player_application.AudioPlayerApplication;
import com.pez.audio_player_application.pojo.Track;

import java.util.List;


//__________________________________________________________________________

/**
 * @author nicolas
 * @date 28/02/17
 */
public class RetrieveTracksAsyncTask extends AsyncTask<Track, Void, List<Track>>
{
    @Override
    protected List<Track> doInBackground(Track... tracks)
    {
        {
            List<Track> result = null;
            if (tracks != null)
            {
//                result = TwitterHelper.getTweetsOfUser(strings[0]);
            }

            return result;
        }
    }


    @Override
    protected void onPostExecute(List<Track> trackList)
    {
//        super.onPostExecute(trackList);
//
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
//                this.songList.add(new Track(trackCursor.getString(titleColumn), trackCursor.getInt(durationColumn)));
//            }
//            while (trackCursor.moveToNext());
//        }

        Toast.makeText(AudioPlayerApplication.getContext(), "Tracks retrieved !", Toast.LENGTH_SHORT).show();
    }


}
