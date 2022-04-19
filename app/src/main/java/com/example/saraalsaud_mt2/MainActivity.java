package com.example.saraalsaud_mt2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TextView reservation;
    TextView temperature, humidity1;
    String group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JSONObject jsonObj;

        reservation = (TextView) findViewById(R.id.txtReaervation);
        Button datebtn = (Button) findViewById(R.id.bttnDate);
        Button bttncity = (Button) findViewById(R.id.btnchoosecity);
        Button btnAct2 = (Button) findViewById(R.id.btnAct2);
        temperature = (TextView) findViewById(R.id.temperature);
        humidity1 = (TextView) findViewById(R.id.humidity1);
        Spinner spinner = (Spinner) findViewById(R.id.spnr);
        datebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, d,
                        c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        bttncity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                group = spinner.getSelectedItem().toString();

                if (group.equals("Riyadh")){
                    String city = spinner.getSelectedItem().toString();
                    String url = "https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=4a4b884c3ff272a5ac40b4ac63e45c35&units=metric";
                    weather(url);

                }

                if (group.equals("Sydney")){
                    String city = spinner.getSelectedItem().toString();
                    String url = "https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=4a4b884c3ff272a5ac40b4ac63e45c35&units=metric";
                    weather(url);
                }

                if (group.equals("Vienna")){
                    String city = spinner.getSelectedItem().toString();
                    String url = "https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=4a4b884c3ff272a5ac40b4ac63e45c35&units=metric";
                    weather(url);
                }
            }
        });

    btnAct2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(MainActivity.this,Activity2.class));
        }
    });

    }
    Calendar c = Calendar.getInstance();
    DateFormat fmtDate = DateFormat.getDateInstance();
    DatePickerDialog.OnDateSetListener d =new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(android.widget.DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, monthOfYear);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            reservation.setText("Your date is "+ fmtDate.format(c.getTime()));
        }
    };

    public void weather(String url){
        JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Sara", "Response recieved");
                        Log.d("Sara",response.toString());
                        try {

                            JSONObject jsonMain = response.getJSONObject("main");
                            JSONObject jsonMain2 = response.getJSONObject("sys");

                            double temp = jsonMain.getDouble("temp");
                            temperature.setText(String.valueOf(temp)+"°C");

                            double humidity = jsonMain.getDouble("humidity");
                            humidity1.setText("humidity: "+String.valueOf(humidity));

                        }catch(JSONException e){
                            e.printStackTrace();
                            Log.e("JSON error",e.toString());
                        }
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Sara", "Error, recieving URL");
            }
        }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObj);
    }



}