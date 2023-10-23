package com.example.locationassigment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShopInfoActivity extends AppCompatActivity {

    TextView resortName, phoneNumber, description;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_information);

        resortName = findViewById(R.id.resortName);
        phoneNumber = findViewById(R.id.phoneNumber);
        description = findViewById(R.id.description);

        db = new DBHelper(this);

        // To get the message send from an activity
        Bundle extras = getIntent().getExtras();

        if(extras != null){

                String name2 = extras.getString( "name");
                String phoneNumber2 = extras.getString("phoneNumber");

                String description2 = extras.getString("descritpion");

            resortName.setText( name2);
            phoneNumber.setText( phoneNumber2);
            description.setText( description2);
        }
    }


    public void backToResortActivity(View view){

        Intent intent = new Intent(getApplicationContext(), ShopsActivity.class);
        //start intent
        startActivity(intent);
    }
}