package com.pez.audio_player_application;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by Antoine on 02/03/2017.
 */

public class PlayActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Button myButton =  (Button) findViewById(R.id.buttonTest);
        myButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View arg0) {
                try{
                    String url = "https://dl.dropboxusercontent.com/u/69344277/09%20Celebration.mp3"; // your URL here
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setDataSource(url);
                    mediaPlayer.prepare(); // might take long! (for buffering, etc)
                    mediaPlayer.start();
                }
                catch(IOException e){

                }

            }
        });
    }
}
