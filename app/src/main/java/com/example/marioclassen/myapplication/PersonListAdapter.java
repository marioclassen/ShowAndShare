package com.example.marioclassen.myapplication;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marioclassen.myapplication.data.dto.PersonDTO;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by marioclassen on 5/5/16.
 */
public class PersonListAdapter extends ArrayAdapter<PersonDTO> {

    MainActivity mainActivity;

    List<PersonDTO> personDTOs;

    public PersonListAdapter(MainActivity context, int layoutResourceId, List<PersonDTO> personDTOs) {
        super(context, layoutResourceId, personDTOs);

        this.personDTOs = personDTOs;
        this.mainActivity =context;


    }

    public void updateList(List<PersonDTO> personDTOs) {
        this.personDTOs.clear();
        this.personDTOs.addAll(personDTOs);
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return personDTOs.size();
    }

    @Override
    public PersonDTO getItem(int position) {
        // TODO Auto-generated method stub
        return personDTOs.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView textPersonName;
        SimpleDraweeView imgThumb;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder;
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater)mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.activity_person_list_item, null);
            holder = new Holder();

            holder.imgThumb = (SimpleDraweeView) view.findViewById(R.id.imageViewPersonThumbnail);
            holder.textPersonName = (TextView) view.findViewById(R.id.textViewPersonName);

            Uri uri = Uri.parse("https://raw.githubusercontent.com/facebook/fresco/gh-pages/static/logo.png");


            view.setTag(holder);

            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Toast.makeText(mainActivity, "You Clicked "+personDTOs.get(position).getName(), Toast.LENGTH_LONG).show();
                    mainActivity.showPersonDetails(position);
                }
            });

        } else {
            holder = (Holder) view.getTag();
        }

        final PersonDTO personDTO = personDTOs.get(position);
        if (personDTO != null) {
            holder.textPersonName.setText(personDTO.getName());
            Uri uri = Uri.parse(personDTO.getThumbnail());
            holder.imgThumb.setImageURI(uri);
        }


        return view;
    }


}
