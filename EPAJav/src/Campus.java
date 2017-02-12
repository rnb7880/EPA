import java.util.ArrayList;

/**
 * Created by rachn_000 on 2/11/2017.
 */
public class Campus {
    private ArrayList<Lot> lots;
    private ArrayList<String> lotNames;
    private String name;
    private ArrayList<Building> bldgs;

    public Campus(String title){
        name = title;
        lotNames = new ArrayList<String>();
        lots = new ArrayList<Lot>();
        bldgs = new ArrayList<Building>();
    }

    public void addSpace(String lotname, int spot, int x, int y, boolean status){
        Lot lot = findLot(lotname);
        lot.addSpace(spot,x,y,status);
    }



    public Lot findLot(String lotname){
        for (Lot lot:lots){
            if(lot.getName().equals(lotname)){
                return lot;
            }
            else{
                continue;
            }
        } return new Lot("unreal",0);
    }

    public ArrayList<Lot> getLots(){return this.lots;}

    public void addLot(Lot lot){
        lots.add(lot);
        lotNames.add(lot.getName());
    }

    public boolean hasLot(String lotname){
        if(lotNames.contains(lotname)){return true;}
        else{return false;}
    }



    public Building findBldg(String name){
        Building pick = bldgs.get(0);
        for(Building bld:bldgs){
            if(bld.getName().equals(name)){
                pick= bld;
            }
        }return pick;
    }

    public void addBuilding(Building bld){this.bldgs.add(bld);}

    public String toString(){
        String campusstring = "";
        for (Lot lot:lots){
            campusstring += lot.toString() + " ";
        }
        return campusstring;
    }


}

