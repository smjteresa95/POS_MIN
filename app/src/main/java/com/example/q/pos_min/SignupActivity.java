package com.example.q.pos_min;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by q on 2017-04-22.
 */

public class SignupActivity extends AppCompatActivity {
    EditText editTextUserName,editTextUserId,editTextPassword,editTextConfirmPassword,editTextAddress,
            editTextCity,editTextZipCode,editTextCountry,editTextNumber;
    Button btnCreateAccount;
    Context context = this;
    DatabaseManager DatabaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);
        DatabaseManager = DatabaseManager.getInstance(this);
        DatabaseManager = DatabaseManager.open();

        editTextUserName = (EditText) findViewById(R.id.editTextUserNameToSignup);
        editTextUserId  = (EditText) findViewById(R.id.editTextUserIdToSignUp);
        editTextPassword = (EditText) findViewById(R.id.editTextPasswordToSignup);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPasswordToSignup);
        editTextAddress = (EditText) findViewById(R.id.editTextAddressToSignup) ;
        editTextCity = (EditText) findViewById(R.id.editTextCityToSignup) ;
        editTextZipCode = (EditText) findViewById(R.id.editTextZipCodeToSignup) ;
        editTextCountry = (EditText) findViewById(R.id.editTextCountryToSignup) ;
        editTextNumber = (EditText) findViewById(R.id.editTextNumberToSignup) ;

        btnCreateAccount = (Button) findViewById(R.id.buttonSignup1);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String userName = editTextUserName.getText().toString();
                String emailID = editTextUserId.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText().toString();
                String address = editTextAddress.getText().toString();
                String city = editTextCity.getText().toString();
                String zipCode = editTextZipCode.getText().toString();
                String country = editTextCountry.getText().toString();
                String number = editTextNumber.getText().toString();
                String role = "CUSTOMER";
                if (userName.equals("") || password.equals("")
                        || confirmPassword.equals("")) {

                    Toast.makeText(getApplicationContext(), "정보를 모두 입력하세요",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(),
                            "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG)
                            .show();
                    return;
                } else {

                    DatabaseManager.insertEntry(userName,emailID,password,address,city,zipCode,country,number,role);
                    Toast.makeText(getApplicationContext(),
                            "계정이 생성되었습니다. ", Toast.LENGTH_LONG)
                            .show();

                    //Toast.makeText(getApplicationContext(),
                    //"Password is :"+DatabaseManager.getSinlgeUser(emailID), Toast.LENGTH_LONG).show();

                    Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();

                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
}

