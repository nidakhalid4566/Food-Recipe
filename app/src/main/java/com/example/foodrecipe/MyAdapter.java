package com.example.foodrecipe;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    Context context;
    ArrayList<ResClass> arrayList;
    public MyAdapter(Context context,ArrayList<ResClass> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }
    public void update(ArrayList<ResClass> result){
        arrayList=new ArrayList<>();
        arrayList.addAll(result);
        notifyDataSetChanged();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.display,null);
        ImageView imageView=(ImageView)convertView.findViewById(R.id.i1);
        TextView textView=(TextView)convertView.findViewById(R.id.t1);

        ResClass resClass=arrayList.get(position);

        textView.setText(resClass.getName());
        Uri imageUri = Uri.parse(resClass.getImgurl());
        imageView.setImageURI(imageUri);

        return convertView;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }
}

