package com.example.michael.epa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class LoadingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        String building_id = getIntent().getStringExtra("building_id");                 // building id sent from Selection Screen

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://people.rit.edu/mb2732/EPA/lotdata.txt");
                    Scanner s = new Scanner(url.openStream());
                    // read through scanner
                    s.close();
                }catch(IOException ex){
                    //handle errors in reading from server
                }
            }
        });
        thread.start();

    }
}
