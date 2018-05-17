package com.example.android.musicalapp;

import android.content.Context;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

public class EnglishSongs extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private AudioManager mAudioManager;
    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mediaPlayer.pause();



            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.songs_item );
        mAudioManager = (AudioManager) getSystemService( Context.AUDIO_SERVICE );
        // Create a list of words
        final ArrayList<Songs> words;
        words = new ArrayList<>();
        words.add( new Songs( "addicted", "Enrique Iglesias", R.drawable.images, R.raw.addicted ) );
        words.add( new Songs( "Broken Angel", "Arash", R.drawable.images, R.raw.brokenangel ) );
        words.add( new Songs( "Panda", "Desiinger", R.drawable.images, R.raw.desiigner ) );
        words.add( new Songs( "Don't Stop the Party", "Pitbull", R.drawable.images, R.raw.dontstop ) );
        words.add( new Songs( "Mixed", "Dj", R.drawable.images, R.raw.excited ) );
        words.add( new Songs( "Follow The Leader", "Wisin_Yandel ft.Jennifer", R.drawable.images, R.raw.follow ) );
        words.add( new Songs( "From Sarah With Love", "sarah connor", R.drawable.images, R.raw.fromsarahwithlove ) );
        words.add( new Songs( "Hello", "Lionel Richie", R.drawable.images, R.raw.hello ) );
        words.add( new Songs( "Here I Am", "Bryan Adams", R.drawable.images, R.raw.hereiam ) );
        words.add( new Songs( "Hero", "Enrique Iglesias", R.drawable.images, R.raw.hero ) );
        words.add( new Songs( "Lady", "Kenny Rogers", R.drawable.images, R.raw.lady ) );
        words.add( new Songs( "My Body", "Nayer Ft. Pitbull & Mohombi", R.drawable.images, R.raw.mybody ) );
        words.add( new Songs( "Kiss me", "Naya", R.drawable.images, R.raw.kissme ) );
        words.add( new Songs( "NO Promises", "Shayne Ward", R.drawable.images, R.raw.nopromise ) );
        words.add( new Songs( "Show Me The Meaning", "Back Street Boys", R.drawable.images, R.raw.showmethe ) );
        words.add( new Songs( "Some Body Is Me", "Enrique Iglesias", R.drawable.images, R.raw.somebodys ) );
        words.add( new Songs( "My Heart Beating For You", "Inna", R.drawable.images, R.raw.yalla ) );
        // Create an {@link SongsAdapter}, whose data source is a list of {@link Songs}s. The
        // adapter knows how to create list items for each item in the list.
        SongsAdapter adapter = new SongsAdapter( this, words, R.color.category_english );

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // songs_item.xml layout file.
        ListView listView = findViewById( R.id.list );

        // Make the {@link ListView} use the {@link SongsAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Songs} in the list.
        listView.setAdapter( adapter );
        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Songs word = words.get( position );
                releaseMediaPlayer();
                int result = mAudioManager.requestAudioFocus( mOnAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT );
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
                    mediaPlayer = MediaPlayer.create( EnglishSongs.this, word.getAudioResourceId() );
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener( mCompletionListener );
            }
        } );
    }


    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;

            mAudioManager.abandonAudioFocus( mOnAudioFocusChangeListener );
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseMediaPlayer();
    }

}
