package com.example.android.musicalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        TextView arabicSongs = findViewById( R.id.Arabic );
        arabicSongs.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent arabicIntent = new Intent( MainActivity.this, ArabicSongs.class );
                startActivity( arabicIntent );
            }
        } );
        //find the view to shows the english category
        TextView englishSongs = findViewById( R.id.English );
        englishSongs.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent englishIntent = new Intent( MainActivity.this, EnglishSongs.class );
                startActivity( englishIntent );
            }
        } );
    }
}
