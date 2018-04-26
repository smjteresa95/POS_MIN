package com.example.q.pos_min;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.preference.Preference;
import android.util.Log;

import java.util.Iterator;

/**
 * Created by q on 2017-04-22.
 */

public class DatabaseManager {

    static final String DATABASE_NAME = "restaurant.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    public final static String USERPROFILE_TABLE = "userProfile";


    public final static String USERPROFILE_ID = "_id";
    public final static String USERPROFILE_EMAIL = "email";
    public final static String USERPROFILE_PASSWORD = "password";
    public final static String USERPROFILE_NAME = "name";
    public final static String USERPROFILE_ADDRESS = "address";
    public final static String USERPROFILE_CITY = "city";
    public final static String USERPROFILE_STATE = "state";
    public final static String USERPROFILE_ZIPCODE = "zipCode";
    public final static String USERPROFILE_COUNTRY = "country";
    public final static String USERPROFILE_NUMBER = "phoneNumber";
    public final static String USERPROFILE_ROLE = "role";


    public final static String MENU_TABLE = "menu";
    public final static String MENU_ID = "_id";
    public final static String MENU_CUISINETYPE = "cuisinetype";
    public final static String MENU_DISHES = "dishes";
    public final static String MENU_PRICE = "price";
    public final static String MENU_DESCRIPTION = "description";


    public final static String ORDER_TABLE = "myOrder";
    public final static String ORDER_ID = "_id";
    public final static String ORDER_TIMESTAMP = "orderTimestamp";
    public final static String ORDER_USERNAME = "orderUsername";
    public final static String ORDER_DESCRIPTION = "orderDescription";
    public final static String ORDER_ESTIMATED_DELIVERY_TIME = "orderEstimatedDeliveryTime";
    public final static String ORDER_AMOUNT = "amount";
    public final static String ORDER_ADDRESS = "orderAddress";


    static final String DATABASE_CREATE_USERPROFILE = "create table if not exists " + USERPROFILE_TABLE + " ( "
            + USERPROFILE_ID + " INTEGER primary key autoincrement,"
            + USERPROFILE_EMAIL + " text, " + USERPROFILE_NAME + " text, " + USERPROFILE_PASSWORD + " text," +
            USERPROFILE_ADDRESS + " text, " + USERPROFILE_CITY + " text, " + USERPROFILE_STATE + " text," + USERPROFILE_ZIPCODE + " text, "
            + USERPROFILE_COUNTRY + " text, " + USERPROFILE_NUMBER + " text, " + USERPROFILE_ROLE + " text);";

    static final String DATABASE_CREATE_MENU = "create table if not exists " + MENU_TABLE + " ( "
            + MENU_ID + " INTEGER primary key autoincrement,"
            + MENU_CUISINETYPE + " text, " + MENU_DISHES + "  text, " + MENU_PRICE + " INTEGER," + MENU_DESCRIPTION + " text );";

    static final String DATABASE_CREATE_ORDER = "create table if not exists " + ORDER_TABLE + " ( "
            + ORDER_ID + " INTEGER primary key autoincrement,"
            + ORDER_TIMESTAMP + " text," + ORDER_USERNAME + " text," + ORDER_DESCRIPTION + " text,"
            + ORDER_ESTIMATED_DELIVERY_TIME + " text," + ORDER_ADDRESS + " text ," + ORDER_AMOUNT + " INTEGER );";



    public SQLiteDatabase db;
    private Context mContext;
    private DatabaseHelper dbHelper;
    private static DatabaseManager mUniqueInstance;

    public static DatabaseManager getInstance(Context context) {
        Log.d("DatabaseManager", DATABASE_CREATE_ORDER);

        if (mUniqueInstance == null) {
            mUniqueInstance = new DatabaseManager(context);
        }
        return mUniqueInstance;
    }

    private DatabaseManager(Context context) {
        mContext = context;
        dbHelper = new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Create Table Query ", DATABASE_CREATE_USERPROFILE);

        Log.d("DatabaseManager", "ORDER table query  : " + DATABASE_CREATE_ORDER);

        String s = String.valueOf(Preference.getInstance(mContext).loadBooleanKey(Preference.IS_FIRST_TIME, false));

        Log.d("Preferenece Value", s);

        db = dbHelper.getWritableDatabase();
        if (!Preference.getInstance(mContext).loadBooleanKey(Preference.IS_FIRST_TIME, false)) {
            Log.d("DatabaseAdpater", "Inside prefrence if");
            initialisingDataBase();

            Preference.getInstance(mContext).saveState(Preference.IS_FIRST_TIME, true);

        }
    }

