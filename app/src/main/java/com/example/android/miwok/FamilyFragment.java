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

public class FamilyFragment extends Fragment {
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
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        // Retrieve Audio Manager object
        audioManager = getActivity().getSystemService(AudioManager.class);


        final ArrayList<Word> familyMembers = new ArrayList<>();
        familyMembers.add(new Word("father", "әpә", R.drawable.family_father, R.raw.family_father));
        familyMembers.add(new Word("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        familyMembers.add(new Word("son", "angsi", R.drawable.family_son, R.raw.family_son));
        familyMembers.add(new Word("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        familyMembers.add(new Word("older brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        familyMembers.add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        familyMembers.add(new Word("older sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        familyMembers.add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        familyMembers.add(new Word("grandmother", "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        familyMembers.add(new Word("grandfather", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));


        // Instantiate custom word adapter
        WordAdapter wordAdapter = new WordAdapter(getActivity(), familyMembers, R.color.category_family);


        // Set the adapter to the listView
        ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(wordAdapter);

        // Set OnItemClickListener for the pronunciation
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Request Audio Focus
                int result = audioManager.requestAudioFocus(afChangeListener, AudioManager.STREAM_NOTIFICATION, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AudioManager.AUDIOFOCUS_GAIN){
                    // Return the color audio resource id
                    int audio = familyMembers.get(i).getAudioResourceId();

                    // Prior to starting media player, release if already exists
                    releaseMedia();
                    mediaPlayer = MediaPlayer.create(getActivity(), audio);
                    mediaPlayer.start();

                    // When media track is finished call releaseMedia method
                    mediaPlayer.setOnCompletionListener(onCompletionListener);
                }
            }
        });

        return rootView;
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

    @Override
    public void onStop() {
        super.onStop();
        releaseMedia();
    }
}
