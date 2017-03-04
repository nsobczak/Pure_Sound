package com.pez.audio_player_application;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.pez.audio_player_application.adapters.TracksAdapter;
import com.pez.audio_player_application.interfaces.TrackListener;
import com.pez.audio_player_application.pojo.Album;
import com.pez.audio_player_application.pojo.Queue;
import com.pez.audio_player_application.pojo.Track;
import com.pez.audio_player_application.ui.fragments.DownloadAlbumInfo;
import com.pez.audio_player_application.ui.fragments.MainActivityFragmentSongs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


//__________________________________________________________________________

/**
 * MainActivity : activity_01
 * TODO: Faire un lien vers la 2ème activité quand on clique sur une chanson
 * TODO: ajouter la liste des chansons
 * TODO: lier les 3 fragments
 */
public class MainActivity extends AppCompatActivity implements TrackListener {
    private DownloadAlbumInfo downloadAlbumInfo;
    private ArrayList<Track> songList;
    private ListView songView;
    private static Queue playQueue = new Queue();
    private static MediaPlayer mediaPlayer = new MediaPlayer();

    public static Queue getPlayQueue(){
        return playQueue;
    }
    public static MediaPlayer getMediaPlayer(){
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

        /* Gestion du fragment */
        //Create fragment transaction
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //Add the fragment
        MainActivityFragmentSongs fragment = new MainActivityFragmentSongs();
        fragmentTransaction.add(R.id.layoutMainActivityContainer, fragment);
        fragmentTransaction.commit();


        //Lecture des chansons existantes
        this.songView = (ListView) findViewById(R.id.songsListLayout);

        // TODO : Récupérer les noms des fichiers
        this.songList = new ArrayList<Track>();
        getSongList();

        //trie des chansons par ordre alphabétique
        Collections.sort(songList, new Comparator<Track>() {
            public int compare(Track trackA, Track trackB) {
                return trackA.getName().compareTo(trackB.getName());
            }
        });

        TracksAdapter tracksAdapter = new TracksAdapter(this.songList);
        this.songView.setAdapter(tracksAdapter);


        //Metadonnees
        downloadAlbumInfo = new DownloadAlbumInfo();
        // TODO : Faire le retrieve APRES avoir récupéré les noms des fichiers
        downloadAlbumInfo.retrieveAlbumsInfo(
                new Album("Radiohead", "ok computer"),
                new Album("Beck", "the information")
        );


        // === Gestion des boutons ===
        FloatingActionButton fab_songPlay = (FloatingActionButton) findViewById(R.id.fab_songPlay);
        fab_songPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Play song", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show(); playQueue(0);
            }
        });

        FloatingActionButton fab_songNext = (FloatingActionButton) findViewById(R.id.fab_songNext);
        fab_songNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Next song", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        FloatingActionButton fab_songPause = (FloatingActionButton) findViewById(R.id.fab_songPause);
        fab_songPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "On pause", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        FloatingActionButton fab_songPrevious = (FloatingActionButton) findViewById(R.id.fab_songPrevious);
        fab_songPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Previous song", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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
            return true;
        }

        //handle here : Kill action
        if (id == R.id.actionKill) {
            Toast.makeText(AudioPlayerApplication.getContext(), "Kill application", Toast.LENGTH_SHORT).show();
            finish();
            return true;
        }

        //handle here : Synchronize database action
        if (id == R.id.actionSyncDB) {
            Toast.makeText(AudioPlayerApplication.getContext(), "Sync database", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //__________________________________________________________________________

    public void getSongList() {
        //retrieve song info
        ContentResolver musicResolver = getContentResolver();
        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor trackCursor = musicResolver.query(musicUri, null, null, null, null);

        if (trackCursor != null && trackCursor.moveToFirst()) {

            //get columns indexes
            int pathColumn = trackCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            int titleColumn = trackCursor.getColumnIndex(android.provider.MediaStore.Audio.Media.TITLE);
            int durationColumn = trackCursor.getColumnIndex(android.provider.MediaStore.Audio.Media.DURATION);
            //add songs to list
            do {
                this.songList.add(new Track(trackCursor.getString(pathColumn), trackCursor.getString(titleColumn), trackCursor.getInt(durationColumn)));
            }
            while (trackCursor.moveToNext());
        }

//        Toast.makeText(AudioPlayerApplication.getContext(), "Tracks retrieved !", Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onViewTrack(Track track) {
        //TODO: lancer la 2ème activité sans lancer la chanson
        Toast.makeText(AudioPlayerApplication.getContext(), "Lancement de la 2ème activité avec la chanson : " +
                track.getName(), Toast.LENGTH_LONG).show();

    }

    public void playQueue(int position) {
        playQueue.setCurrentTrackIndex(position);
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }


}
