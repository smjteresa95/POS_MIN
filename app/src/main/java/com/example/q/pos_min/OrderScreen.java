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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by q on 2017-04-22.
 */

public class OrderScreen extends AppCompatActivity {

    private Cursor c;
    DatabaseManager DatabaseManager;
    TextView tvUserIdValue,tvOrderIdValue,tvAddress,tvTimeStamp;

    static String[] cartItems,itemname,itemquantity;

    OrderAdapter orderAdapter;
    ListView orderDescription;
    Button bRemove;

    int i;
    int j=0,k=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("OrderScreen","onCreate()");

        setContentView(R.layout.activity_order_screen);
        DatabaseManager = DatabaseManager.getInstance(this);
        DatabaseManager = DatabaseManager.open();
        tvUserIdValue = (TextView)findViewById(R.id.orderUserIdValue);
        tvOrderIdValue = (TextView)findViewById(R.id.orderOrderIdValue);
        tvAddress = (TextView)findViewById(R.id.orderAddressValue);
        tvTimeStamp = (TextView)findViewById(R.id.orderTimeStampValue);
        bRemove = (Button) findViewById(R.id.buttonRemove);

        Log.d("OrderScreen","GettingIntent");


        Intent intent = getIntent();
        String orderID = intent.getStringExtra("주문번호");
        Log.d("OrderScreen","listview obtained in the variable "+orderID);

        c = DatabaseManager.getSingleOrder(orderID);

        c.moveToFirst();

        Log.d("OrderScreen","Cursor value "+c.getString(c.getColumnIndex(DatabaseManager.ORDER_DESCRIPTION)));

        orderDescription = (ListView)findViewById(R.id.listViewDescription);
        tvUserIdValue.setText(c.getString(c.getColumnIndex(com.example.q.pos_min.DatabaseManager.ORDER_USERNAME)));
        tvOrderIdValue.setText(c.getString(c.getColumnIndex(com.example.q.pos_min.DatabaseManager.ORDER_ID)));
        tvAddress.setText(c.getString(c.getColumnIndex(com.example.q.pos_min.DatabaseManager.ORDER_ADDRESS)));
        tvTimeStamp.setText(c.getString(c.getColumnIndex(com.example.q.pos_min.DatabaseManager.ORDER_TIMESTAMP)));

        cartItems = c.getString(c.getColumnIndex(com.example.q.pos_min.DatabaseManager.ORDER_DESCRIPTION)).split(" ");
        Log.d("OrderScreen","cartItems "+cartItems);
        itemname = new String[cartItems.length/2];
        itemquantity= new String[cartItems.length/2];

        for(i=0;i<cartItems.length;i++){
            if(i%2==0){
                Log.d("OrderScreen","Even "+cartItems[i]);

                itemname[j]=cartItems[i];
                j++;}
            else
            {
                Log.d("OrderScreen","ODD "+cartItems[i]);
                itemquantity[k]=cartItems[i];
                k++;
            }
        }

        orderAdapter=new OrderAdapter(this,R.layout.order_description_list_view,itemname);
        orderDescription.setAdapter(orderAdapter);

        bRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseManager.removeOrder(c.getString(c.getColumnIndex(com.example.q.pos_min.DatabaseManager.ORDER_ID)));
                Intent intentChefhome = new Intent(OrderScreen.this, ChefHome.class);
                startActivity(intentChefhome);
            }
        });

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
                Toast.makeText(OrderScreen.this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT)
                        .show();

                Intent intentLogout = new Intent(OrderScreen.this, LoginActivity.class);
                startActivity(intentLogout);
                return true;
            }

            case android.R.id.home : {

                Intent intentLogout = new Intent(OrderScreen.this, ChefHome.class);
                startActivity(intentLogout);
                return true;
            }

            default: {
                return true;
            }


        }

    }
}

