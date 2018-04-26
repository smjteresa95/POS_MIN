package com.example.q.pos_min;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by q on 2017-04-21.
 */

public class AddEmployee extends AppCompatActivity {
    EditText editTextUserName,editTextUserId,editTextPassword,editTextConfirmPassword,editTextAddress,
            editTextCity,editTextZipCode,editTextCountry,editTextNumber;
    DatabaseManager databaseAdapter;
    Context context = this;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_employee);

        databaseAdapter = DatabaseManager.getInstance(this);
        databaseAdapter.open();


        editTextUserName = (EditText) findViewById(R.id.editTextUserNameToSignup);
        editTextUserId  = (EditText) findViewById(R.id.editTextUserIdToSignUp);
        editTextPassword = (EditText) findViewById(R.id.editTextPasswordToSignup);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPasswordToSignup);
        editTextAddress = (EditText) findViewById(R.id.editTextAddressToSignup) ;
        editTextCity = (EditText) findViewById(R.id.editTextCityToSignup) ;
        editTextZipCode = (EditText) findViewById(R.id.editTextZipCodeToSignup) ;
        editTextCountry = (EditText) findViewById(R.id.editTextCountryToSignup) ;
        editTextNumber = (EditText) findViewById(R.id.editTextNumberToSignup) ;

        Button addEmployeeButton = (Button) findViewById(R.id.buttonSignup1);

        addEmployeeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                signup();

                Toast.makeText(getApplicationContext(),
                        "직원정보가 추가되었습니다.", Toast.LENGTH_LONG)
                        .show();

            }
        });
    }

    public void signup(){

        String userName = editTextUserName.getText().toString();
        String emailID = editTextUserId.getText().toString();
        String password = editTextPassword.getText().toString();
        String address = editTextAddress.getText().toString();
        String city = editTextCity.getText().toString();
        String zipCode = editTextZipCode.getText().toString();
        String country = editTextCountry.getText().toString();
        String number = editTextNumber.getText().toString();

        databaseAdapter.insertEntry(userName,emailID,password,address,city,zipCode,country,number,"CHEF");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cart_screen_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.cart_screen_logout: {
                Toast.makeText(AddEmployee.this,
                        "로그아웃 되었습니다", Toast.LENGTH_SHORT)
                        .show();

                Intent intentLogout = new Intent(AddEmployee.this, LoginActivity.class);
                startActivity(intentLogout);
                return true;
            }

            case android.R.id.home:
            {
                Log.d("CartScreen","Backbutton:");

                Intent intentMenu = new Intent(AddEmployee.this, EmployeeTasks.class);
                startActivity(intentMenu);
                return true;
            }

            default: {
                return true;
            }

        }

    }

}
