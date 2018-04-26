package com.example.q.pos_min;

import android.database.Cursor;

import com.example.q.pos_min.DatabaseManager;

/**
 * Created by q on 2017-04-22.
 */

public class User {
    static String mUserName;
    static String mUserId;
    static String mPassword;
    static String mAddress;
    static String mCity;
    static String mZipCode;
    static String mCountry;
    static String mNumber;

    public  void User(Cursor userCursor){
        mUserName = userCursor.getString(userCursor.getColumnIndex(DatabaseManager.USERPROFILE_NAME));
        mUserId = userCursor.getString(userCursor.getColumnIndex(DatabaseManager.USERPROFILE_EMAIL));
        mPassword =  userCursor.getString(userCursor.getColumnIndex(DatabaseManager.USERPROFILE_PASSWORD));
        mAddress =  userCursor.getString(userCursor.getColumnIndex(DatabaseManager.USERPROFILE_ADDRESS));
        mCity =  userCursor.getString(userCursor.getColumnIndex(DatabaseManager.USERPROFILE_CITY));
        mZipCode =  userCursor.getString(userCursor.getColumnIndex(DatabaseManager.USERPROFILE_ZIPCODE));
        mCountry = userCursor.getString(userCursor.getColumnIndex(DatabaseManager.USERPROFILE_COUNTRY));
        mNumber = userCursor.getString(userCursor.getColumnIndex(DatabaseManager.USERPROFILE_NUMBER));
    }

    public String getuserID(){
        return mUserId;
    }

    public String getuserName(){
        return mUserName;
    }


    public String getAddress(){
        return mAddress;
    }

    public String getmPassword(){return mPassword; }
}