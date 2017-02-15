package com.pez.audio_player_application.helpers;

import com.pez.audio_player_application.pojo.Album;
import com.pez.audio_player_application.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Auteur Baudouin
 * @Date 13/02/2017.
 */

public class MetadataSongHelper {

    /* TODO
      1) Récupérer tous les albums à partir du nom d'un artiste
      2) Si le nom d'un album est mal orthographié (album non trouvé par exemple) : étape (1)
         Puis on essaye de trouver l'album le plus proche de celui qui est passé en paramètre
      3) Récupérer le nom des pistes d'un album

     */
    public static Album getAlbumInfo(String albumName, String artistName) {
        // Create the HTTP Get request to Lastfm API
        String url = Constants.API_BASE_URL + "&api_key=" + Constants.API_KEY_LASTFM + "&artist=" +
                artistName + "&album=" + albumName + "&format=json";
        final HttpURLConnection connection = getRequest(url);
        final int responseCode;
        String result = "";
        try {

            if (connection != null) {
                responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    InputStreamReader stream = new InputStreamReader(connection.getInputStream(), "UTF-8");
                    if(stream != null) {
                        BufferedReader reader = new BufferedReader(stream, 8);
                        StringBuilder sb = new StringBuilder();

                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            sb.append(line + "\n");
                        }
                        result = sb.toString();
                        try {
                            JSONObject object = new JSONObject(result);
                            JSONObject album = object.getJSONObject("album");
                            return new Album(album.getString("artist"), album.getString("name"), album.getString("mbid"), album.getString("url"), null);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return null;
        }
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
