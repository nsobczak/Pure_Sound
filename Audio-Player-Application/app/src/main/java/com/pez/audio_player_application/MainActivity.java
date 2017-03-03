package com.pez.audio_player_application;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.pez.audio_player_application.pojo.Album;
import com.pez.audio_player_application.ui.fragments.DownloadAlbumInfo;

//__________________________________________________________________________

/**
 * MainActivity : activity_01
 * TODO: Faire un lien vers la 2ème activité quand on clique sur une chanson
 * TODO: ajouter la liste des chansons
 * TODO: lier les 3 fragments
 */

public class MainActivity extends AppCompatActivity {
    private DownloadAlbumInfo downloadAlbumInfo;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        downloadAlbumInfo = new DownloadAlbumInfo();
        // TODO : Faire le retrieve APRES avoir récupéré les noms des fichiers
        downloadAlbumInfo.retrieveAlbumsInfo(
                new Album("Radiohead", "ok computer"),
                new Album("Beck", "the information")
        );


        FloatingActionButton fab_songPlay = (FloatingActionButton) findViewById(R.id.fab_songPlay);
        fab_songPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Play song", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
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

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
            return true;
        }

        //handle here : Kill action
        if (id == R.id.actionKill) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
