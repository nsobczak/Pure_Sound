package com.pez.audio_player_application.helpers;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.net.HttpURLConnection;
import java.net.URL;

public class ImageHelper {

    public static Bitmap getImageFromURL(String imageUrl) throws Exception {
        final HttpURLConnection connection = getHTTPUrlConnection(imageUrl);
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        final int responseCode = connection.getResponseCode();

        // If success
        if (responseCode == 200) {
            final Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            return bitmap;
        }
        return null;
    }

    private static HttpURLConnection getHTTPUrlConnection(String url) throws Exception {
        return (HttpURLConnection) new URL(url).openConnection();
    }
}

