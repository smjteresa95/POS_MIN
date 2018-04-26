package com.example.q.pos_min;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by q on 2017-04-22.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase _db) {
        _db.execSQL(DatabaseManager.DATABASE_CREATE_USERPROFILE);
        _db.execSQL(DatabaseManager.DATABASE_CREATE_MENU);
        _db.execSQL(DatabaseManager.DATABASE_CREATE_ORDER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
        Log.w("TaskDBAdapter", "Upgrading from version " + _oldVersion + " to "
                + _newVersion + ", which will destroy all old data");
        _db.execSQL("DROP TABLE IF EXISTS " + DatabaseManager.USERPROFILE_TABLE);
        _db.execSQL("DROP TABLE IF EXISTS " + DatabaseManager.MENU_TABLE);
        _db.execSQL("DROP TABLE IF EXISTS " + DatabaseManager.ORDER_TABLE);

        onCreate(_db);
    }

}