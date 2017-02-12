import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.net.*;
import java.io.*;


/**
 * Created by rachn_000 on 2/11/2017.
 */


public class LoadingScreen {


    public static void main(String[] args) throws Exception {
        int x;
        int y;
        boolean boo;
        int spot;
        String ID;
        int thresh;

        Campus RIT = new Campus("RIT");

        URL oracle = new URL("https://people.rit.edu/~msj1475/EPA/lotdata.txt");
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

        URL bldg = new URL("https://people.rit.edu/~msj1475/EPA/building_info.txt");
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


//        System.out.println(RIT.toString());


//        for (Lot lot:RIT.getLots()){
//            lot.orgSpaces();
//        }

//        for (Lot lot:RIT.getLots()){
//            System.out.println(lot.arrString());
//        }

        Building destination = RIT.findBldg(args[0]);
        Lot toPark = destination.bestLot();
        toPark.orgSpaces();
        String location = toPark.getBldgs().get(destination);
        toPark.priorityLot(location);

        System.out.println(toPark.getBest());






    }



}

