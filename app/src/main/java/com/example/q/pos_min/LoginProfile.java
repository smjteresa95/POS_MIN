package com.example.q.pos_min;

import android.database.Cursor;
import android.util.Log;

/**
 * Created by q on 2017-04-22.
 */

public class LoginProfile {
    String mUsername;
    String mpassword;

    public void LoginProfile(Cursor userCursor){
        Log.d("LoginProfile","IKn login profile");

        mUsername = userCursor.getString(userCursor.getColumnIndex(DatabaseManager.USERPROFILE_EMAIL));
        mpassword = userCursor.getString(userCursor.getColumnIndex(DatabaseManager.USERPROFILE_PASSWORD));
        Log.d("LoginProfile","IKn login profile"+mUsername);
        Log.d("LoginProfile","IKn login profile"+mpassword);

    }

    public boolean checkPassowrd(String enteredPassword){
        if(mpassword.equals(enteredPassword))
        {
            Log.d("LoginProfile","비밀번호 일치");
            return true;
        }
        else
        {
            Log.d("LoginProfile","비밀번호 불일치");
            return false ;
        }
    }
}