    public void initialisingDataBase() {
        Log.d("getSinlgeUser", "intialising data");
        ContentValues newValues1 = new ContentValues();
        newValues1.put(USERPROFILE_NAME, "MIN");
        newValues1.put(USERPROFILE_EMAIL, "MIN");
        newValues1.put(USERPROFILE_PASSWORD, "MIN");
        newValues1.put(USERPROFILE_ROLE, "ADMIN");
        db.insert(USERPROFILE_TABLE, null, newValues1);

        ContentValues newValues2 = new ContentValues();
        newValues2.put(MENU_CUISINETYPE, "에피타이저");
        newValues2.put(MENU_DISHES, "시저샐러드");
        newValues2.put(MENU_PRICE, 15000);
        newValues2.put(MENU_DESCRIPTION, " ");
        db.insert(MENU_TABLE, null, newValues2);

        ContentValues newValues3 = new ContentValues();
        newValues3.put(MENU_CUISINETYPE, "에피타이저");
        newValues3.put(MENU_DISHES, "카프리제");
        newValues3.put(MENU_PRICE, 10000);
        newValues3.put(MENU_DESCRIPTION, "생 모짜렐라 치즈, 토마토와 바질을 발사믹소스와 곁들임");
        db.insert(MENU_TABLE, null, newValues3);


        ContentValues newValues4 = new ContentValues();
        newValues4.put(MENU_CUISINETYPE, "에피타이저");
        newValues4.put(MENU_DISHES, "오늘의 스프");
        newValues4.put(MENU_PRICE, 9000);
        newValues4.put(MENU_DESCRIPTION, "빵과 함꼐 제공");
        db.insert(MENU_TABLE, null, newValues4);


        ContentValues newValues5 = new ContentValues();
        newValues5.put(MENU_CUISINETYPE, "메인디쉬");
        newValues5.put(MENU_DISHES, "마르게리따 피자");
        newValues5.put(MENU_PRICE, 14000);
        newValues5.put(MENU_DESCRIPTION, "전형적인 나폴리피자" + "토마토와 바질과 모짜렐라 치즈");
        db.insert(MENU_TABLE, null, newValues5);

        ContentValues newValues6 = new ContentValues();
        newValues6.put(MENU_CUISINETYPE, "메인디쉬");
        newValues6.put(MENU_DISHES, "라자냐");
        newValues6.put(MENU_PRICE, 14000);
        newValues6.put(MENU_DESCRIPTION, "소고기와 토마토소스, 치즈를 곁들임");
        db.insert(MENU_TABLE, null, newValues6);

        ContentValues newValues7 = new ContentValues();
        newValues7.put(MENU_CUISINETYPE, "메인디쉬");
        newValues7.put(MENU_DISHES, "안심 스테이크");
        newValues7.put(MENU_PRICE, 45000);
        newValues7.put(MENU_DESCRIPTION, "국내산 A++한우, 하루 10서빙 한정판매");
        db.insert(MENU_TABLE, null, newValues7);


        ContentValues newValues8 = new ContentValues();
        newValues8.put(MENU_CUISINETYPE, "메인디쉬");
        newValues8.put(MENU_DISHES, "해산물 파스타");
        newValues8.put(MENU_PRICE, 140000);
        newValues8.put(MENU_DESCRIPTION, "새우, 홍합, 전복이 들어감");
        db.insert(MENU_TABLE, null, newValues8);

        ContentValues newValues9 = new ContentValues();
        newValues9.put(MENU_CUISINETYPE, "음료");
        newValues9.put(MENU_DISHES, "탄산수");
        newValues9.put(MENU_PRICE, 3000);
        newValues9.put(MENU_DESCRIPTION, ".");
        db.insert(MENU_TABLE, null, newValues9);

        ContentValues newValues10 = new ContentValues();
        newValues10.put(MENU_CUISINETYPE, "음료");
        newValues10.put(MENU_DISHES, "에이드");
        newValues10.put(MENU_PRICE, 5000);
        newValues10.put(MENU_DESCRIPTION, "자몽, 레몬 중 택 1");
        db.insert(MENU_TABLE, null, newValues10);


        /** Inserting the CHEFS of Restaurant
        **/

        ContentValues newValues12 = new ContentValues();
        newValues12.put(USERPROFILE_NAME, "SONG");
        newValues12.put(USERPROFILE_EMAIL, "SONG");
        newValues12.put(USERPROFILE_PASSWORD, "SONG");
        newValues12.put(USERPROFILE_ROLE, "CHEF");
        db.insert(USERPROFILE_TABLE, null, newValues12);

        ContentValues newValues13 = new ContentValues();
        newValues13.put(USERPROFILE_NAME, "KANG");
        newValues13.put(USERPROFILE_EMAIL, "KANG");
        newValues13.put(USERPROFILE_PASSWORD, "KANG");
        newValues13.put(USERPROFILE_ROLE, "CHEF");
        db.insert(USERPROFILE_TABLE, null, newValues13);

    }

