package com.example.android.miwok;

/**
 * Created by jordanhuus on 3/4/2018.
 */

public class Word {
    private String englishWord;
    private String miwokTranslation;
    private static final int NO_IMAGE_PROVIDED = -1;
    private static final int NO_AUDIO_PROVIDED = -1;
    private int imageResourceId = NO_IMAGE_PROVIDED;
    private int audioResourceId = NO_AUDIO_PROVIDED;


    public Word(String englishWord, String miwokTranslation) {
        this.englishWord = englishWord;
        this.miwokTranslation = miwokTranslation;
    }

    public Word(int audioResourceId, String miwokTranslation, String englishWord){
        this.audioResourceId = audioResourceId;
        this.miwokTranslation = miwokTranslation;
        this.englishWord = englishWord;
    }

    public Word(String englishWord, String miwokTranslation, int imageResourceId) {
        this.englishWord = englishWord;
        this.miwokTranslation = miwokTranslation;
        this.imageResourceId = imageResourceId;
    }

    public Word(String englishWord, String miwokTranslation, int imageResourceId, int audioResourceId) {
        this.englishWord = englishWord;
        this.miwokTranslation = miwokTranslation;
        this.imageResourceId = imageResourceId;
        this.audioResourceId = audioResourceId;
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

