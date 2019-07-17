package com.hd.hdnote;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.hd.hdnote.Dao.DatabaseHelper;
import com.hd.hdnote.Dao.Thing;
import com.hd.hdnote.Util.ListViewAdapter;
import com.hd.hdnote.Util.Utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;

    ListView mylist;
    List<Thing> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Intent intent =getIntent();
        String table=intent.getStringExtra("table");

        dbHelper =  DatabaseHelper.getInstance(this);//用getinstance（自己创建的） 初始化数据库 创表
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        mylist = (ListView) findViewById(R.id.mylist);

        list = new ArrayList<>();

        list = Utils.getThing(this,table);

//        System.out.println(list.get(0).thing);


        ListViewAdapter myAdapter = new ListViewAdapter(list,this,table);//list 音乐信息


        mylist.setAdapter(myAdapter);

    }
    public void add(View view){
        Intent intent3 = new Intent(this,AddActivity.class);
        startActivity(intent3);
    }
    public void back(View view){
        Intent intent = new Intent(this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);//跳转到主页面
        startActivity(intent);//并且清空之前activity栈
    }

}
