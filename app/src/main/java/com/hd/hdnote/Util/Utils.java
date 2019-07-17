package com.hd.hdnote.Util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hd.hdnote.Dao.DatabaseHelper;
import com.hd.hdnote.Dao.Thing;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Queue;

public class Utils {

    public static List<Thing> list;

    public static Thing thing;

    private static DatabaseHelper dbHelper;
    public  static void init(Context context){

        dbHelper = DatabaseHelper.getInstance(context);//数据库传递 context

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //判断是否为新的一天 如果是则将 today清空
        Cursor cursor3 = db.rawQuery("select * from tbdate",null);
        while(cursor3.moveToNext()){
            String date = cursor3.getString(cursor3.getColumnIndex("date"));
            SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd"); //加上时间
            //必须捕获异常
            try {//判断日期大小
                Date fdate=sDateFormat.parse(date);
                Date odate=new Date();
                Calendar aCalendar = Calendar.getInstance();
                aCalendar.setTime(fdate);
                int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
                aCalendar.setTime(odate);
                int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
                int days=day2-day1;

                if(days>0){
                    db.execSQL("delete from Today where 1=1");
                }
                db.execSQL("delete from tbdate where 1=1");

                db.execSQL("insert into  tbdate(date) values('"+sDateFormat.format(odate)+"')");


            }
                catch(ParseException px) {
                px.printStackTrace();
            }
        }


        Cursor cursor=db.rawQuery("select * from Everyday  ",null);
//如果everyday没加过则添加进去
        while (cursor.moveToNext()){
            int id =cursor.getInt(cursor.getColumnIndex("id"));
            Cursor cursor2=db.rawQuery("select * from Today where id=?",new String[]{id+""});
            if(!cursor2.moveToNext()){//判断是否添加过,没有则添加
            int time=cursor.getInt(cursor.getColumnIndex("time"));
            String thing = cursor.getString(cursor.getColumnIndex("thing"));
            String sql= "insert into  Today(id,time,thing,ok) values("+id+","+time+",'"+thing+"',0)";
            db.execSQL(sql);}
        }



        //如果tomorrow没加过 则添加进去
        Cursor cursor1=db.rawQuery("select * from Tomorrow",null);
        while (cursor.moveToNext()){
            String strDate=cursor1.getString(cursor1.getColumnIndex("date"));
            //注意：SimpleDateFormat构造函数的样式与strDate的样式必须相符
            SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //加上时间
            //必须捕获异常
            try {//判断日期大小
                Date date=sDateFormat.parse(strDate);
                Date date1=new Date();
                int i=date1.compareTo(date);//比较现在时间和数据库存的时间 大于则为1
                System.out.println(i);
                if(i>0){
                    int id =cursor.getInt(cursor.getColumnIndex("id"));


                    int time=cursor.getInt(cursor.getColumnIndex("time"));
                    String thing = cursor.getString(cursor.getColumnIndex("thing"));
                    String sql1= "insert into  Today(id,time,thing,ok) values("+id+","+time+",'"+thing+"',0)";
                    db.execSQL(sql1);

                    delete(context,"Tomorrow",id);
                }
            } catch(ParseException px) {
                px.printStackTrace();
            }
        }
        System.out.println("初始化数据库判断完成");


    }


    public static List<Thing> getThing(Context context) {



        dbHelper = DatabaseHelper.getInstance(context);//数据库传递 context

        list = new ArrayList<>();

        //Int size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));
        //查询数据库
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor=db.rawQuery("select * from Today order by time ",null);


            while (cursor.moveToNext()) {

                    thing = new Thing();
                    thing.id=cursor.getInt(cursor.getColumnIndex("id"));
                    thing.time= cursor.getInt(cursor.getColumnIndex("time"));
                    thing.thing= cursor.getString(cursor.getColumnIndex("thing"));
                    if(cursor.getInt(cursor.getColumnIndex("ok"))==0)
                        {
                        list.add(thing);}


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
            thing.id=cursor.getInt(cursor.getColumnIndex("id"));

            thing.time= cursor.getInt(cursor.getColumnIndex("time"));
            thing.thing= cursor.getString(cursor.getColumnIndex("thing"));

           // System.out.println("get"+cursor.getString(cursor.getColumnIndex("date")));

            list.add(thing);


        }

        cursor.close();
        System.out.println("列表获取完成 导入适配器成功");
        return list;

    }

    public static void delete(Context context,String table,int id) {



        dbHelper = DatabaseHelper.getInstance(context);//数据库传递 context
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        //Int size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));
        //查询数据库

        if(table.contains("Today")){
            db.execSQL("update Today set ok=1 where id="+id);
        }
        else {

        String[] whereArgs = {String.valueOf(id)};
        db.delete(table,"id=?",whereArgs);}
    }

    public static  void add(Context context,String table,int time,String thing){
//        dbHelper = DatabaseHelper.getInstance(context);
//        SQLiteDatabase db=dbHelper.getWritableDatabase();
//        int x=(int)(Math.random()*1000);
//
//        String test="测试"+x;
//
//        db.execSQL("insert into  Today(id,time,thing,ok) values("+x+",6,'"+test+"',0)");


        dbHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        int id=(int)(Math.random()*1000);
        String sql6=null;
        if(table.contains("Today")){
            sql6 = "insert into  Today(id,time,thing,ok) values("+id+","+time+",'"+thing+"',0)";

        }
        if(table.contains("Tomorrow")) {

//            Date date = new Date();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Format f = new SimpleDateFormat("yyyy-MM-dd");

            Date today = new Date();
            System.out.println("今天是:" + f.format(today));

            Calendar c = Calendar.getInstance();
            c.setTime(today);
            c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天

            Date tomorrow = c.getTime();
            System.out.println("明天是:" + f.format(tomorrow));


            sql6 = "insert into  Tomorrow(id,time,thing,date) values("+id+","+time+",'"+thing+"','"+f.format(tomorrow)+"')";
        }
        if(table.contains("Everyday")){

            sql6 = "insert into  Everyday(id,time,thing) values("+id+","+time+",'"+thing+"')";


        }
        // System.out.println(sdf.format(date));


        db.execSQL(sql6);
    }
}
