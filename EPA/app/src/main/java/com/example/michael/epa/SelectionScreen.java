package com.example.michael.epa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

public class SelectionScreen extends AppCompatActivity {


    private String buildingID = "";
    private void setBuildingID(String id){
        buildingID = id;
    }
    private boolean buildingSelected(){
        return !buildingID.isEmpty();
    }
    private String getBuildingID(){
        return buildingID;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_screen);

        final String[] buildings = new String[]{"Select a building", "A", "B", "C"};

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SelectionScreen.this,
                android.R.layout.simple_spinner_item, buildings);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        setBuildingID("");
                        break;
                    case 1:
                        setBuildingID("A");
                        break;
                    case 2:
                        setBuildingID("B");
                        break;
                    case 3:
                        setBuildingID("C");
                        break;

                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        /* Find Spot Button
            proceeds to loading screen when pressed
                if building id is not empty
         */
        final Button find_spot = (Button) findViewById(R.id.find_spot);
        find_spot.setText("Find a Spot");
        find_spot.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {                                                   // moves on to loading screen when a building is selected
                if(buildingSelected()) {
                    Intent intent = new Intent(SelectionScreen.this, LoadingScreen.class);
                    intent.putExtra("building_id", getBuildingID());
                    startActivity(intent);
                }
            }
        });

        final Button main_menu = (Button) findViewById(R.id.main_menu);
        main_menu.setText("Main Menu");
        main_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        final ImageButton logo = (ImageButton) findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
