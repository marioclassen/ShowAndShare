package com.example.marioclassen.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.marioclassen.myapplication.data.dto.PersonDTO;

/**
 * Created by marioclassen on 5/5/16.
 */
public class PersonDetailActivity extends AppCompatActivity implements PersonDetailView {

    int personID;
    String key_person_id = "";

    PersonDetailPresenter personDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);


        personDetailPresenter = new PersonDetailPresenterImpl();

        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Info");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Info");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Friends");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Friends");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Professions");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Professions");
        host.addTab(spec);




    }

    @Override
    protected void onStart () {
        super.onStart();

        key_person_id = getString(R.string.key_person_id);

        Intent intent = getIntent();

        if (intent != null) {
            Bundle extras = intent.getExtras();
            personID = intent.getExtras().getInt(key_person_id);
            personDetailPresenter.updatePersonDetail(this, personID);
        } else {
            System.out.println("error");
        }



    }


    @Override
    public void updatePersonDetails(PersonDTO personDTO) {
        TextView textView = (TextView) findViewById(R.id.txtName);
        textView.setText(personDTO.getName());

        TextView tvAge = (TextView) findViewById(R.id.ageValue);
        tvAge.setText(personDTO.getAge() + "");

        TextView tvHeight = (TextView) findViewById(R.id.heightValue);
        tvHeight.setText(personDTO.getHeight() +"");

        TextView tvWeight = (TextView) findViewById(R.id.weightValue);
        tvWeight.setText(personDTO.getWeight() +"");

        TextView tvHair = (TextView) findViewById(R.id.hairValue);
        tvHair.setText(personDTO.getHair_color());

        loadImage(personDTO);
    }

    private void loadImage(PersonDTO personDTO) {

        ImageView imgPerson = (ImageView) findViewById(R.id.imgPerson);
        Uri uri = Uri.parse(personDTO.getThumbnail());
        imgPerson.setImageURI(uri);

    }
}
