package com.hd.hdnote.Util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hd.hdnote.Dao.DatabaseHelper;
import com.hd.hdnote.Dao.Thing;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<Thing> list;

    public static Thing thing;

    private static DatabaseHelper dbHelper;


    public static List<Thing> getThing(Context context) {



        dbHelper = DatabaseHelper.getInstance(context);//数据库传递 context

        list = new ArrayList<>();

        //Int size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));
        //查询数据库
        SQLiteDatabase db = dbHelper.getWritableDatabase();

            Cursor cursor = db.rawQuery("select * from Today order by time", null);

            while (cursor.moveToNext()) {

                thing = new Thing();
                thing.time= cursor.getInt(cursor.getColumnIndex("time"));
                thing.thing= cursor.getString(cursor.getColumnIndex("thing"));

                list.add(thing);



            }

            cursor.close();



        return list;


    }

    public static List<Thing> getThing(Context context,String table) {



        dbHelper = DatabaseHelper.getInstance(context);//数据库传递 context

        list = new ArrayList<>();

        //Int size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));
        //查询数据库
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from "+table+" order by time", null);

        while (cursor.moveToNext()) {

            thing = new Thing();
            thing.time= cursor.getInt(cursor.getColumnIndex("time"));
            thing.thing= cursor.getString(cursor.getColumnIndex("thing"));

           // System.out.println("get"+cursor.getString(cursor.getColumnIndex("date")));

            list.add(thing);


        }

        cursor.close();
        return list;
    }

    public static void delete(Context context,String table,String thing) {



        dbHelper = DatabaseHelper.getInstance(context);//数据库传递 context

        list = new ArrayList<>();

        //Int size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));
        //查询数据库

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("delete from "+table+" where thing="+thing);

    }
}
