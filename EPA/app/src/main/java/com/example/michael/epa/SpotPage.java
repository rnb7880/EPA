package com.example.michael.epa;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SpotPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_page);

        String lot_id = getIntent().getStringExtra("lot_id");
        String spot_id = getIntent().getStringExtra("spot_id");

        TextView lot = (TextView)findViewById(R.id.lot);
        lot.setText("LOT\n" + lot_id);

        TextView spot = (TextView)findViewById(R.id.spot);
        spot.setText("SPOT\n" + spot_id);
    }


    @Override
    public void onBackPressed() {
        finish();
    }
}
