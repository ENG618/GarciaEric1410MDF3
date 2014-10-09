package com.garciaericn.mediaplayer;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/6/14.
 */
public class Song {

    private String songString;
    private String songTitle;
    private String songAuthor;

    public Song(String songURI, String songTitle, String songAuthor) {
        this.songString = songURI;
        this.songTitle = songTitle;
        this.songAuthor = songAuthor;
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
}
