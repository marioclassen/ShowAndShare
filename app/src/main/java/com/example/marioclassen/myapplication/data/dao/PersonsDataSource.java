package com.example.marioclassen.myapplication.data.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.marioclassen.myapplication.data.db.MySQLiteHelper;
import com.example.marioclassen.myapplication.data.dto.PersonDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marioclassen on 5/7/16.
 */
public class PersonsDataSource {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_NAME };

    public PersonsDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public PersonDTO createPerson(String comment) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NAME, comment);
        long insertId = database.insert(MySQLiteHelper.TABLE_PERSONS, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_PERSONS,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        PersonDTO newPerson = cursorToComment(cursor);
        cursor.close();
        return newPerson;
    }

    public void deletePerson(PersonDTO person) {
        long id = person.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_PERSONS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<PersonDTO> getAllComments() {
        List<PersonDTO> persons = new ArrayList<PersonDTO>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_PERSONS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            PersonDTO person = cursorToComment(cursor);
            persons.add(person);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return persons;
    }

    private PersonDTO cursorToComment(Cursor cursor) {
        PersonDTO person = new PersonDTO();
        person.setId(cursor.getInt(0));
        person.setName(cursor.getString(1));
        return person;
    }


}
