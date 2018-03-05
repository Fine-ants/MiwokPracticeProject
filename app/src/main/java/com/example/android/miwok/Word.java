package com.example.android.miwok;

/**
 * Created by jordanhuus on 3/4/2018.
 */

public class Word {
    private String word;
    private String definition;


    // Constructor
    public Word(String word, String definition) {
        this.word = word;
        this.definition = definition;
    }


    public String getWord() {
        return word;
    }

    public String getDefinition() {
        return definition;
    }
}
