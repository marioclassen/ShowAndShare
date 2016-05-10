package com.example.marioclassen.myapplication;

import com.example.marioclassen.myapplication.data.loader.LoadTownDTOAsync;

/**
 * Created by marioclassen on 5/5/16.
 */
public class MainPresenterImpl implements MainPresenter {
    @Override
    public void searchPeople(MainView mainView) {

        new LoadTownDTOAsync(mainView).execute("");

    }
}
