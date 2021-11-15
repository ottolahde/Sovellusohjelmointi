package com.example.helloworld;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                //    .replace(R.id.settings, new getWeatherData())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void getWeatherData(View view){

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.openweathermap.org/data/2.5/weather?q=Tampere&units=metric&appid=5bf8f6840ca09945410e569e5fa3352a";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    //Toast.makeText(this, "Loading weather", Toast.LENGTH_SHORT).show();
                    parseJsonAndUpdateUI(response);
                }, error -> {

        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    private void parseJsonAndUpdateUI(String response){
        try{
            JSONObject rootObject = new JSONObject(response);
            float temperature = (float) rootObject.getJSONObject("main").getDouble("temp");
            float windSpeed = (float) rootObject.getJSONObject("wind").getDouble("speed");
            String description = rootObject.getJSONArray("weather").getJSONObject(0).getString("main");

            //Laiteteaan data näytölle

            TextView weatherDescriptionTextView = findViewById(R.id.weatherDescriptionTextView);
            weatherDescriptionTextView.setText(description);
            TextView temperatureTextView = findViewById(R.id.temperatureTextView);
            temperatureTextView.setText(temperature + "C");
            TextView windspeedTextView = findViewById(R.id.windspeedTextView);
            windspeedTextView.setText(windSpeed + "m/s");

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}