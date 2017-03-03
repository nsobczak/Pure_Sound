package com.pez.audio_player_application;

import android.app.Application;
import android.content.Context;

/**
 * @Auteur Baudouin
 * @Date 03/03/2017.
 */

public class AudioPlayerApplication extends Application {
    private static Context sContext;

    // To have static access to the context in the application
    public static Context getContext() {
        return sContext;
    }

    public void onCreate() {
        super.onCreate();

        sContext = getApplicationContext();
    }

}
