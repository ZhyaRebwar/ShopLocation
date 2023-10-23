package com.example.locationassigment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ShopsActivity extends AppCompatActivity implements ScrollingFragment.FragmentScrollListener {


    MapsFragment maps;
    ScrollingFragment scrollData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_activity);

        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        scrollData = new ScrollingFragment();
        fragmentTransaction.add(R.id.fragment_container2, scrollData);

        maps = new MapsFragment();
        fragmentTransaction.add(R.id.fragment_container, maps);
        fragmentTransaction.commit();
    }

    @Override
    public void inputSend(int id) {
        maps.updateText(id);
    }
    public  void removeMarker(){maps.refreshMarker();}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) {
            case R.id.add:
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", 0);
                Intent intent = new Intent(getApplicationContext(), addShop.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
