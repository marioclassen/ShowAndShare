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
public class DBHandlerFriends extends SQLiteOpenHelper implements DBListenerFriends{

    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "TownDatabase.db";
    private static final String TABLE_NAME = "friends_table";

    private static final String KEY_ID = "_id";
    private static final String KEY_PERSON_ID = "_person_id";
    private static final String KEY_FRIEND_NAME = "_friend_name";

    String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+KEY_ID+" INTEGER PRIMARY KEY,"+KEY_PERSON_ID+" TEXT,"+KEY_FRIEND_NAME+" TEXT)";
    String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;

    public DBHandlerFriends(Context context) {
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
    public void addAllFriends(PersonDTO personDTO) {
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            for (String friendName : personDTO.getFriends()) {
                ContentValues values = new ContentValues();
                values.put(KEY_PERSON_ID, personDTO.getId());
                values.put(KEY_FRIEND_NAME, friendName);
                db.insert(TABLE_NAME, null, values);
            }
            db.close();
        }catch (Exception e){
            Log.e("problem",e+"");
        }
    }

    @Override
    public void addFriend(int personId, String friendName) {
        // TODO Have to be implemented
    }

    @Override
    public List<String> getAllFriends(int personId) {
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
                    String friend = cursor.getString(cursor.getColumnIndex(KEY_FRIEND_NAME));

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
