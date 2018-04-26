package com.example.q.pos_min;

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

public class AddDish extends AppCompatActivity {
    EditText dishName,cuisineType,description,price;
    DatabaseManager databaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_dish);

        databaseAdapter = DatabaseManager.getInstance(this);
        databaseAdapter.open();

        dishName = (EditText) findViewById(R.id.editTextAddDishName);
        cuisineType  = (EditText) findViewById(R.id.editTextAddCuisineType);
        description = (EditText) findViewById(R.id.editTextAddDescription);
        price = (EditText) findViewById(R.id.editTextAddPrice);

        Button btnAddDishToMenu = (Button) findViewById(R.id.btnAddDishToMenu);

        btnAddDishToMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                insert();
                Toast.makeText(getApplicationContext(),
                        "메뉴가 등록되었습니다. ", Toast.LENGTH_LONG)
                        .show();

            }
        });

    }

    public void insert(){

        String stringDishName = dishName.getText().toString();
        String stringCuisineType = cuisineType.getText().toString();
        String stringDescription = description.getText().toString();
        String stringPrices = price.getText().toString();

        databaseAdapter.addDishToMenu(stringDishName,stringCuisineType,stringDescription,stringPrices);

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
                Toast.makeText(AddDish.this,
                        "로그아웃 되었습니다.", Toast.LENGTH_SHORT)
                        .show();

                Intent intentLogout = new Intent(AddDish.this, LoginActivity.class);
                startActivity(intentLogout);
                return true;
            }

            case android.R.id.home:
            {
                Log.d("CartScreen","Backbutton:");

                Intent intentMenu = new Intent(AddDish.this, DishTasks.class);
                startActivity(intentMenu);
                return true;
            }

            default: {
                return true;
            }


        }

    }


}
