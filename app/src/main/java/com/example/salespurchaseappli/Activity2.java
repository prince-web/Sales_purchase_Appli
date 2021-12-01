package com.example.salespurchaseappli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {
    String BASE_URL = "http://d67c-223-236-11-228.ngrok.io/posts";
    RequestQueue requestQueue;
    EditText dateEt,timeEt,productEt,priceEt,unitEt,totalQuantity;
    TextView totaltv,status;
    Button savebtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        initialization();
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData();
            }
        });

    }
    private void postData() {
        String date,time,name,unit,typeOfTransaction;
        int pricePerItem,quantity,totalPrice ;


       name = productEt.getText().toString();
        date = dateEt.getText().toString();
        time = timeEt.getText().toString();
        unit = unitEt.getText().toString();
        typeOfTransaction = "Sell";
        pricePerItem = Integer.parseInt(priceEt.getText().toString());
        quantity = Integer.parseInt(totalQuantity.getText().toString());
        totalPrice = quantity * pricePerItem;

        totaltv.setText(totalPrice);


        requestQueue = RequestQueueSingleton.getInstance(getApplicationContext()).getRequestQueue();
        JSONObject object  = new JSONObject();
        try {
            object.put("name",name);
            object.put("pricePerItem",pricePerItem);
            object.put("unit",unit);
            object.put("quantity",quantity);
            object.put("totalPrice",totalPrice);
            object.put("typeOfTransaction",typeOfTransaction);
            object.put("id",date+time);
        }catch (Exception e){
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, BASE_URL, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                status.setText("Successfully");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                status.setText("Something went Wrong!!");
            }
        });
        RequestQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }

    private void initialization() {
        dateEt = findViewById(R.id.sellDate_tv);
        timeEt = findViewById(R.id.sellTime);
        setDateAndTime();

        productEt= findViewById(R.id.ProductName_tv);
        priceEt =findViewById(R.id.price_tv);
        unitEt=findViewById(R.id.unitPerPiece_et);
        totalQuantity=findViewById(R.id.totalQuantity_tv);
        savebtn=findViewById(R.id.button);
        totaltv=findViewById(R.id.total_tv);
        status=findViewById(R.id.result_tv);
    }

    private void setDateAndTime() {
        //set current date and time
        String currentDate = LocalDate.now().toString();
        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")).toString();

        //setting both the date and time
        dateEt.setText(currentDate);
        timeEt.setText(currentTime);
    }

}