package com.example.class255;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class Luncher extends AppCompatActivity {

    GridView gridView;

    ArrayList <HashMap <String,String>> arrayList = new ArrayList<>();
    HashMap <String,String> hashMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luncher);
        gridView = findViewById(R.id.gridView);

        Adepter myadepter = new Adepter();
        gridView.setAdapter(myadepter);

        hashMap = new HashMap<>();
        hashMap.put("Name","Uzzal & Jasmin");
        hashMap.put("pic_url","https://uzzal10.com/apps/pic/me_jas.JPG");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("Name","Alim & Najma");
        hashMap.put("pic_url","https://uzzal10.com/apps/pic/CAM00669.jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("Name","juwel & Ema");
        hashMap.put("pic_url","https://uzzal10.com/apps/pic/DSC00304.jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("Name","Josim & Taslima");
        hashMap.put("pic_url","https://uzzal10.com/apps/pic/IMG_9458.JPG");
        arrayList.add(hashMap);
    }

    private class Adepter extends BaseAdapter{
        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater layoutInflater= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View myview = layoutInflater.inflate(R.layout.grid_item,parent,false);
            LinearLayout leyitelm = myview.findViewById(R.id.leyitelm);
            TextView gridTv = myview.findViewById(R.id.gridTv);
            ImageView gridPic = myview.findViewById(R.id.gridPic);

            HashMap<String,String> hashMap= arrayList.get(position);
            String myname = hashMap.get("Name");
            gridTv.setText(myname);
            String image = hashMap.get("pic_url");
            Picasso.get().load(image).placeholder(R.drawable.my_pic).into(gridPic);



            leyitelm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(Luncher.this,MainActivity.class));

                }
            });


            return myview;
        }
    }

}