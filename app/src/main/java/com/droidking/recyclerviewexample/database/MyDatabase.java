package com.droidking.recyclerviewexample.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Droid King on 28/06/2015.
 */
public class MyDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "_database";

    private static final String GROUPS_TABLE_NAME = "groups_table";


    public static final String GROUP_ID = "group_id";
    public static final String GROUP_NAME = "group_name";
    public static final String GROUP_NOTES = "group_notes";




    private static final String CREATE_GROUPS_TABLE = "CREATE TABLE " + GROUPS_TABLE_NAME + " ( " + GROUP_ID + " INTEGER PRIMARY KEY,"
            + GROUP_NAME + " TEXT," + GROUP_NOTES + " TEXT );";

    private SQLiteDatabase database;

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_GROUPS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GROUPS_TABLE_NAME);
        onCreate(db);
    }





    public void setGroupData(String groupName, String groupNotes) {
        database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(GROUP_NAME, groupName);
        cv.put(GROUP_NOTES, groupNotes);
        database.insert(GROUPS_TABLE_NAME, null, cv);
    }


    public Cursor getGroupData() {
        database = getReadableDatabase();
        String[] columns = {GROUP_ID, GROUP_NAME, GROUP_NOTES};
        Cursor c = database.query(GROUPS_TABLE_NAME, columns, null, null, null, null, GROUP_ID + " DESC");
        return c;
    }

    public Cursor getGroupName(String[] args) {
        database = getReadableDatabase();
        String query = "SELECT " + GROUP_NAME + " FROM " + GROUPS_TABLE_NAME + " WHERE " + GROUP_NAME + " =?";
        Cursor c = database.rawQuery(query, args);
        return c;
    }



}
