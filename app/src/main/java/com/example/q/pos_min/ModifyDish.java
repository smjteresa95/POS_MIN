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

public class ModifyDish extends AppCompatActivity {

    EditText editTextDishName,editTextCuisineType,editTextDescription,editTextPrice;
    Button btnPopulate,btnUpdateDish;
    DatabaseManager dataBaseAdapter;
    String dishNameToSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_dish);  dataBaseAdapter = DatabaseManager.getInstance(this);
        dataBaseAdapter.open();

        editTextDishName  = (EditText)findViewById(R.id.editTextDishName);
        editTextCuisineType     = (EditText)findViewById(R.id.editTextCuisineType);
        editTextDescription = (EditText)findViewById(R.id.editTextDescription) ;
        editTextPrice  = (EditText)findViewById(R.id.editTextPrice);
        btnPopulate = (Button) findViewById(R.id.btnPopulate);
        btnUpdateDish = (Button) findViewById(R.id.btnUpdateDish);


        btnPopulate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                populateDetails();
            }
        });


        btnUpdateDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateDish();
                Toast.makeText(ModifyDish.this,"메뉴가 수정되었습니다.",Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void populateDetails(){
        dishNameToSearch =( editTextDishName.getText()).toString();
        if(dishNameToSearch.length()==0){
            Toast.makeText(ModifyDish.this,"이메일을 입력하세요.",Toast.LENGTH_SHORT).show();
        }
        else {
            Log.d("Modify_Employee", dishNameToSearch);

            Cursor userCursor = dataBaseAdapter.getSingleMenu(dishNameToSearch);

            if(userCursor.getCount()==0) {
                Toast.makeText(ModifyDish.this,"해당 직원이 존재하지 않습니다.",Toast.LENGTH_SHORT).show();

            }

            else {
                Log.d("ModifyEmployee", String.valueOf(userCursor.getCount()));
                editTextCuisineType.setText(userCursor.getString(userCursor.getColumnIndex(DatabaseManager.MENU_CUISINETYPE)));
                editTextDescription.setText(userCursor.getString(userCursor.getColumnIndex(DatabaseManager.MENU_DESCRIPTION)));
                editTextPrice.setText(userCursor.getString(userCursor.getColumnIndex(DatabaseManager.MENU_PRICE)));

            }
        }
    }

    public void updateDish(){

        dataBaseAdapter.updateDish(dishNameToSearch,
                editTextCuisineType.getText().toString(),
                editTextPrice.getText().toString(),
                editTextDescription.getText().toString()
        );

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
                Toast.makeText(ModifyDish.this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT)
                        .show();

                Intent intentLogout = new Intent(ModifyDish.this, LoginActivity.class);
                startActivity(intentLogout);
                return true;
            }

            case android.R.id.home:
            {
                Log.d("CartScreen","Backbutton:");

                Intent intentMenu = new Intent(ModifyDish.this, DishTasks.class);
                startActivity(intentMenu);
                return true;
            }

            default: {
                return true;
            }
        }
    }
}