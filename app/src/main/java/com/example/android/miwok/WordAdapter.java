package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by jordanhuus on 3/4/2018.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    public WordAdapter(Context context, ArrayList<Word> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, @NonNull View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(convertView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }


        // Get the word at the current position
        Word word = getItem(position);


        // Set word text
        TextView word_textView = (TextView) listItemView.findViewById(R.id.word);
        word_textView.setText(word.getEnglishWord());


        // Set definition text
        TextView definition_textView = (TextView) listItemView.findViewById(R.id.definition);
        definition_textView.setText(word.getMiwokTranslation());


        // Set image drawable
        ImageView imageView = listItemView.findViewById(R.id.image);
        if(word.hasImage()){
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(word.getImageResourceId());
        }else{
            imageView.setVisibility(View.INVISIBLE);
        }


        return listItemView;


    }
}





























