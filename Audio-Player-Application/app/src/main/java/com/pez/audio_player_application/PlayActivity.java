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
                    String url = "/storage/extSdCard/Download/apsco-1.mp3"; // your URL here
                    MediaPlayer mediaPlayer = MainActivity.getMediaPlayer();
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