    public DatabaseManager open() throws SQLException {
        return this;
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    /* THIS FUNCTION WILL INSERT USER FROM SIGNUP THE DATABASE */
    public void insertEntry(String userName, String userId, String password, String address,
                            String city, String zipCode, String country,String number, String role) {


        ContentValues newValues = new ContentValues();
        newValues.put(USERPROFILE_NAME, userName);
        newValues.put(USERPROFILE_EMAIL, userId);
        newValues.put(USERPROFILE_PASSWORD, password);
        newValues.put(USERPROFILE_ADDRESS, address);
        newValues.put(USERPROFILE_CITY, city);
        newValues.put(USERPROFILE_ZIPCODE, zipCode);
        newValues.put(USERPROFILE_COUNTRY, country);
        newValues.put(USERPROFILE_NUMBER, number);
        newValues.put(USERPROFILE_ROLE, role);
        db.insert(USERPROFILE_TABLE, null, newValues);

    }


    public void deleteUSER(String UserName) {

        String where = USERPROFILE_NAME+"=?";
        db.delete(USERPROFILE_TABLE, where,
                new String[]{UserName});
        return ;
    }


    public String getPassword(String emailID) {
        Cursor cursor = db.query(USERPROFILE_TABLE, null, USERPROFILE_EMAIL+"=?",
                new String[]{emailID}, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String Row = cursor.getString(cursor.getColumnIndex(USERPROFILE_PASSWORD));
        cursor.close();
        return Row;
    }


    public Cursor getSinlgeUser(String emailID) {
        Cursor cursor = db.query("userProfile", null, "email =?",
                new String[]{emailID}, null, null, null);


        /*if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        }*/
        cursor.moveToFirst();
        /*String Row = cursor.getString(cursor.getColumnIndex(USERPROFILE_NAME))
                + " " + cursor.getString(cursor.getColumnIndex(USERPROFILE_EMAIL))
                + " " + cursor.getString(cursor.getColumnIndex(USERPROFILE_PASSWORD))
                + " " + cursor.getString(cursor.getColumnIndex(USERPROFILE_ADDRESS))
                + " " + cursor.getString(cursor.getColumnIndex(USERPROFILE_CITY))
                + " " + cursor.getString(cursor.getColumnIndex(USERPROFILE_STATE))
                + " " + cursor.getString(cursor.getColumnIndex(USERPROFILE_COUNTRY))
                + " " + cursor.getString(cursor.getColumnIndex(USERPROFILE_NUMBER))
                + " " + cursor.getString(cursor.getColumnIndex(USERPROFILE_ROLE)) ;*/
        //cursor.close();
        return cursor;
    }


    public void updateUSer(String userID, String userName,String password, String address, String city, String zipcode,
                           String country, String number) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put(USERPROFILE_NAME, userName);
        updatedValues.put(USERPROFILE_PASSWORD, password);
        updatedValues.put(USERPROFILE_ADDRESS, address);
        updatedValues.put(USERPROFILE_CITY, city);
        updatedValues.put(USERPROFILE_ZIPCODE, zipcode);
        updatedValues.put(USERPROFILE_COUNTRY, country);
        updatedValues.put(USERPROFILE_NUMBER, number);

        String where = USERPROFILE_EMAIL +" = ?";
        db.update(USERPROFILE_TABLE, updatedValues, where, new String[]{userID});
    }


    public Cursor getMenu() {

        String orderBy =  MENU_CUISINETYPE + " DESC";

        Cursor cursor = db.query(MENU_TABLE, null, null ,
                null , null, null, orderBy);
        cursor.moveToFirst();
        String s = String.valueOf(cursor);
        Log.d("Cursor query value",cursor.getString(cursor.getColumnIndex(MENU_DESCRIPTION)));


        //MENUID" + " INTEGER primary key autoincrement,"
        //+ "CUISINETYPE text,DISHES  text,PRICE INTEGER,DESCRIPTION text)";

        /*cursor.moveToFirst();
        String  Row= cursor.getString(cursor.getColumnIndex("MENUID"))+" "+cursor.getString(cursor.getColumnIndex("USERID"))
                +" "+cursor.getString(cursor.getColumnIndex("PASSWORD"))+" "+cursor.getString(cursor.getColumnIndex("ADDRESS"))
                +" "+cursor.getString(cursor.getColumnIndex("STREETNAME"))+" "+cursor.getString(cursor.getColumnIndex("CITY"))
                +" "+cursor.getString(cursor.getColumnIndex("ZIPCODE"))+" "+cursor.getString(cursor.getColumnIndex("COUNTRY"));
        cursor.close();*/

        return cursor;
    }

    public void updateDish(String dishName, String cuisineType, String price, String description) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put(MENU_CUISINETYPE, cuisineType);
        updatedValues.put(MENU_PRICE, price);
        updatedValues.put(MENU_DESCRIPTION, description);

        String where = MENU_DISHES +" = ?";
        db.update(MENU_TABLE, updatedValues, where, new String[]{dishName});
    }


