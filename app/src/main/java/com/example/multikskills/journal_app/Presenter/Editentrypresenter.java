package com.example.multikskills.journal_app.Presenter;

import com.example.multikskills.journal_app.EditentryMVP;

public class Editentrypresenter implements EditentryMVP.presenter{
    private final EditentryMVP.view view;

    public Editentrypresenter(EditentryMVP.view view){
        this.view=view;
    }

    @Override
    public void editsave() {
        view.showeditsave();
    }
}
