package com.example.q.pos_min;

import android.database.Cursor;

/**
 * Created by q on 2017-04-22.
 */

public class Chef extends User {
    static String mRole;
    public void Customer(Cursor userCursor){

        super.User(userCursor);
        mRole = "CHEF";
    }
}
