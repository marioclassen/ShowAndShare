package com.example.marioclassen.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.marioclassen.myapplication.data.dto.PersonDTO;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by marioclassen on 5/10/16.
 */
public class DBHandler extends SQLiteOpenHelper implements TownListener {

    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "TownDatabase.db";
    private static final String TABLE_NAME = "persons_table";

    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "_name";
    private static final String KEY_THUMBNAIL = "_thumbnail";
    private static final String KEY_AGE = "_age";
    private static final String KEY_WEIGHT = "_weight";
    private static final String KEY_HEIGHT = "_height";
    private static final String KEY_HAIR_COLOR = "_hair_color";

    String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+KEY_ID+" INTEGER PRIMARY KEY,"+KEY_NAME+" TEXT,"+KEY_AGE+" TEXT,"+KEY_HEIGHT+" TEXT,"+KEY_THUMBNAIL+" TEXT,"+KEY_WEIGHT+" TEXT,"+KEY_HAIR_COLOR+" TEXT)";
    String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;

    DBListenerFriends dbListenerFriends;
    DBListenerProfs dbListenerProfs;

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        dbListenerFriends = new DBHandlerFriends(context);
        dbListenerProfs = new DBHandlerProfs(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    @Override
    public void addPerson(PersonDTO personDTO) {
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put(KEY_ID, personDTO.getId());
            values.put(KEY_AGE, personDTO.getAge());
            values.put(KEY_HAIR_COLOR, personDTO.getHair_color());
            values.put(KEY_HEIGHT, personDTO.getHeight());
            values.put(KEY_NAME, personDTO.getName());
            values.put(KEY_THUMBNAIL, personDTO.getThumbnail());
            values.put(KEY_WEIGHT, personDTO.getWeight());
            db.insert(TABLE_NAME, null, values);
            db.close();

            dbListenerFriends.addAllFriends(personDTO);
        }catch (Exception e){
            Log.e("problem",e+"");
        }
    }

    @Override
    public int getPersonCount() {
        int num = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            String QUERY = "SELECT * FROM "+TABLE_NAME;
            Cursor cursor = db.rawQuery(QUERY, null);
            num = cursor.getCount();
            db.close();
            return num;
        }catch (Exception e){
            Log.e("error",e+"");
        }
        return 0;
    }

    @Override
    public List<PersonDTO> getAllPersons() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<PersonDTO> personList = null;
        try{
            personList = new ArrayList<PersonDTO>();
            String QUERY = "SELECT * FROM "+TABLE_NAME;
            Cursor cursor = db.rawQuery(QUERY, null);
            if(!cursor.isLast())
            {
                while (cursor.moveToNext())
                {
                    PersonDTO personDTO = new PersonDTO();
                    personDTO.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                    personDTO.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                    personDTO.setThumbnail(cursor.getString(cursor.getColumnIndex(KEY_THUMBNAIL)));
                    personDTO.setAge(cursor.getInt(cursor.getColumnIndex(KEY_AGE)));
                    personDTO.setHair_color(cursor.getString(cursor.getColumnIndex(KEY_HAIR_COLOR)));
                    personDTO.setHeight(cursor.getDouble(cursor.getColumnIndex(KEY_HEIGHT)));
                    personDTO.setWeight(cursor.getDouble(cursor.getColumnIndex(KEY_WEIGHT)));

                    personDTO.setFriends(dbListenerFriends.getAllFriends(personDTO.getId()));

                    personList.add(personDTO);
                }
            }
            db.close();
        }catch (Exception e){
            Log.e("error",e+"");
        }
        return personList;
    }

    @Override
    public PersonDTO getPersonWithId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        PersonDTO person = null;
        try{
            person = new PersonDTO();
            String QUERY = "SELECT * FROM "+TABLE_NAME+" WHERE "+ KEY_ID + " = " + id;
            Cursor cursor = db.rawQuery(QUERY, null);
            if(!cursor.isLast())
            {
                while (cursor.moveToNext())
                {
                    person.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                    person.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                    person.setThumbnail(cursor.getString(cursor.getColumnIndex(KEY_THUMBNAIL)));
                    person.setAge(cursor.getInt(cursor.getColumnIndex(KEY_AGE)));
                    person.setHair_color(cursor.getString(cursor.getColumnIndex(KEY_HAIR_COLOR)));
                    person.setHeight(cursor.getDouble(cursor.getColumnIndex(KEY_HEIGHT)));
                    person.setWeight(cursor.getDouble(cursor.getColumnIndex(KEY_WEIGHT)));
                    person.setFriends(dbListenerFriends.getAllFriends(person.getId()));
                }
            }
            db.close();
        }catch (Exception e){
            Log.e("error",e+"");
        }
        return person;
    }
}
