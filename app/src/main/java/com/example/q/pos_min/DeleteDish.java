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

public class DeleteDish extends AppCompatActivity {
    EditText dishName;
    DatabaseManager DatabaseAdapter;
    Button delDish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_dish);
        DatabaseAdapter = DatabaseManager.getInstance(this);
        DatabaseAdapter.open();
        dishName = (EditText) findViewById(R.id.editTextDeleteDish);
        delDish = (Button) findViewById(R.id.buttonDeleteDish);

        delDish.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                callDelete();
                Toast.makeText(DeleteDish.this,"메뉴가 삭제되었습니다.",Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void callDelete(){
        if(dishName.getText().toString().length()==0){
            Toast.makeText(DeleteDish.this,"이메일을 입력하세요.",Toast.LENGTH_SHORT).show();
        }

        else {
            Log.d("Modify_Employee", "Nothing");
            Cursor getSingleMenuCursor = DatabaseAdapter.getSingleMenu(dishName.getText().toString());

            if(getSingleMenuCursor.getCount()==0) {
                Toast.makeText(DeleteDish.this,"삭제 할 메뉴가 없습니다.",Toast.LENGTH_SHORT).show();
            }

            else {
                DatabaseAdapter.deleteDish(dishName.getText().toString());
            }
        }
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
                Toast.makeText(DeleteDish.this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT)
                        .show();

                Intent intentLogout = new Intent(DeleteDish.this, LoginActivity.class);
                startActivity(intentLogout);
                return true;
            }

            case android.R.id.home: {
                Log.d("CartScreen","Backbutton:");

                Intent intentMenu = new Intent(DeleteDish.this, DishTasks.class);
                startActivity(intentMenu);
                return true;
            }

            default: {
                return true;
            }
        }
    }
}
