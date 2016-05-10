package com.example.marioclassen.myapplication;

import android.content.Intent;
import android.net.http.HttpResponseCache;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import com.example.marioclassen.myapplication.data.dto.PersonDTO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    GridView gridView;
    PersonListAdapter personListAdapter;
    List<PersonDTO> personDTOs = new ArrayList<PersonDTO>();

    String key_person_id;

    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        key_person_id  = getString(R.string.key_person_id);

        mainPresenter = new MainPresenterImpl();

        gridView = (GridView) findViewById(R.id.gridView1);

        personListAdapter = new PersonListAdapter(this, R.layout.activity_person_list_item, personDTOs);
        gridView.setAdapter(personListAdapter);



    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.searchPeople(this);
    }

    public void searchPeople(View view) {


        mainPresenter.searchPeople(this);

    }

    @Override
    public void updatePersonList(List<PersonDTO> personDTOs) {
        personListAdapter.updateList(personDTOs);
    }

    @Override
    public void showPersonDetails(int id) {
        Intent intent = new Intent(this, PersonDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(key_person_id, id);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
