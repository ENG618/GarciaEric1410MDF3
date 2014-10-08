package com.garciaericn.mediaplayer;

import android.net.Uri;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/6/14.
 */
public class Song {

    private Uri songURI;
    private String songTitle;
    private String songAuthor;

    public Song(Uri songURI, String songTitle, String songAuthor) {
        this.songURI = songURI;
        this.songTitle = songTitle;
        this.songAuthor = songAuthor;
    }

    // Getter and setter methods


    public Uri getSongURI() {
        return songURI;
    }

    public void setSongURI(Uri songURI) {
        this.songURI = songURI;
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
}
