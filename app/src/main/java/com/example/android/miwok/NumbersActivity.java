/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMedia();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);


        final ArrayList<Word> numbers = new ArrayList<>();
        numbers.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        numbers.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        numbers.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        numbers.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        numbers.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        numbers.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        numbers.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        numbers.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        numbers.add(new Word("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
        numbers.add(new Word("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));


        // Instantiate custom word adapter
        WordAdapter itemsAdapter = new WordAdapter(this, numbers, R.color.category_numbers);


        // Set the adapter to the listView
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        // Set OnItemClickListener for the pronunciation
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Return the color audio resource id
                int audio = numbers.get(i).getAudioResourceId();

                // Prior to starting media player, release if already exists
                releaseMedia();

                // Create a new media player and start
                mediaPlayer = MediaPlayer.create(NumbersActivity.this, audio);
                mediaPlayer.start();

                // When media track is finished call releaseMedia method
                mediaPlayer.setOnCompletionListener(onCompletionListener);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMedia();
    }

    void releaseMedia(){
        if(mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer=null;
        }
    }
}



















