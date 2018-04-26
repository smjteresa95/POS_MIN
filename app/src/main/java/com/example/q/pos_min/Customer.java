package com.example.q.pos_min;

import android.database.Cursor;

/**
 * Created by q on 2018-04-22.
 */

public class Customer extends User {
    static String mRole;

    public void Customer(Cursor userCursor) {

        super.User(userCursor);
        mRole = "CUSROMER";
    }
}

