package com.example.multikskills.journal_app.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.multikskills.journal_app.EditentryMVP;
import com.example.multikskills.journal_app.Model.Journal;
import com.example.multikskills.journal_app.Presenter.Editentrypresenter;
import com.example.multikskills.journal_app.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditEntryActivity extends AppCompatActivity implements EditentryMVP.view{
    EditText title,message;
    Button modify;
    DatabaseReference database;
    private Editentrypresenter editentrypresenter;
    Date date;
    DateFormat dateFormat;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      editentrypresenter = new Editentrypresenter(this);
        //point database reference to Movies in Firebase Database
        database = FirebaseDatabase.getInstance().getReference("journal");

        //initialize edittext and button
       // title = (EditText) findViewById(R.id.title);
        message = (EditText) findViewById(R.id.message);
        modify = (Button) findViewById(R.id.modify);

        //getExtra is used to fetch data passed through intent
        final String title1 = getIntent().getStringExtra("title");
        String message1 = getIntent().getStringExtra("message");
        // String date = getIntent().getStringExtra("date");
          key = getIntent().getStringExtra("key");

        //show values in Edittexts
       // title.setText(title1);
        message.setText(message1);
         dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
         date = new Date();


        //click Listener of EDIT Button
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            editentrypresenter.editsave();

            }
        });

    }

    @Override
    public void showeditsave() {
        final String strDate = dateFormat.format(date).toString();
        //new values to be added to database
        Journal m = new Journal(message.getText().toString().toString(),strDate);
        //add new values to child key
        database.child(key).setValue(m);
        Toast.makeText(getApplicationContext(), "Changed", Toast.LENGTH_SHORT).show();
        finish();
    }
}
