package com.hd.hdnote;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hd.hdnote.Dao.DatabaseHelper;
import com.hd.hdnote.Util.Utils;

import java.util.Timer;
import java.util.TimerTask;

public class start extends AppCompatActivity {
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //获取权限
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    return;
                }
            }
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        dbHelper =  DatabaseHelper.getInstance(this);//用getinstance（自己创建的） 初始化数据库 创表
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        final Intent intent = new Intent(this,MainActivity.class);

        Timer timer = new Timer();
        TimerTask tast = new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);
            }
        };
        timer.schedule(tast, 1500);
    }
}
