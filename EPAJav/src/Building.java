import java.util.ArrayList;

/**
 * Created by rachn_000 on 2/12/2017.
 */
public class Building {
    private ArrayList<Lot> lots;
    private String name;

    public Building(String name){
        this.lots = new ArrayList<Lot>();
        this.name = name;
    }

    public void addLot(Lot lot){lots.add(lot);}

    public ArrayList<Lot> getLots(){
        return this.lots;
    }

    public Lot findLot(String ID){
        Lot toreturn = new Lot("unreal",0);
        for(Lot lot:lots){
            if(lot.getName().equals(ID)){
                toreturn = lot;
            }
        }return toreturn;
    }

    public Lot bestLot(){
        return lots.get(0);
    }

    public String getName(){return this.name;}


}
