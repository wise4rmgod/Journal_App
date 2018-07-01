package com.example.multikskills.journal_app.Presenter;

import com.example.multikskills.journal_app.AddentryMVP;

public class Addentrypresenter implements AddentryMVP.presenter {
    private final AddentryMVP.view view;

    public Addentrypresenter(AddentryMVP.view view){
        this.view=view;
    }

    @Override
    public void buttonsave() {
        view.showsave();
    }
}
