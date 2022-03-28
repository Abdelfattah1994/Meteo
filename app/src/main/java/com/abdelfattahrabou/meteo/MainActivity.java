package com.abdelfattahrabou.meteo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button btn_cityId ;
    Button btn_weatherById ;
    Button btn_weatherByName;
    EditText et_cityName;
    ListView lv_meteoInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_cityId=findViewById(R.id.btn_getCityById);
        btn_weatherById=findViewById(R.id.btn_getWeatherById);
        btn_weatherByName=findViewById(R.id.btn_getWeatherByCity);
        et_cityName=findViewById(R.id.et_cityName);
        lv_meteoInfo=findViewById(R.id.lv_meteoInfo);

        btn_cityId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "https://www.metaweather.com/api/location/search/?query="+et_cityName.getText().toString();
                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String cityId ="" ;
                        try {
                            JSONObject weatherInfo= response.getJSONObject(0);
                            cityId=weatherInfo.getString("woeid");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(MainActivity.this, cityId, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                MySingleton.getInstance(MainActivity.this).addToRequestQueue(request);
            }
        });

        btn_weatherById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"You clicked Button 2",Toast.LENGTH_SHORT).show();
            }
        });
        btn_weatherByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "You clicked Button 3", Toast.LENGTH_SHORT).show();
            }
        });
    }
}