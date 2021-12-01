package com.example.salespurchaseappli;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Model> datalist = new ArrayList<>();
    private ScreenItemAdapter mAdapter;
    private RecyclerView myRv;
    String URL = "http://d67c-223-236-11-228.ngrok.io/";

    private String date;
    private String time;
    private String name;
    private String unit;
    private int quantity;
    private int totalPrice;
    private String typeOfTransaction;


Button sellBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myRv = findViewById(R.id.listOfItem);


         sellBtn = findViewById(R.id.sell_btn);

        parseApiData();

        sellBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellActivity();
            }
        });
        /*mNumbersList = (RecyclerView) findViewById(R.id.listOfItem);

        LinearLayoutManager layoutManager  = new LinearLayoutManager(this);
        mNumbersList.setLayoutManager(layoutManager);

        mNumbersList.setHasFixedSize(true);

        mAdapter = new ScreenItemAdapter(NUM_LIST_ITEMS);

        mNumbersList.setAdapter(mAdapter);*/


    }

    public void parseApiData(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String quantity1,totalPrice1;
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    date = jsonObject.getString("date");
                    time = jsonObject.getString("time");
                    name = jsonObject.getString("name");
                    unit = jsonObject.getString("unit");
                    quantity1 = jsonObject.getString("quantity");
                    totalPrice1 = jsonObject.getString("totalPrice");
                    quantity = Integer.parseInt(quantity1);
                    totalPrice = Integer.parseInt(totalPrice1);
                    typeOfTransaction= jsonObject.getString("typeOfTransaction");

                    datalist.add(new Model(date,time,name,unit,quantity,totalPrice,typeOfTransaction));

                    mAdapter = new ScreenItemAdapter(datalist,MainActivity.this);
                    myRv.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false));
                    myRv.setAdapter(mAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void sellActivity() {
        Intent intent = new Intent(this,Activity2.class);
        startActivity(intent);
    }
}