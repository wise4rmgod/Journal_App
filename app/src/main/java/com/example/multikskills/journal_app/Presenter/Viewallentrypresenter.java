package com.example.multikskills.journal_app.Presenter;


import com.example.multikskills.journal_app.ViewallentryMVP;

public class Viewallentrypresenter implements ViewallentryMVP.presenter {

    private final ViewallentryMVP.view view;

    public Viewallentrypresenter(ViewallentryMVP.view view){

        this.view=view;
    }

    @Override
    public void addentry() {
      view.showentry();
    }
}
