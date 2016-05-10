package com.example.marioclassen.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.example.marioclassen.myapplication.data.dto.PersonDTO;
import com.example.marioclassen.myapplication.db.DBHandler;
import com.example.marioclassen.myapplication.helper.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    GridView gridView;
    PersonListAdapter personListAdapter;
    List<PersonDTO> personDTOs = new ArrayList<PersonDTO>();

    String key_person_id;

    MainPresenter mainPresenter;

    DBHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        key_person_id  = getString(R.string.key_person_id);

        mainPresenter = new MainPresenterImpl();

        gridView = (GridView) findViewById(R.id.gridView1);





        handler = new DBHandler(this);
        NetworkUtils utils = new NetworkUtils(MainActivity.this);
        if(handler.getPersonCount() == 0 && utils.isConnectingToInternet())
        {
            mainPresenter.searchPeople(this);
        }
        else
        {
            this.personDTOs = handler.getAllPersons();
        }

        personListAdapter = new PersonListAdapter(this, R.layout.activity_person_list_item, personDTOs);
        gridView.setAdapter(personListAdapter);



    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void updatePersonList(List<PersonDTO> personDTOs) {
        for (PersonDTO personDTO : personDTOs) {
            handler.addPerson(personDTO);
        }
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
