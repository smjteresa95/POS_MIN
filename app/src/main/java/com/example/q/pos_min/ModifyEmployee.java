package com.example.q.pos_min;

import android.content.Intent;
import android.database.Cursor;
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
 * Created by q on 2017-04-22.
 */

public class ModifyEmployee extends AppCompatActivity {
    EditText emailID, name ,password, address, city , zipCode , Country , mobileNumber;
    Button buttonPopulateDetails,buttonUpdate;
    String emailIdToSearch;
    DatabaseManager dataBaseAdapter;

    protected void onCreate(Bundle savedStateInstance) {
        super.onCreate(savedStateInstance);
        setContentView(R.layout.activity_admin_modify_employee);

        dataBaseAdapter = DatabaseManager.getInstance(this);
        dataBaseAdapter.open();

        emailID  = (EditText)findViewById(R.id.editTextEmail);
        name     = (EditText)findViewById(R.id.editTextName);
        password = (EditText)findViewById(R.id.editTextPassword) ;
        address  = (EditText)findViewById(R.id.editTextAddress);
        city     = (EditText)findViewById(R.id.editTextCity);
        zipCode  = (EditText)findViewById(R.id.editTextZipCode);
        Country  = (EditText)findViewById(R.id.editTextCountry);
        mobileNumber = (EditText)findViewById(R.id.editMobileNumber);
        buttonUpdate = (Button) findViewById(R.id.buttonUpdateEmployee);
        buttonPopulateDetails = (Button) findViewById(R.id.buttonPopulate);

        buttonPopulateDetails.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                populateDetails();
            }
        });


        buttonUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                updateUSer();
                Toast.makeText(ModifyEmployee.this,"직원정보가 수정되었습니다",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void populateDetails(){

        emailIdToSearch =( emailID.getText()).toString();
        if(emailIdToSearch.length()==0){
            Toast.makeText(ModifyEmployee.this,"이메일을 입력하세요",Toast.LENGTH_SHORT).show();
        }
        else {
            Log.d("Modify_Employee", emailIdToSearch);

            Cursor userCursor = dataBaseAdapter.getSinlgeUser(emailIdToSearch);

            if(userCursor.getCount()==0) {
                Toast.makeText(ModifyEmployee.this,"해당 직원이 존재하지 않습니다.",Toast.LENGTH_SHORT).show();

            }

            else {
                Log.d("ModifyEmployee", String.valueOf(userCursor.getCount()));
                name.setText(userCursor.getString(userCursor.getColumnIndex(DatabaseManager.USERPROFILE_NAME)));
                password.setText(userCursor.getString(userCursor.getColumnIndex(DatabaseManager.USERPROFILE_PASSWORD)));
                address.setText(userCursor.getString(userCursor.getColumnIndex(DatabaseManager.USERPROFILE_ADDRESS)));
                city.setText(userCursor.getString(userCursor.getColumnIndex(DatabaseManager.USERPROFILE_CITY)));
                zipCode.setText(userCursor.getString(userCursor.getColumnIndex(DatabaseManager.USERPROFILE_ZIPCODE)));
                Country.setText(userCursor.getString(userCursor.getColumnIndex(DatabaseManager.USERPROFILE_COUNTRY)));
                mobileNumber.setText(userCursor.getString(userCursor.getColumnIndex(DatabaseManager.USERPROFILE_NUMBER)));
            }
        }
    }

    public void updateUSer(){

        dataBaseAdapter.updateUSer(emailIdToSearch, (name.getText().toString()),password.getText().toString(),
                address.getText().toString(),
                city.getText().toString(), zipCode.getText().toString(), Country.getText().toString(),
                mobileNumber.getText().toString());


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
                Toast.makeText(ModifyEmployee.this,
                        "로그아웃 되었습니다", Toast.LENGTH_SHORT)
                        .show();

                Intent intentLogout = new Intent(ModifyEmployee.this, LoginActivity.class);
                startActivity(intentLogout);
                return true;
            }

            case android.R.id.home:
            {
                Log.d("CartScreen","Backbutton:");

                Intent intentMenu = new Intent(ModifyEmployee.this, EmployeeTasks.class);
                startActivity(intentMenu);
                return true;
            }

            default: {
                return true;
            }

        }

    }
}