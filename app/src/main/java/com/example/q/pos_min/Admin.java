package com.example.q.pos_min;

import android.database.Cursor;

/**
 * Created by q on 2017-04-21.
 */

public class Admin extends User {
    static String mRole;
    public void Customer(Cursor userCursor){

        super.User(userCursor);
        mRole = "ADMIN";
    }
}