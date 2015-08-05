package com.novopay.sachinmusicapp.model;

/**
 * Created by sachintyagi on 8/4/15.
 */
public class Music {
    private String album;
    private  String artist;
    private  String song;
    private String image;

    public Music(String album, String artist, String song, String image) {
        this.album = album;
        this.artist = artist;
        this.song = song;
        this.image = image;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
