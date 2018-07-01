package com.example.multikskills.journal_app.Presenter;

import com.example.multikskills.journal_app.MainMVP;

public class Signinpresenter implements MainMVP.presenter{
    private final MainMVP.view view;

    public Signinpresenter(MainMVP.view view){
        this.view=view;
    }


    @Override
    public void buttonclicksigin() {
        view.showsign();
    }
}
