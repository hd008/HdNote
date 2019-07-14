package com.hd.hdnote;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hd.hdnote.Util.Utils;

public class AddActivity extends AppCompatActivity {
    private AlertDialog alertDialog2; //单选框
    private AlertDialog alertDialog3; //单选框


    private Button button2;
    private Button button3;
    private EditText editText;

    private int time = 6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        editText = (EditText) findViewById(R.id.thing);


    }



    public void showSingleAlertDialog(View view){
        final String[] items = {"Today", "Tomorrow", "Everyday"};
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("请选择日期");
        alertBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(AddActivity.this, items[i], Toast.LENGTH_SHORT).show();
                button2.setText((String)items[i]);//显示在button上
            }
        });

        alertBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog2.dismiss();
            }
        });

        alertBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog2.dismiss();
            }
        });

        alertDialog2 = alertBuilder.create();
        alertDialog2.show();
    }

    public void showSingleAlertDialog2(View view){
        final String[] items = {"6","7","8"};
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("请选择时间");
        alertBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(AddActivity.this, items[i], Toast.LENGTH_SHORT).show();
                button3.setText(items[i]);//显示在button上
                time=Integer.parseInt(items[i]);
            }
        });

        alertBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog3.dismiss();
            }
        });

        alertBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog3.dismiss();
            }
        });

        alertDialog3 = alertBuilder.create();
        alertDialog3.show();
    }
    public void submit(View view){
//        int time=Integer.getInteger(String.valueOf(button3.getText()));
        //System.out.println(time);
        Utils.add(this,String.valueOf(button2.getText()),time,String.valueOf(editText.getText()));
        Intent intent1 = new Intent(this,MainActivity.class);
        startActivity(intent1);
    }


}
