package com.example.q.pos_min;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
        Button buttonSignIn,buttonLoginProfile, buttonSignUp;
        String password;
        static String userName,address;
        static  Customer customer;
        static Cursor userCursor;

    /*Creating DatbaseAdapter Refrence variable */
        DatabaseManager DatabaseManager;

        /**
         * ATTENTION: This was auto-generated to implement the App Indexing API.
         * See https://g.co/AppIndexing/AndroidStudio for more information.
         */


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.d("MIN","onCreate() - LoginActivity");
            setContentView(R.layout.main);


          /* instantiating the the DBMgr and opening the database adapter */

            DatabaseManager = DatabaseManager.getInstance(this);
            DatabaseManager = DatabaseManager.open();

            buttonSignIn = (Button) findViewById(R.id.button_sign_in);
            buttonSignUp = (Button) findViewById(R.id.button_sign_up);
          /* buttonSignIn refers to the Signin button
          and buttonSignUp refers to the Signup button
           */
            buttonSignIn.setOnClickListener(this);
            buttonSignUp.setOnClickListener(this);
        }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_sign_in:
                signIn();
                break;

            case R.id.button_sign_up:
                signUp();
                break;
        }
    }
      /* If buttonSignup is clciked it will go to SignUPActivity.class
      for filling up the details of the user
       */

     /* If buttonsignin is clicked then the llayout is changed within the same activity i.e. Mainactivity
           *  */

    public void signUp(){
        Intent intentSignUP = new Intent(getApplicationContext(),
                SignupActivity.class);
        startActivity(intentSignUP);
    }

    public void signIn() {
        final Dialog dialog = new Dialog(LoginActivity.this);
        dialog.setContentView(R.layout.activity_login);
        dialog.setTitle("로그인");

        buttonLoginProfile = (Button) dialog.findViewById(R.id.button_log_in);

        buttonLoginProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                final EditText editTextUserName = (EditText) dialog
                        .findViewById(R.id.editTextUserNameToLogin);
                final EditText editTextPassword = (EditText) dialog
                        .findViewById(R.id.editTextPasswordToLogin);

                String emailID = editTextUserName.getText().toString();
                password = editTextPassword.getText().toString();

                String storedPassword = DatabaseManager.getPassword(emailID);
                userCursor = DatabaseManager.getSinlgeUser(emailID);

                if (userCursor.getCount()==0)
                {
                    Toast.makeText(LoginActivity.this,
                            "User does Not Exist",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                LoginProfile lprofile = new LoginProfile();

                lprofile.LoginProfile(userCursor);
                Log.d("LOGIN","user input email id is "+emailID);
                Log.d("LOGIN","stored password is "+storedPassword);
                //String[] rowValueUserProfile = row.split(" ");
                /*String roleValue = rowValueUserProfile[11];
                Log.d("value of rowValueUser",rowValueUserProfile[11]);
                Log.d("value of roleValue",roleValue);*/
                userName = emailID;

                Log.d("LoginActivity.java","userName :"+userCursor.getColumnName(userCursor.getColumnIndex(com.example.q.pos_min.DatabaseManager.USERPROFILE_EMAIL)));
                Log.d("LoginActivity.java","address :"+address);
                Log.d("LoginActivity.java","address :"+String.valueOf(lprofile.checkPassowrd(password)));

                if (lprofile.checkPassowrd(password)) {

                    Log.d("LogIn.java","if password.equals TRUE");
                    Toast.makeText(LoginActivity.this,
                            "로그인이 성공적으로 되었습니다.", Toast.LENGTH_SHORT)
                            .show();
                    dialog.dismiss();
                    Log.d("LoginActivity.java","ROLE OF USER: "+userCursor.getString(userCursor.getColumnIndex(DatabaseManager.USERPROFILE_ROLE)));

                    Log.d("LoginActivity.java",String.valueOf(userCursor.getString(userCursor.getColumnIndex(DatabaseManager.USERPROFILE_ROLE)).equals("CUSTOMER")));
                    if(userCursor.getString(userCursor.getColumnIndex(DatabaseManager.USERPROFILE_ROLE)).equals("CUSTOMER")) {
                        customer = new Customer();
                        customer.Customer(userCursor);

                        Log.d("LogIn.java","User Login Role CUSTOMER");
                        Intent menuIntent = new Intent(LoginActivity.this, MenuActivity.class);
                        startActivity(menuIntent);
                    }

                    Log.d("LogIn.java",String.valueOf(userCursor.getString(userCursor.getColumnIndex(DatabaseManager.USERPROFILE_ROLE)).equals("ADMIN")));

                    if(userCursor.getString(userCursor.getColumnIndex(DatabaseManager.USERPROFILE_ROLE)).equals("ADMIN")) {
                        Log.d("LogIn.java","User Login Role ADMIN");
                        Intent adminIntent = new Intent(LoginActivity.this, AdminHome.class);
                        startActivity(adminIntent);
                    }

                    if(userCursor.getString(userCursor.getColumnIndex(DatabaseManager.USERPROFILE_ROLE)).equals("CHEF")) {
                        Log.d("LogIn.java","User Login Role CHEF");
                        Intent chefIntent = new Intent(LoginActivity.this, ChefHome.class);
                        startActivity(chefIntent);
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this,
                            "아이디나 비밀번호가 일치하지 않습니다.",
                            Toast.LENGTH_LONG).show();

                }
            }
        });
        dialog.show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}