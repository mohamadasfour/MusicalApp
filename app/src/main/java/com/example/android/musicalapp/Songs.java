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
    /**
     * music resources
     */

    private int mAudioResourceId;
    /**
     * Images resources
     */

    private int mImageResourceId;


    /**
     * Create a new Word object.
     *
     * @param songName   the name of the songs
     *                   (such as addicted)
     * @param artistName is the name of artist who sing that song
     */
    public Songs(String songName, String artistName, int imageResourceId, int audioResourceId) {
        mSongName = songName;
        mArtistName = artistName;
        mAudioResourceId = audioResourceId;
        mImageResourceId = imageResourceId;
    }

    public String getSongsName() {
        return mSongName;
    }


    public String getArtistName() {
        return mArtistName;
    }


    public int getImageResourceId() {
        return mImageResourceId;
    }


    public int getAudioResourceId() {
        return mAudioResourceId;
    }
}
