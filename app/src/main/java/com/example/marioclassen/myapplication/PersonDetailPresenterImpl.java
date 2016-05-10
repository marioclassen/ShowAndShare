package com.example.marioclassen.myapplication;

import com.example.marioclassen.myapplication.data.loader.LoadPersonDTOAsync;

/**
 * Created by marioclassen on 5/6/16.
 */
public class PersonDetailPresenterImpl implements PersonDetailPresenter {
    @Override
    public void updatePersonDetail(PersonDetailView personDetailView, int position) {
        new LoadPersonDTOAsync(personDetailView).execute(position);
    }

}
