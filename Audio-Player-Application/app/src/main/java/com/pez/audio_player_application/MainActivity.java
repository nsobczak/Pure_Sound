package com.pez.audio_player_application;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;


//__________________________________________________________________________

/**
 * MainActivity : activity_01
 */
public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab_songPlay = (FloatingActionButton) findViewById(R.id.fab_songPlay);
        fab_songPlay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Play song", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });

        FloatingActionButton fab_songNext = (FloatingActionButton) findViewById(R.id.fab_songNext);
        fab_songNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Next song", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        FloatingActionButton fab_songPause = (FloatingActionButton) findViewById(R.id.fab_songPause);
        fab_songPause.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "On pause", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        FloatingActionButton fab_songPrevious = (FloatingActionButton) findViewById(R.id.fab_songPrevious);
        fab_songPrevious.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
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
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.audio_player_main, menu);
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
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        //handle here : Share action
        if (id == R.id.actionShare)
        {
            return true;
        }

        //handle here : Kill action
        if (id == R.id.actionKill)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
