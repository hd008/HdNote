package com.hd.hdnote.Util;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hd.hdnote.Dao.Thing;
import com.hd.hdnote.MainActivity;
import com.hd.hdnote.R;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private List<Thing> list;
    private Context context;
    private String table;


    public ListViewAdapter(List<Thing>list,Context context,String table){
        this.list=list;
        this.context=context;
        this.table=table;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView==null) {
            viewHolder=new ViewHolder();
            convertView =LayoutInflater.from(context).inflate(R.layout.list,null);
            viewHolder.tvContent= (TextView) convertView.findViewById(R.id.tvContent);
            viewHolder.btnDelete= (Button) convertView.findViewById(R.id.btnDelete);
            viewHolder.swipeMenuLayout= (SwipeMenuLayout) convertView.findViewById(R.id.swipeMenuLayout);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.tvContent.setText(list.get(position).time+"  "+list.get(position).thing);

//      点击事件
        final ViewHolder  finalViewHolder= viewHolder;
        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              删除list中对应的数据
                list.remove(position);
               System.out.println("1232321"+context.toString());
               String table;

//              重新绑定数据
                notifyDataSetChanged();
//              关闭侧滑菜单
                finalViewHolder.swipeMenuLayout.quickClose();
            }
        });

        return convertView;
    }
    class ViewHolder{
        SwipeMenuLayout swipeMenuLayout;
        TextView tvContent;
        Button btnDelete;
    }





    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
