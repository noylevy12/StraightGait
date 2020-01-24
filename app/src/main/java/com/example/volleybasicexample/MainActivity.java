package com.example.volleybasicexample;

//import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private Button btnRequest;
    private ImageView imgFoot;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "https://straightgait.000webhostapp.com/?requestName=legMoved&degreeR=50&degreeL=60&degreeU=70&degreeD=80";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRequest = (Button) findViewById(R.id.buttonRequest);

        btnRequest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){

                    showFootStatus();

                }
            }
        );

/*------------------------------will replace to bluetooth listener-----------------------------------------*/
//        Timer timer = new Timer();
//        //Set the schedule function
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                showFootStatus();
//            }
//        },0, 1000);   // 1000 Millisecond  = 1 second
//
/*--------------------------------------------------------------------------------------------------------*/
}
    private void showFootStatus() {

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);
        String customUrl = "https://straightgait.000webhostapp.com/?requestName=getLegStatus";

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, customUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                imgFoot = (ImageView) findViewById(R.id.img_straightFoot);
                int myNum = 0;
                try {
                    myNum = Integer.parseInt(response.toString());
                } catch(NumberFormatException nfe) {
                    System.out.println("Could not parse " + nfe);
                }
                imgFoot.setRotation(myNum);

                Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i(TAG,"Error :" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);
    }



    private void sendAndRequestResponse() {

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i(TAG,"Error :" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);
    }
}