    public void addDishToMenu(String dishName , String cuisineType, String description, String price){

        ContentValues newValues9 = new ContentValues();
        newValues9.put(MENU_CUISINETYPE, cuisineType);
        newValues9.put(MENU_DISHES, dishName);
        newValues9.put(MENU_PRICE, price);
        newValues9.put(MENU_DESCRIPTION, description);
        db.insert(MENU_TABLE, null, newValues9);

    }

    public void deleteDish(String dishName) {

        String where = MENU_DISHES+"=?";
        db.delete(MENU_TABLE, where,
                new String[]{dishName});
        return ;
    }



    public Cursor getSingleMenu(String dishName) {

        Cursor cursor = db.query(MENU_TABLE , null, MENU_DISHES+" =?",
                new String[]{dishName}, null, null, null);

        cursor.moveToFirst();
        String s = String.valueOf(cursor);

        return cursor;
    }

    public Cursor getOrder() {
        Log.d("Cursor query value","Inside cursor");

        String orderBy =  ORDER_ID + " ASC";

        Cursor cursor = db.query(ORDER_TABLE, null, null ,
                null , null, null, orderBy);

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false)
        {
            Log.d("Cursor query value",cursor.getString(cursor.getColumnIndex(ORDER_ID)));
            cursor.moveToNext();
        }
        //Log.d("Cursor query value",cursor.getString(cursor.getColumnIndex(ORDER_ID)));

        cursor.moveToFirst();
        return cursor;
    }


    public Cursor getSingleOrder(String orderId) {
        Log.d("Cursor query value","Inside cursor");

        Cursor cursor = db.query(ORDER_TABLE, null, ORDER_ID+" =?",
                new String[]{orderId}, null, null, null);

        cursor.moveToFirst();
        return cursor;
    }


    public void insertOrder(){

        String Description="";

        for(Iterator<Cart> it = Order.mCartItems.iterator(); it.hasNext();) {
            Cart crt = it.next();

            Description = Description+crt.getDishName().replaceAll(" ","")+" "+crt.getDishQuantity()+" ";
            //Log.d("DatabaseManager",String.valueOf(crt.getDishName()) );
            Log.d("DatabaseManager",String.valueOf(crt.getDishQuantity()) );
        }

        Log.d("DatabaseManager","Description"+Description );

        ContentValues newValues18 = new ContentValues();
        newValues18.put(ORDER_TIMESTAMP, Order.mTimeStamp);
        newValues18.put(ORDER_USERNAME, Order.mUsername);
        newValues18.put(ORDER_DESCRIPTION,Description);
        newValues18.put(ORDER_ESTIMATED_DELIVERY_TIME, Order.mEstimatedDeliveryTime);
        newValues18.put(ORDER_AMOUNT, Order.mTotalPrice);
        newValues18.put(ORDER_ADDRESS,Order.mAddress);
        db.insert(ORDER_TABLE, null, newValues18);

    }

    public void removeOrder(String orderId)
    {
        String where = ORDER_ID+"=?";
        db.delete(ORDER_TABLE, where, new String[]{orderId});
        // return db.delete(ORDER_ID, ORDER_ID + "=" + orderId, null) > 0;
    }


}
