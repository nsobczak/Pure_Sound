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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pez.audio_player_application.pojo.Queue;
import com.pez.audio_player_application.pojo.Track;
import com.pez.audio_player_application.utils.TimeUtilities;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class PlayActivity extends Activity {

    boolean changeFromUser = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        final MediaPlayer mediaPlayer = MainActivity.getMediaPlayer();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                if (mp.getCurrentPosition() != 0) {
                    songNext();
                }
            }
        });
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

        FloatingActionButton buttonPlayPause = (FloatingActionButton) findViewById(R.id.button_PlayPause);
        buttonPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                songPlayPause();
            }
        });

        FloatingActionButton buttonPrevious = (FloatingActionButton) findViewById(R.id.button_Previous);
        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                songPrevious();
            }
        });

        FloatingActionButton buttonNext = (FloatingActionButton) findViewById(R.id.button_Next);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                songNext();
            }
        });

        SeekBar seekBarSong = (SeekBar) findViewById(R.id.seekBarSong);
        seekBarSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo((mediaPlayer.getDuration() * progress) / 100);
                    String progressStr = TimeUtilities.milliSecondsToString(mediaPlayer.getCurrentPosition());
                    String durationStr = TimeUtilities.milliSecondsToString(mediaPlayer.getDuration());
                    TextView textView = (TextView) findViewById(R.id.textViewProgressSong);
                    textView.setText(progressStr + " / " + durationStr);
                }
            }
        });

        readQueue();

        MPProgressUpdater timerTask = new MPProgressUpdater(mediaPlayer, seekBarSong, (TextView) findViewById(R.id.textViewProgressSong));
        new Timer().scheduleAtFixedRate(timerTask, 0, 500);
    }

    class MPProgressUpdater extends TimerTask {
        private MediaPlayer mp;
        private SeekBar seekBar;
        private TextView textView;

        public MPProgressUpdater(MediaPlayer mp, SeekBar seekBar, TextView textView) {
            this.mp = mp;
            this.seekBar = seekBar;
            this.textView = textView;
        }

        @Override
        public void run() {
            if (mp.isPlaying()) {
                final SeekBar seekBar = this.seekBar;
                final TextView textView = this.textView;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String progress = TimeUtilities.milliSecondsToString(mp.getCurrentPosition());
                        String duration = TimeUtilities.milliSecondsToString(mp.getDuration());
                        seekBar.setProgress(100 * mp.getCurrentPosition() / mp.getDuration());
                        textView.setText(progress + " / " + duration);
                    }
                });
            }
        }
    }

    private void readQueue() {
        Queue playQueue = MainActivity.getPlayQueue();
        Track trackToRead = playQueue.getCurrentTrack();
        if (trackToRead != null) {
            try {
                // On initialise la seekbar
                SeekBar seekBarSong = (SeekBar) findViewById(R.id.seekBarSong);
                seekBarSong.setProgress(0);

                // Lecture
                String url = trackToRead.getPath();
                MediaPlayer mediaPlayer = MainActivity.getMediaPlayer();
                mediaPlayer.reset();
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepareAsync();

                FloatingActionButton buttonPlayPause = (FloatingActionButton) findViewById(R.id.button_PlayPause);
                buttonPlayPause.setImageResource(android.R.drawable.ic_media_pause);

                TextView textViewtTitre = (TextView) findViewById(R.id.textViewTitre);
                textViewtTitre.setText(trackToRead.getName());

                TextView textViewtArtisteAlbum = (TextView) findViewById(R.id.textViewArtisteAlbum);
                textViewtArtisteAlbum.setText(trackToRead.getArtist() + " - " + trackToRead.getAlbum());
            } catch (IOException e) {
            }
        }
    }

    private void songPlayPause() {
        MediaPlayer mediaPlayer = MainActivity.getMediaPlayer();
        if (mediaPlayer != null) {
            FloatingActionButton buttonPlayPause = (FloatingActionButton) findViewById(R.id.button_PlayPause);
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                buttonPlayPause.setImageResource(android.R.drawable.ic_media_play);
            } else {
                mediaPlayer.start();
                buttonPlayPause.setImageResource(android.R.drawable.ic_media_pause);
            }
        }
    }

    private void songPrevious() {
        Queue playQueue = MainActivity.getPlayQueue();
        playQueue.previousTrack();
        readQueue();
    }

    private void songNext() {
        Queue playQueue = MainActivity.getPlayQueue();
        if (playQueue.getCurrentTrackIndex() + 1 < playQueue.getTracks().size()) {
            playQueue.nextTrack();
            readQueue();
        } else {
            // Dernière chanson de la file
            FloatingActionButton buttonPlayPause = (FloatingActionButton) findViewById(R.id.button_PlayPause);
            buttonPlayPause.setImageResource(android.R.drawable.ic_media_play);
        }
    }
}
