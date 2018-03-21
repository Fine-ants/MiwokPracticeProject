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
 * Created by jordanhuus on 3/18/2018.
 */

public class NumbersFragment extends Fragment {

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
        WordAdapter itemsAdapter = new WordAdapter(getActivity(), numbers, R.color.category_numbers);


        // Set the adapter to the listView
        ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        // Set OnItemClickListener for the pronunciation
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Request Audio Focus
                int result = audioManager.requestAudioFocus(afChangeListener, AudioManager.STREAM_NOTIFICATION, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AudioManager.AUDIOFOCUS_GAIN) {
                    // Return the color audio resource id
                    int audio = numbers.get(i).getAudioResourceId();

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

    private void releaseMedia(){
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
