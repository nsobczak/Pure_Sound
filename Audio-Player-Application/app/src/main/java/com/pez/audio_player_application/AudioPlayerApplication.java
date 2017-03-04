package com.pez.audio_player_application;

import android.app.Application;
import android.content.Context;

//__________________________________________________________________________

/**
 * @author nicolas
 * @date 26/02/17.
 * AudioPlayerApplication
 */
public class AudioPlayerApplication extends Application
{
    private static Context sContext;

    public void onCreate()
    {
        super.onCreate();

        // Keep a reference to the application context
        sContext = getApplicationContext();
    }


    // Used to access Context anywhere within the app
    public static Context getContext()
    {
        return sContext;
    }


}
