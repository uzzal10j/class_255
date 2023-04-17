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
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    GridView listView;
    ProgressBar progressBar;

    TextView textView;

    ArrayList <HashMap<String,String>> arrayList = new ArrayList<>();
    HashMap<String,String> hashMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        progressBar =findViewById(R.id.progressBar);





        String url = "https://uzzal10.com/apps/my_video.json";

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                progressBar.setVisibility(View.GONE);

                try {

                    for (int x=0;x<response.length();x++){

                        JSONObject jsonObject = response.getJSONObject(x);
                        String titel = jsonObject.getString("titel");
                        String video_id = jsonObject.getString("video_id");

                       //textView.append(x + "."+titel+"\n"+video_id+"\n"+"\n");
                        hashMap = new HashMap<>();
                        hashMap.put("titel",titel);
                        hashMap.put("video_id",video_id);
                        arrayList.add(hashMap);

                    }

                    MyAdapter myAdapter = new MyAdapter();
                    listView.setAdapter(myAdapter);


                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                progressBar.setVisibility(View.GONE);
            }
        });


        RequestQueue requestQueue =Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(arrayRequest);
    }



private class MyAdapter extends BaseAdapter{


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

        LayoutInflater layoutInflater = getLayoutInflater();
        View myView = layoutInflater.inflate(R.layout.item,null);
        TextView tvtitel = myView.findViewById(R.id.tvTitel);
        ImageView imageView = myView.findViewById(R.id.imageView);
        LinearLayout leyItem =myView.findViewById(R.id.leyItem);

        HashMap<String,String> hashMap = arrayList.get(position);
        String titel =hashMap.get("titel");
        String video_id =hashMap.get("video_id");

        String image_url = "https://img.youtube.com/vi/"+video_id+"/0.jpg";

        tvtitel.setText(titel);
        Picasso.get().load(image_url).placeholder(R.drawable.my_pic).into(imageView);



        leyItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player.videod=video_id;
                startActivity(new Intent(MainActivity.this,Player.class));

            }
        });



        return myView;
    }
}

}