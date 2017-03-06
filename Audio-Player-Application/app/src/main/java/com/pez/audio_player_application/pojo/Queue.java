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

    public Track getCurrentTrack() {
        if (currentTrackIndex > -1 && this.tracks.size() > currentTrackIndex) {
            return this.tracks.get(currentTrackIndex);
        } else {
            return null;
        }
    }

    public void setCurrentTrackIndex(int currentTrackIndex) {
        this.currentTrackIndex = currentTrackIndex;
    }

    public void previousTrack() {
        if (this.currentTrackIndex - 1 > -1) {
            this.currentTrackIndex -= 1;
        }
    }

    public void nextTrack() {
        if (this.currentTrackIndex + 1 < this.tracks.size()) {
            this.currentTrackIndex += 1;
        }
    }

    public ArrayList<Track> getTracks() {
        return this.tracks;
    }

    public int getCurrentTrackIndex() {
        return this.currentTrackIndex;
    }
}
