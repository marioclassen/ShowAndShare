package com.example.marioclassen.myapplication;

import android.widget.ImageView;

/**
 * Created by marioclassen on 5/6/16.
 */
public interface PersonDetailPresenter {

    void updatePersonDetail(PersonDetailView personDetailView, int position);

    void loadImageFromUrl (ImageView imageView, String url);

}
