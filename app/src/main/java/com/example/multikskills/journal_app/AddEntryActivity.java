/*
 * Copyright 2017 The Android Open Source Project
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

package com.example.multikskills.journal_app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddEntryActivity extends AppCompatActivity {
    DatabaseReference database;
    Button save;
    EditText title, message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//point databasereference to Movies
        database = FirebaseDatabase.getInstance().getReference("journal");
        //Initalize Editexts and button
        title = (EditText) findViewById(R.id.title);
        message = (EditText) findViewById(R.id.message);
        save = (Button) findViewById(R.id.save);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        final String strDate = dateFormat.format(date).toString();
        //Clicklistener of ADD button
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add etails to MovieDetails object
                Journal m = new Journal(title.getText().toString(), message.getText().toString(),strDate);
                //push object to database to add in a new node
                database.push().setValue(m);
                Toast.makeText(getApplicationContext(), "Item Added", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

}
