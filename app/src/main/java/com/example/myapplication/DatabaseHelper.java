package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(1234567890,'Ankit',8000.00,'ankit@gmail.com','XXXXXXXXXXXX3423','CAB21098766')");
        db.execSQL("insert into user_table values(70987654321,'Ranjit',5820.67,'Ranjit.1403@gmail.com','XXXXXXXXXXXX2092','ABC10987090')");
        db.execSQL("insert into user_table values(1265438709,'Piyush',1959.56,'aPiyush4@gmail.com','XXXXXXXXXXXX3417','CAB87654765')");
        db.execSQL("insert into user_table values(6543288776,'Rishik',15000.01,'rishk@gmail.com','XXXXXXXXXXXX4124','ABC43210984')");
        db.execSQL("insert into user_table values(2678901234,'Siddhart',2603.48,'siddhart09@gmail.com','XXXXXXXXXXXX2342','BCA65438768')");
        db.execSQL("insert into user_table values(1789012345,'Dimple',8450.16,'dimple@gmail.com','XXXXXXXXXXXX3459','CAB59871099')");
        db.execSQL("insert into user_table values(3890123456,'Ankush',59836.00,'ankush0809@gmail.com','XXXXXXXXXXXX4525','ABC76545613')");
        db.execSQL("insert into user_table values(6901234567,'Vinit',8570.22,'vinit.0910@gmail.com','XXXXXXXXXXXX5232','BCA32101239')");
        db.execSQL("insert into user_table values(5012345678,'Om',4398.46,'om78@gmail.com','XXXXXXXXXXXX3458','ABC09876093')");
        db.execSQL("insert into user_table values(1234567809,'Ayan',2703.90,'Ayan@gmail.com','XXXXXXXXXXXX4560','BCA98767338')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
