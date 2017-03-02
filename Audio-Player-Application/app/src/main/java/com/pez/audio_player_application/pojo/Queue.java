package com.pez.audio_player_application.pojo;

import java.util.ArrayList;
import java.util.List;

public class Queue {
    private ArrayList<Track> tracks;
    private int currentTrackIndex;

    public Queue() {
        this.tracks = new ArrayList<Track>();
        this.currentTrackIndex = 0;
    }

    public void addTracks(Track track) {
        this.tracks.add(track);
    }

    public void addTracks(Album album) {
        for (Track track : album.getTracks()) {
            this.tracks.add(track);
        }
    }

    public ArrayList<Track> getTracks() {
        return this.tracks;
    }
}
