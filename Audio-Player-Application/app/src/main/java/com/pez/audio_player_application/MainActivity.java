package com.pez.audio_player_application;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.pez.audio_player_application.interfaces.TrackListener;
import com.pez.audio_player_application.pojo.Queue;
import com.pez.audio_player_application.pojo.Track;

//__________________________________________________________________________

/**
 * MainActivity : activity_01
 */
public class MainActivity extends AppCompatActivity implements TrackListener {

    private static Queue playQueue = new Queue();
    private static MediaPlayer mediaPlayer = new MediaPlayer();

    public static Queue getPlayQueue() {
        return playQueue;
    }

    public static MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    //__________________________________________________________________________
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Gestion des permissions pour pouvoir accéder aux chansons de la carte SD (l'ajout dan le manifest ne suffit pas)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return;
            }
        }

    }


    /**
     * Inflate the menu; this adds items to the action bar if it is present.
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.audio_player_main_menu, menu);
        return true;
    }


    /**
     * Handle action bar item clicks here.
     * The action bar will automatically handle clicks on the Home/Up button, so long as you specify a parent activity in AndroidManifest.xml.
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //handle here : Share action
        if (id == R.id.actionShare) {
            Toast.makeText(AudioPlayerApplication.getContext(), "Share listened song", Toast.LENGTH_SHORT).show();
            Intent shareIntent = new Intent(Intent.ACTION_SEND);

            //Share Text
            // shareIntent.setType("text/plain");

            //Share Image and text
            shareIntent.setType("image/jpeg");
            startActivity(Intent.createChooser(shareIntent, "Share"));
            return true;
        }

        //handle here : Kill action
        if (id == R.id.actionKill)
        {
            Toast.makeText(AudioPlayerApplication.getContext(), "Close application", Toast.LENGTH_SHORT).show();
            finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    //__________________________________________________________________________
    @Override
    public void onViewTrack(Track track) {
        if (playQueue.getTracks().contains(track)) {
            playMusic(playQueue.getTracks().indexOf(track));
        } else {
            playQueue.addTracks(track);
            playMusic();
        }

    }

    public void playMusic(int position) {
        playQueue.setCurrentTrackIndex(position);
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }

    public void playMusic() {
        playMusic(playQueue.getTracks().size() - 1);
    }


}
