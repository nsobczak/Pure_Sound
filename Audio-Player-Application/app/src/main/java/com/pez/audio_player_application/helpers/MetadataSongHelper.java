package com.pez.audio_player_application.helpers;

import com.google.gson.Gson;
import com.pez.audio_player_application.pojo.Album;
import com.pez.audio_player_application.utils.Constants;

import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static android.R.attr.id;

/**
 * @Auteur Baudouin
 * @Date 13/02/2017.
 */

public class MetadataSongHelper {

    // TODO : A Debugger
    public static Album getAlbumInfo(String albumName, String artistName) {
        // Create the HTTP Get request to Lastfm API
        String url = Constants.API_BASE_URL + "&api_key=" + Constants.API_KEY_LASTFM + "&artist=" +
                artistName + "&album=" + albumName + "&format=json";
        final HttpURLConnection connection = getRequest(url);
        final int responseCode;
        try {

            if (connection != null) {
                responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    InputStreamReader stream = new InputStreamReader(connection.getInputStream(), "UTF-8");
                    if(stream != null) {
                        Album album = new Gson().fromJson(new JsonReader(stream), Album.class);
                        return album;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static HttpURLConnection getRequest(String url) {
        try {
            final HttpURLConnection connection;
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(1000);
            connection.setDoOutput(false); // GET Request

            return connection;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void getAlbumCover(String albumTitle, String artistName) {

    }

}
