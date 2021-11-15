package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void calculateBMI(View view) {
        //Lasketaan painoindeksi
        EditText heightEditText = findViewById(R.id.heightEditText);
        EditText weightEditText = findViewById(R.id.weightEditText);
        String heightString = heightEditText.getText().toString();
        String weightString = weightEditText.getText().toString();
        double weight = Double.parseDouble(weightString);
        double height = Double.parseDouble(heightString);
        double bmi = weight / (height/100 * height/100);


        //Laitetaan tulos näytölle
        TextView resultTextView = findViewById(R.id.resultTextView);
        resultTextView.setText("BMI: "+ bmi);
    }

    public void  dialPhone (View view){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:+358451133858"));
        if (intent.resolveActivity(getPackageManager())!= null){
            startActivity(intent);
        }
    }

    public void openSettings(View view){
        //Avataan toinen aktiviteetti
        Intent openSettingsIntent = new Intent(this, SettingsActivity.class );
        startActivity(openSettingsIntent);
    }
    public void openButtons(View view){
        //Avataan toinen aktiviteetti
        Intent openFingridIntent = new Intent(this, FingridActivity.class );
        startActivity(openFingridIntent);
    }
}