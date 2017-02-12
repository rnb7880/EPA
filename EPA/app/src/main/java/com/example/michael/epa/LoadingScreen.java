package com.example.michael.epa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
                    URL url = new URL("https://people.rit.edu/~msj1475/EPA/lotdata.txt");
                    Scanner s = new Scanner(url.openStream());

                    /* FILE FORMAT
                    # comment, ignore line
                    lot_id space_id x_coord y_coord occupied

                    occupied will be 1 if full, 0 if available
                     */

                    // read through scanner

                    System.out.println(s);


                    s.close();
                }catch(IOException ex){
                    //handle errors in reading from server
                }
            }
        });
        thread.start();


    }
}
