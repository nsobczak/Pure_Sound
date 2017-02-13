package com.pez.audio_player_application.helpers;

import android.graphics.Bitmap;
import android.util.JsonReader;

import com.pez.audio_player_application.utils.Constants;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * @Auteur Baudouin
 * @Date 13/02/2017.
 */

public class MetadataSongHelper {

    public static void getAlbumInfo(String albumName, String artistName) {
        // Create the HTTP Get request to Lastfm API
        String url = Constants.API_BASE_URL + "&api_key=" + Constants.API_KEY_LASTFM + "&artist=" +
                artistName + "&album=" + albumName + "&format=json";
        final HttpURLConnection connection;

        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(1000);
            connection.setDoInput(false);
            connection.setDoOutput(true);
            final int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                // Read the response, and build the TwitterAuthenticated object
                InputStream stream = connection.getInputStream();
               // new JsonReader(new InputStreamReader(connection.getInputStream(), "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getAlbumCover(String albumTitle, String artistName) {
        JSONObject jsonAlbum = new JSONObject();


    }

}
