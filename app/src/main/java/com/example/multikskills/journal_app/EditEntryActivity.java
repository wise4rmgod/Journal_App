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

public class EditEntryActivity extends AppCompatActivity {
    EditText title,message;
    Button modify;
    DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //point database reference to Movies in Firebase Database
        database = FirebaseDatabase.getInstance().getReference("journal");

        //initialize edittext and button
        title = (EditText) findViewById(R.id.title);
        message = (EditText) findViewById(R.id.message);
        modify = (Button) findViewById(R.id.modify);

        //getExtra is used to fetch data passed through intent
        final String title1 = getIntent().getStringExtra("title");
        String message1 = getIntent().getStringExtra("message");
        // String date = getIntent().getStringExtra("date");
        final String key = getIntent().getStringExtra("key");

        //show values in Edittexts
        title.setText(title1);
        message.setText(message1);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        final String strDate = dateFormat.format(date).toString();

        //click Listener of EDIT Button
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new values to be added to database
                Journal m = new Journal(title.getText().toString(), message.getText().toString(),strDate);
                //add new values to child key
                database.child(key).setValue(m);
                Toast.makeText(getApplicationContext(), "Changed", Toast.LENGTH_SHORT).show();
                finish();

            }
        });

    }

}
