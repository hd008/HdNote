package com.hd.hdnote.Dao;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static DatabaseHelper instance;

    //带全部参数的构造函数，此构造函数必不可少
    public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据库sql语句 并 执行
        String sql = "create table Today(time int(20),thing varchar(20),ok int(10))";
        String sql1 = "create table Everyday(time int(20),thing varchar(20))";
        String sql2 = "create table Tomorrow(time int(20),thing varchar(20),date varchar(20))";

        String sql3 = "insert into  Today(time,thing,ok) values(1,'today测试',0)";



        String sql4 = "insert into  Everyday(time,thing) values(1,'everyday测试')";

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       // System.out.println(sdf.format(date));

        String sql5 = "insert into  Tomorrow(time,thing,date) values(1,'tomorrow测试','"+sdf.format(date)+"')";

        String sql6 = "insert into  Today(time,thing,ok) values(16,'today测试2',0)";


        db.execSQL(sql);
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL(sql4);
        db.execSQL(sql5);
        db.execSQL(sql6);



    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    //
    public static DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context, "hdnote.db", null, 2);
        }
        return instance;//保持该数据库

    }

}