 package com.example.multikskills.journal_app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

 public class ViewEntryActivity extends AppCompatActivity {
     TextView date,message,title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("journal");

        //initialize edittext and button
        title =  findViewById(R.id.title);
        message =  findViewById(R.id.message);
        date   = findViewById(R.id.date);

//getExtra is used to fetch data passed through intent
        final String title1 = getIntent().getStringExtra("title");
        String message1 = getIntent().getStringExtra("message");
        String date1 = getIntent().getStringExtra("date");
        final String key = getIntent().getStringExtra("key");

        //show values in Edittexts
        title.setText(title1);
        message.setText(message1);
        date.setText(date1);
    }

}
