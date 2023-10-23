package com.example.locationassigment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;


public class ScrollingFragment extends Fragment {

    //similar to onClickListener
    public interface FragmentScrollListener{
        void inputSend(int id);

        void removeMarker();
    }

    private FragmentScrollListener listener;

    private ListView obj;
    DBHelper db;
    MapsFragment mMap;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_scrolling, container, false);


        db = new DBHelper( getContext() );

        ArrayList records = db.getAllRecordsName();

        ArrayAdapter adapt = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.custom_row_layout, R.id.nameTextView, records)  {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = view.findViewById(R.id.nameTextView);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //send to main activity.
                        listener.inputSend(position);
                    }
                });

                Button deleteButton = view.findViewById(R.id.deleteButton);
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int i = v.getId();
                        System.out.println(i + " is the id");
                        int result = db.deleteRecord(tv.getText().toString() );
                        if(result > 0){
                            Toast.makeText(getContext(), "The itemd deleted", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(), "Failed to delete the item", Toast.LENGTH_SHORT).show();
                        }
                        records.remove( position );
                        notifyDataSetChanged();

                       listener.removeMarker();
                    }
                });
                return view;
            }
        };

        obj = view.findViewById(R.id.items);
        obj.setAdapter(adapt);

        return view;
    }



    //to attach to activity
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        //to check if the activity(context) implements this interface:
        if(context instanceof FragmentScrollListener){
            listener = (FragmentScrollListener) context;
        }else{
            throw new RuntimeException(context.toString()
            + " must implement FragmentScrollListener");
        }
    }

    //we don't need our activity inplace
    @Override
    public void onDetach(){
        super.onDetach();
        listener = null;
    }
}