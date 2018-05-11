package com.example.android.musicalapp;

public class Songs {
    /**
     * Arabic songs names
     */
    private String mSongName;
    /**
     * English songs names
     */
    private String mArtistName;

    private int mAudioResourceId;

    private int mImageResourceId;


    /**
     * Create a new Word object.
     *
     * @param songName   the word in a language that the user is already familiar with
     *                   (such as English)
     * @param artistName is the word in the Miwok language
     */
    public Songs(String songName, String artistName, int audioResourceId, int imageResourceId) {
        mSongName = songName;
        mArtistName = artistName;
        mAudioResourceId = audioResourceId;
        mImageResourceId = imageResourceId;
    }

    public String getArabicSongs() {
        return mSongName;
    }


    public String getEnglishSongs() {
        return mArtistName;
    }


    public int getImageResourceId() {
        return mImageResourceId;
    }


    public int getAudioResourceId() {
        return mAudioResourceId;
    }
}
