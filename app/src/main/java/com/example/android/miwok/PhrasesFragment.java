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

public class PhrasesFragment extends Fragment {
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
                    mediaPlayer.stop();
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
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMedia();
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        // Inflate a rootView for the fragment
        View rootView = inflater.inflate(R.layout.word_list, container, false);


        // Retrieve Audio Manager object
        audioManager = getActivity().getSystemService(AudioManager.class);

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
        WordAdapter wordAdapter = new WordAdapter(getActivity(), phrases, R.color.category_phrases);


        // Set the adapter to the listView
        ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(wordAdapter);

        // Set OnItemClickListener for the pronunciation
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Request Audio Focus
                int result = audioManager.requestAudioFocus(afChangeListener, AudioManager.STREAM_NOTIFICATION, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AudioManager.AUDIOFOCUS_GAIN) {
                    // Return the color audio resource id
                    int audio = phrases.get(i).getAudioResourceId();

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

            // Release media player object
            mediaPlayer.release();
            mediaPlayer=null;


            // Media player is complete, abandon audio focus
            audioManager.abandonAudioFocus(afChangeListener);
        }
    }
}
