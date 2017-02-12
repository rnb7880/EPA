package com.example.michael.epa;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by rachn_000 on 2/11/2017.
 */
public class Space{
    private int x;
    private int y;
    private boolean open;
    private int spot;
    private int[] coord;

    public Space(int spot, int x, int y, boolean status) {
        this.x =x;
        this.y=y;
        this.open = status;
        this.spot = spot;
        this.coord = new int[2];
    }

    public int get_x(){
        return this.x;
    }

    public int get_y(){
        return this.y;
    }

    public boolean get_status(){
        return this.open;
    }

    public int get_spot(){return this.spot;}

    public int[] get_coord(){return this.coord;}

    public void set_coord(int x, int y){coord[0]=x;coord[1]=y;}

    public void put_y(int y){
        this.y=y;
    }

    public void put_x(int x){
        this.x=x;
    }

    public void def_spot(int spot){this.spot = spot;}

    public void def_status(boolean status){
        this.open = status;
    }



    @Override
    public String toString() {
        return "(" + String.valueOf(this.x) + ", " + String.valueOf(this.y) + ") at spot " + String.valueOf(this.spot)
                + " is open: " + String.valueOf(this.open);
    }

}
