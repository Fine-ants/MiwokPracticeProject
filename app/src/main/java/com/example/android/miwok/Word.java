package com.example.android.miwok;

/**
 * Created by jordanhuus on 3/4/2018.
 */

public class Word {
    private String englishWord;
    private String miwokTranslation;
    private static final int NO_IMAGE_PROVIDED = -1;
    private int imageResourceId = NO_IMAGE_PROVIDED;


    public Word(String englishWord, String miwokTranslation) {
        this.englishWord = englishWord;
        this.miwokTranslation = miwokTranslation;
    }

    public Word(String englishWord, String miwokTranslation, int imageResourceId) {
        this.englishWord = englishWord;
        this.miwokTranslation = miwokTranslation;
        this.imageResourceId = imageResourceId;
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

    public int getImageResourceId() {
        return imageResourceId;
    }

    public boolean hasImage() {
        return imageResourceId != NO_IMAGE_PROVIDED;
    }
}

