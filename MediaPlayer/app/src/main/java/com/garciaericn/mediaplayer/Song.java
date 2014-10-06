package com.garciaericn.mediaplayer;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/6/14.
 */
public class Song {

    private String songURI;
    private String songTitle;
    private String songAuthor;

    public Song(String songURI, String songTitle, String songAuthor) {
        this.songURI = songURI;
        this.songTitle = songTitle;
        this.songAuthor = songAuthor;
    }

    // Getter and setter methods


    public String getSongURI() {
        return songURI;
    }

    public void setSongURI(String songURI) {
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
