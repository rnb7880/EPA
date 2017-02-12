package com.example.michael.epa;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class SpotPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_page);

        String lotid = getIntent().getStringExtra("lot_id");
        String spotid = getIntent().getStringExtra("spot_id");




        if(spotid.equals(new String("NA"))){
            TextView sorry = (TextView)findViewById(R.id.sorry);
            sorry.setText("Sorry!\nThe lot is completely full :(");
        }else{
            TextView lot = (TextView)findViewById(R.id.lot);
            lot.setText("LOT");
            TextView spot = (TextView)findViewById(R.id.spot);
            spot.setText("SPOT");
            TextView lot_id = (TextView) findViewById(R.id.lot_id);
            lot_id.setText(lotid);
            TextView spot_id = (TextView) findViewById(R.id.spot_id);
            spot_id.setText(spotid);
        }

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
