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

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

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

        // Create array list of phrases
        final ArrayList<Word> phrases = new ArrayList<>();
        phrases.add(new Word(R.raw.phrase_where_are_you_going,"Where are you going?", "minto wuksus"));
        phrases.add(new Word(R.raw.phrase_what_is_your_name,"What is your name?", "tinnә oyaase'nә"));
        phrases.add(new Word(R.raw.phrase_my_name_is,"My name is...", "oyaaset..."));
        phrases.add(new Word(R.raw.phrase_how_are_you_feeling,"How are you feeling?", "michәksәs?"));
        phrases.add(new Word(R.raw.phrase_im_feeling_good,"I'm feeling good.", "kuchi achit"));
        phrases.add(new Word(R.raw.phrase_are_you_coming,"Are you coming?", "әәnәs'aa?"));
        phrases.add(new Word(R.raw.phrase_yes_im_coming,"Yes, I'm coming.", "hәә’ әәnәm"));
        phrases.add(new Word(R.raw.phrase_im_coming,"I'm coming.", "әәnәm"));
        phrases.add(new Word(R.raw.phrase_lets_go,"Let's go.", "yoowutis"));
        phrases.add(new Word(R.raw.phrase_come_here,"Come here.", "әnni'nem"));


        // Instantiate word adapter object
        WordAdapter wordAdapter = new WordAdapter(this, phrases, R.color.category_phrases);


        // Set the adapter to the listView
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(wordAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int audio = phrases.get(i).getAudioResourceId();
                Log.i("Logtag", "onItemClick " + i + " " + audio);
                mediaPlayer = MediaPlayer.create(PhrasesActivity.this, audio);
                mediaPlayer.start();
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
