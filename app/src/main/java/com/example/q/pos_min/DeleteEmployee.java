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
 * Created by q on 2018-04-22.
 */

public class DeleteEmployee extends AppCompatActivity {
    EditText emailID;
    DatabaseManager databaseAdapter;
    Button delEmp;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete_employee);
        databaseAdapter = DatabaseManager.getInstance(this);
        databaseAdapter.open();
        emailID = (EditText) findViewById(R.id.editTextDeleteEmployee);
        delEmp = (Button) findViewById(R.id.buttonDeleteEmployee);

        delEmp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                callDelete();
                if(i==1){
                    return;
                }
                Toast.makeText(DeleteEmployee.this,"직원정보가 삭제되었습니다.",Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void callDelete(){
        if(emailID.getText().toString().length()==0){
            Toast.makeText(DeleteEmployee.this,"이메일을 입력하세요 ",Toast.LENGTH_SHORT).show();
        }
        else {
            Log.d("Modify_Employee", "Nothing");

            Cursor userCursor = databaseAdapter.getSinlgeUser(emailID.getText().toString());

            if(userCursor.getCount()==0) {
                Toast.makeText(DeleteEmployee.this,"삭제할 직원정보가 없습니다",Toast.LENGTH_SHORT).show();
                i=1;
            }

            else {
                databaseAdapter.deleteUSER(emailID.getText().toString());
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
                Toast.makeText(DeleteEmployee.this,"로그아웃 되었습니다", Toast.LENGTH_SHORT)
                        .show();

                Intent intentLogout = new Intent(DeleteEmployee.this, LoginActivity.class);
                startActivity(intentLogout);
                return true;
            }

            case android.R.id.home:
            {
                Log.d("CartScreen","Backbutton:");

                Intent intentMenu = new Intent(DeleteEmployee.this, EmployeeTasks.class);
                startActivity(intentMenu);
                return true;
            }

            default: {
                return true;
            }
        }
    }
}