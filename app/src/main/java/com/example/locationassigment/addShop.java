package com.example.locationassigment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class addShop extends AppCompatActivity {

    private DBHelper db;
    EditText name ,phone ,latitude ,longitude ,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_shop);

        name = (EditText) findViewById(R.id.resortNameInput);
        phone = findViewById(R.id.contactInfoInput);
        latitude = findViewById(R.id.latitudeInput);
        longitude = findViewById(R.id.longitudeInput);
        description = findViewById(R.id.multilineTextInput);

        db = new DBHelper(this);
    }

    public void addResort(View view){

        if(name.getText().toString().equals("") && phone.getText().toString().equals("") &&
                latitude.getText().toString().equals("") && longitude.getText().toString().equals("") &&
                description.getText().toString().equals("") ){
            Toast.makeText(getApplicationContext(), "Cannot add an empty information all fields should be filled",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            if (db.insertValues(name.getText().toString(), phone.getText().toString(),
                    latitude.getText().toString(), longitude.getText().toString(),
                    description.getText().toString())) {
                Toast.makeText(getApplicationContext(), "The Resort Added",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "The Resort not added",
                        Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(getApplicationContext(), ShopsActivity.class);
            //if you want to add .putExtra do it here:

            //start intent
            startActivity(intent);
        }
    }

    public void backToResortActivity(View view){

        Intent intent = new Intent(getApplicationContext(), ShopsActivity.class);
        //start intent
        startActivity(intent);
    }
}