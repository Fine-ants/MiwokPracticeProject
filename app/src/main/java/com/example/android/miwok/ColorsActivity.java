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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);


        // Store each word object into array
        ArrayList<Word> colors = new ArrayList<>();
        colors.add(new Word("green", "green definition"));
        colors.add(new Word("blue", "blue definition"));
        colors.add(new Word("yellow", "yellow definition"));
        colors.add(new Word("red", "red definition"));
        colors.add(new Word("orange", "orange definition"));
        colors.add(new Word("black", "black definition"));


        // Instantiate custom word adapter
        WordAdapter wordAdapter = new WordAdapter(this, colors);

        // Set the custom word adapter
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(wordAdapter);


    }
}
