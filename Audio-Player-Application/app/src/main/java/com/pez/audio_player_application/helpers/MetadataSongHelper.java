package com.pez.audio_player_application.helpers;

import com.pez.audio_player_application.pojo.Album;
import com.pez.audio_player_application.pojo.Track;
import com.pez.audio_player_application.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auteur Baudouin
 * @Date 13/02/2017.
 */

public class MetadataSongHelper {

    public static List<Album> getAlbumsFromArtist(String artistName) {
        String url = Constants.API_BASE_URL + "?method=artist.gettopalbums&artist=" + artistName +
                "&api_key=" + Constants.API_KEY_LASTFM + "&format=json";
        ArrayList<Album> albums = new ArrayList<>();

        final HttpURLConnection connection = getRequest(url);

        try {
            if (connection != null) {
                final int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    String result = sb.toString();
                    try {
                        JSONObject object = new JSONObject(result);
                        if(object.has("topalbums")) {
                            JSONArray list_album = object.getJSONObject("topalbums").getJSONArray("album");
                            for (int i = 0; i < list_album.length(); i++) {
                                JSONObject current_album = list_album.getJSONObject(i);
                                albums.add(new Album(current_album));
                            }
                        }
                        return albums;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Track getAlbumTrackInfo(Track track) {
        // Create the HTTP Get request to Lastfm API
        String url = Constants.API_BASE_URL + "?method=track.getinfo&api_key=" + Constants.API_KEY_LASTFM + "&artist=" +
                track.getArtist() + "&track=" + track.getName() + "&format=json";
        final HttpURLConnection connection = getRequest(url);
        try {
            if (connection != null) {
                final int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    String result = sb.toString();
                    try {
                        JSONObject object = new JSONObject(result);
                        if(object.has("track")) {
                            JSONObject trackinfo = new JSONObject(result).getJSONObject("track");
                            JSONObject album = new JSONObject(trackinfo.getString("album"));
                            track.setAlbum(album.getString("title"));
                            JSONArray images = new JSONArray(album.getString("image"));
                            track.setCover_url(((JSONObject)images.get(images.length() - 1)).getString("#text"));
                            return track;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Album getAlbumInfo(String albumName, String artistName) {
        // Create the HTTP Get request to Lastfm API
        String url = Constants.API_BASE_URL + "?method=album.getinfo&api_key=" + Constants.API_KEY_LASTFM + "&artist=" +
                artistName + "&album=" + albumName + "&format=json";
        final HttpURLConnection connection = getRequest(url);
        try {
            if (connection != null) {
                final int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    String result = sb.toString();
                    try {
                        JSONObject object = new JSONObject(result);
                        if(object.has("album"))
                            return new Album(new JSONObject(result).getJSONObject("album")); // album.getString("artist"), album.getString("name"), album.getString("mbid"), album.getString("url"), null);
                    } catch (JSONException e) {
                        e.printStackTrace();
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
}
