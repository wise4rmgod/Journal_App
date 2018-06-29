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

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.widget.LinearLayout.VERTICAL;

public class ViewAllEntryActivity extends AppCompatActivity {
    //a constant for detecting the login intent result
    private static final int RC_SIGN_IN = 234;

    //Tag for the logs optional
    private static final String TAG = "wisejournal";

    //creating a GoogleSignInClient object
    GoogleSignInClient mGoogleSignInClient;
    static {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
    //And also a Firebase Auth object
    FirebaseAuth mAuth;
    private List<Journal> mjournal = new ArrayList<Journal>();
    private RecyclerView recyclerView;
    private Journal_adapter mAdapter;
    DatabaseReference database;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher_round);
        getSupportActionBar().setTitle("  Journal App");
        //first we intialized the FirebaseAuth object
        mAuth = FirebaseAuth.getInstance();


//initialized fab button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //Click listener of ADD NEW button
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ViewAllEntryActivity.this,AddEntryActivity.class);
                startActivity(intent);
            }
        });

        // initialized recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //initialize Adapter
        mAdapter = new Journal_adapter(getApplicationContext(), mjournal);
        //set layout manager to recycler view
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), VERTICAL);
        recyclerView.addItemDecoration(decoration);
        //set Adapter to recycler view
        recyclerView.setAdapter(mAdapter);
        //  FirebaseCrash.log("App Started");
        prepareMovieData();

    }


    @Override
    protected void onResume() {
        super.onResume();
        prepareMovieData();
    }

    //method to fetch data from firebase and add to RecyclerView
    private void prepareMovieData() {
        mjournal.clear();
        //pint database reference to journals
        database = FirebaseDatabase.getInstance().getReference("journal");

        //ValueEvent Listener --> executes everytime a datachange in Movies node in firebase occurs
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //clear list
                mjournal.clear();
                //loop items
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //get value from postSnapshot to journal object
                    Journal m = postSnapshot.getValue(Journal.class);
                    Journal journ = new Journal(m.getMessage(), m.getDate(), m.getTitle(), postSnapshot.getKey());
                    //add journal object to list used in recycler
                    mjournal.add(journ);
                    //notify data changed to recyclerView
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
