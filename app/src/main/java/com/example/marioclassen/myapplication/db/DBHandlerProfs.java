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
public class DBHandlerProfs extends SQLiteOpenHelper implements DBListenerProfs{

    private static final int DB_VERSION = 3;
    private static final String DB_NAME = "TownDatabase.db";
    private static final String TABLE_NAME = "profs_table";

    private static final String KEY_ID = "_id";
    private static final String KEY_PERSON_ID = "_person_id";
    private static final String KEY_PROD_NAME = "_prof_name";

    String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+KEY_ID+" INTEGER PRIMARY KEY,"+KEY_PERSON_ID+" TEXT,"+ KEY_PROD_NAME +" TEXT)";
    String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;

    public DBHandlerProfs(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
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
    public void addAllProfs(PersonDTO personDTO) {
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            for (String friendName : personDTO.getFriends()) {
                ContentValues values = new ContentValues();
                values.put(KEY_PERSON_ID, personDTO.getId());
                values.put(KEY_PROD_NAME, friendName);
                db.insert(TABLE_NAME, null, values);
            }
            db.close();
        }catch (Exception e){
            Log.e("problem",e+"");
        }
    }

    @Override
    public void addProf(int personId, String profName) {
        // TODO Have to be implemented
    }

    @Override
    public List<String> getAllProfs(int personId) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> friends = null;
        try{
            friends = new ArrayList<String>();
            String QUERY = "SELECT * FROM "+TABLE_NAME+ " where " + KEY_PERSON_ID + " = " + personId;
            Cursor cursor = db.rawQuery(QUERY, null);
            if(!cursor.isLast())
            {
                while (cursor.moveToNext())
                {
                    String friend = cursor.getString(cursor.getColumnIndex(KEY_PROD_NAME));

                    friends.add(friend);
                }
            }
            db.close();
        }catch (Exception e){
            Log.e("error",e+"");
        }
        return friends;
    }
}
