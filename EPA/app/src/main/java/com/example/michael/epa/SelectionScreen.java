package com.example.michael.epa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SelectionScreen extends AppCompatActivity {


    private String buildingID = "";
    private void setBuildingID(String id){
        buildingID = id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_screen);

        String[] buildings = new String[]{"A", "B", "C"};                                               //TODO add buildings algorithmically?

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SelectionScreen.this,
                android.R.layout.simple_spinner_item, buildings);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final String building_id = "";

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {                                                                    // TODO must be changed with above todo tag
                    case 0:

                        break;
                    case 1:
                        setBuildingID("A");
                        break;
                    case 2:
                        setBuildingID("B");
                        break;

                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                String building_id = "";
            }
        });

        /* Find Spot Button
            proceeds to loading screen when pressed
                if building id is not empty
         */
        final Button find_spot = (Button) findViewById(R.id.find_spot);
        find_spot.setText("Find a Spot");
        find_spot.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!building_id.isEmpty()) {
                    Intent intent = new Intent(SelectionScreen.this, LoadingScreen.class);
                    startActivity(intent);
                }
            }
        });


    }
}
