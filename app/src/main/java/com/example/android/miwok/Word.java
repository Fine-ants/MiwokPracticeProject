package com.example.android.miwok;

/**
 * Created by jordanhuus on 3/4/2018.
 */

public class Word {
    private String englishWord;
    private String miwokTranslation;


    public Word(String englishWord, String miwokTranslation) {
        this.englishWord = englishWord;
        this.miwokTranslation = miwokTranslation;
    }


    @Override
    public String toString() {
        return "Word{" +
                "englishWord='" + englishWord + '\'' +
                ", miwokTranslation='" + miwokTranslation + '\'' +
                '}';
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public String getMiwokTranslation() {
        return miwokTranslation;
    }
}
