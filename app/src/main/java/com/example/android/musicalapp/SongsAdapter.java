package com.example.android.musicalapp;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SongsAdapter extends ArrayAdapter<Songs> {

    private int mColorResourceId;

    /**
     * Create a new {@link SongsAdapter} object.
     *
     * @param context is the current context (i.e. Activity) that the adapter is being created in.
     * @param words   is the list of {@link Songs}s to be displayed.
     */
    public SongsAdapter(Context context, ArrayList<Songs> words, int colorResourceId) {
        super( context, 0, words );
        mColorResourceId = colorResourceId;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from( getContext() ).inflate(
                    R.layout.list_item, parent, false );
        }

        // Get the {@link Word} object located at this position in the list
        Songs currentWord = getItem( position );

        // Find the TextView in the list_item.xml layout with the ID miwok_text_view.
        TextView songTextView = listItemView.findViewById( R.id.name );
        // Get the Miwok translation from the currentWord object and set this text on
        // the Miwok TextView.
        songTextView.setText( currentWord.getArabicSongs() );

        // Find the TextView in the list_item.xml layout with the ID default_text_view.
        TextView secondTextView = listItemView.findViewById( R.id.artist );
        // Get the default translation from the currentWord object and set this text on
        // the default TextView.
        secondTextView.setText( currentWord.getEnglishSongs() );

        // Find the ImageView in the list_item.xml layout with the ID image.
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
              // Set the ImageView to the image resource specified in the current Word
                  imageView.setImageResource(currentWord.getImageResourceId());


        // Set the theme color for the list item
        View textContainer = listItemView.findViewById( R.id.text_container );
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor( getContext(), mColorResourceId );
        // Set the background color of the text container View
        textContainer.setBackgroundColor( color );


        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }
}
