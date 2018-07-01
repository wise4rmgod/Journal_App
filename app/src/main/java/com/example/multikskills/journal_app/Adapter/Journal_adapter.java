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

package com.example.multikskills.journal_app.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.multikskills.journal_app.Model.Journal;
import com.example.multikskills.journal_app.R;
import com.example.multikskills.journal_app.View.EditEntryActivity;
import com.example.multikskills.journal_app.View.ViewEntryActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class   Journal_adapter extends RecyclerView.Adapter<Journal_adapter.MyViewHolder> {

        private List<Journal> journal;
        public Context mContext;
        DatabaseReference database;


        public class MyViewHolder extends RecyclerView.ViewHolder {
            public Button delete, edit;
            public TextView title, message, date;


            public MyViewHolder(View view) {
                super(view);
                //initialize buttons and TextViews
               // title =  view.findViewById(R.id.title);
                message =  view.findViewById(R.id.message);
                date =  view.findViewById(R.id.date);
                delete=view.findViewById(R.id.delete);
                edit=view.findViewById(R.id.edit);

            }
        }

        //constructor
        public Journal_adapter(Context mContext, List<Journal> journal) {
            this.journal = journal;
            this.mContext = mContext;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //set layout to itemView using Layout inflater
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {

            final Journal journals = journal.get(position);
         //   holder.title.setText(journals.getTitle());
            holder.message.setText(journals.getMessage());
            holder.date.setText(journals.getDate());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, ViewEntryActivity.class);
                    //pass data though intent using puExtra
                    i.putExtra("title", journals.getTitle());
                    i.putExtra("message",journals.getMessage());
                    i.putExtra("date", journals.getDate());
                    i.putExtra("key", journals.getKey());
                    mContext.startActivity(i);

                }
            });


            //Click listener of button delete
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //point databaseReference to Movies
                    database = FirebaseDatabase.getInstance().getReference("journal");
                    //remove value of child movie.getKey()
                    database.child(journals.getKey()).setValue(null);
                    //remove item from list
                    journal.remove(position);
                    //notofy datachanged to adapter
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, journal.size());
                    Toast.makeText(mContext, "Item Deleted", Toast.LENGTH_SHORT).show();

                }
            });

            //Click listener of Button Edit
          holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, EditEntryActivity.class);
                    //pass data though intent using puExtra
                    i.putExtra("title", journals.getTitle());
                    i.putExtra("message",journals.getMessage());
                    i.putExtra("date", journals.getDate());
                    i.putExtra("key", journals.getKey());
                    mContext.startActivity(i);
                }
            });
        }

        @Override
        public int getItemCount() {
            return journal.size();
        }
    }

