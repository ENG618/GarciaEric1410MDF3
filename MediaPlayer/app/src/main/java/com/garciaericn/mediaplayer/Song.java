package com.garciaericn.mediaplayer;

import android.graphics.Bitmap;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/6/14.
 */
public class Song {

    private String songString;
    private String songTitle;
    private String songAuthor;
    private int albumArtResourse;
    private Bitmap albumArt;

    public Song(String songURI, String songTitle, String songAuthor, int albumArtResourse, Bitmap albumArt) {
        this.songString = songURI;
        this.songTitle = songTitle;
        this.songAuthor = songAuthor;
        this.albumArtResourse = albumArtResourse;
        this.albumArt = albumArt;
    }

    /**
     * Getter and setter methods
     * */

    public String getSongString() {
        return songString;
    }

    public void setSongString(String songString) {
        this.songString = songString;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongAuthor() {
        return songAuthor;
    }

    public void setSongAuthor(String songAuthor) {
        this.songAuthor = songAuthor;
    }

    public Bitmap getAlbumArt() {
        return albumArt;
    }

    public int getAlbumArtResourse() {
        return albumArtResourse;
    }

    public void setAlbumArtResourse(int albumArtResourse) {
        this.albumArtResourse = albumArtResourse;
    }

    public void setAlbumArt(Bitmap albumArt) {
        this.albumArt = albumArt;
    }
}
