package com.example.michael.epa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);



        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String building_id = getIntent().getStringExtra("building_id");// building id sent from Selection Screen
                    int x;
                    int y;
                    boolean boo;
                    int spot;
                    String ID;
                    int thresh;

                    Campus RIT = new Campus("RIT");

                    URL oracle = new URL("https://people.rit.edu/~mb2732/EPA/lotdata.txt");
                    BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));


                    String input;

                    while ((input = in.readLine()) != null) {

                        String[] inputLine = input.split(" ");

                        if (inputLine[0].equals("#")){
                        } else {
                            ID = inputLine[0];
                            thresh = Integer.parseInt(inputLine[1]);
                            spot = Integer.parseInt(inputLine[2]);
                            x = Integer.parseInt(inputLine[3]);
                            y = Integer.parseInt(inputLine[4]);
                            if (inputLine[5].equals("0")) {
                                boo = true;
                            } else {
                                boo = false;
                            }

                            if(RIT.hasLot(String.valueOf(ID))){
                                RIT.addSpace(ID,spot,x,y,boo);
                            }else{
                                RIT.addLot(new Lot(ID,thresh));
                                RIT.addSpace(ID,spot,x,y,boo);
                            };

                        }

                    }
                    in.close();

                    URL bldg = new URL("https://people.rit.edu/~mb2732/EPA/building_info.txt");
                    BufferedReader bld = new BufferedReader(new InputStreamReader(bldg.openStream()));

                    String input2;
                    while ((input2 = bld.readLine()) != null) {
                        String[] bldLine = input2.split(" ");
            //            System.out.println(input2);
                        Building b = new Building(bldLine[0]);
                        //add building to campus
                        RIT.addBuilding(b);
                        //add lots to building
                        b.addLot(RIT.findLot(bldLine[1]));
                        //ass building to lot
                        RIT.findLot(bldLine[1]).addBuilding(b,bldLine[2]);
                    }

                    bld.close();
                    Building destination = RIT.findBldg(building_id);
                    Lot toPark = destination.bestLot();
                    toPark.orgSpaces();
                    String location = toPark.getBldgs().get(destination);
                    toPark.priorityLot(location);

                    String result = (toPark.getBest());
                    String[] results = result.split(" ");

                    Intent intent = new Intent(LoadingScreen.this, SpotPage.class);
                    intent.putExtra("lot_id", results[0]);
                    intent.putExtra("spot_id", results[1]);
                    startActivity(intent);

                }catch(IOException ex){
                    //handle errors in reading from server
                }
            }
        });
        thread.start();
        



    }
}
