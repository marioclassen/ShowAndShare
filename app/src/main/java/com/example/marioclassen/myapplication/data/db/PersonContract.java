package com.example.marioclassen.myapplication.data.db;

import android.provider.BaseColumns;

/**
 * Created by marioclassen on 5/7/16.
 */
public class PersonContract {

    public PersonContract () {

    }

    public static abstract class PersonEntry implements BaseColumns {
        public static final String TABLE_NAME = "persons";
        public static final String COLUMN_NAME_ENTRY_ID = "personid";
        public static final String COLUMN_NAME_NAME = "name";

    }

}
