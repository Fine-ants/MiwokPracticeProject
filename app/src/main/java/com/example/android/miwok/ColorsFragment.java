package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by jorda on 3/18/2018.
 */

public class ColorsFragment extends Fragment {
    private AudioManager audioManager;
    private AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            switch (i){

                // AUDIOFOCUS_GAIN
                case AudioManager.AUDIOFOCUS_GAIN:
                    mediaPlayer.start();
                    break;

                // AUDIOFOCUS_LOSS
                case AudioManager.AUDIOFOCUS_LOSS:
                    releaseMedia();
                    break;

                // AUDIOFOCUS_LOSS_TRANSIENT and AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    mediaPlayer.pause();
                    break;
            }
        }
    };
    private MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMedia();
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        // Retrieve audio manager object
        audioManager = getActivity().getSystemService(AudioManager.class);


        // Store each word object into array
        final ArrayList<Word> colors = new ArrayList<>();
        colors.add(new Word("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        colors.add(new Word("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        colors.add(new Word("brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        colors.add(new Word("gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        colors.add(new Word("black", "kululli", R.drawable.color_black, R.raw.color_black));
        colors.add(new Word("white", "kelelli", R.drawable.color_white, R.raw.color_white));
        colors.add(new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        colors.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));


        // Instantiate custom word adapter
        WordAdapter wordAdapter = new WordAdapter(getActivity(), colors, R.color.category_colors);


        // Set the custom word adapter
        ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(wordAdapter);

        // Set OnItemClickListener for the pronunciation
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Request audio focus
                int result = audioManager.requestAudioFocus(afChangeListener, AudioManager.STREAM_NOTIFICATION, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    // Return the color audio resource id
                    int audio = colors.get(i).getAudioResourceId();

                    // Prior to starting media player, release if already exists
                    releaseMedia();

                    // Create a new media player and start
                    mediaPlayer = MediaPlayer.create(getActivity(), audio);
                    mediaPlayer.start();

                    // When media track is finished call releaseMedia method
                    mediaPlayer.setOnCompletionListener(onCompletionListener);
                }
            }
        });


        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMedia();
    }

    void releaseMedia(){
        if(mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer=null;

            // Abandon audio focus
            audioManager.abandonAudioFocus(afChangeListener);
        }
    }
}
