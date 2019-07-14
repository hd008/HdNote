package com.hd.hdnote;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;


import com.hd.hdnote.Dao.DatabaseHelper;
import com.hd.hdnote.Dao.Thing;
import com.hd.hdnote.Util.ListViewAdapter;
import com.hd.hdnote.Util.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickListener  {
    private DatabaseHelper dbHelper;

    ListView mylist;
    List<Thing> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper =  DatabaseHelper.getInstance(this);//用getinstance（自己创建的） 初始化数据库 创表
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        mylist = (ListView) findViewById(R.id.mylist);

        list = new ArrayList<>();

        list = Utils.getThing(this);

//        System.out.println(list.get(0).thing);


        ListViewAdapter myAdapter = new ListViewAdapter(list,this);//list 音乐信息


        mylist.setAdapter(myAdapter);

//        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String thing = list.get(i).thing;//获得歌曲的地址
//                Toast.makeText(MainActivity.this, thing, Toast.LENGTH_SHORT).show();
////                Intent intent = new Intent(MainActivity.this,play.class);
////                intent.putExtra("name",name);
////                intent.putExtra("singer",author);
////                startActivity(intent);
//
//
//            }
//        });



        Button add=findViewById(R.id.add);
        Button tomorrow=(Button)findViewById(R.id.tomorrow);

        add.setOnClickListener(this);
        tomorrow.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //根据响应Click的按钮id进行选择操作
        switch(v.getId()){

            case R.id.tomorrow:

                Intent intent1 = new Intent(MainActivity.this,ShowActivity.class);
                intent1.putExtra("table","Tomorrow");
                startActivity(intent1);
                break;
            case R.id.everyday:
                Intent intent2 =new Intent(this,ShowActivity.class);
                intent2.putExtra("table","Everyday");
                startActivity(intent2);

            default:
                break;
        }

    }
}
