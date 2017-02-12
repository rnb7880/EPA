import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rachn_000 on 2/11/2017.
 */
public class Lot {
    private String ID;
    private int thresh;
    private int lotHeight;
    private int lotWidth;

    private ArrayList<Space> spaces;
    private String[][] arrSpaces;
    private ArrayList<int[]> coordSpaces;

    private HashMap<Building,String> bldgs;


    public Lot(String ID,int thresh){
        this.ID = ID;
        this.thresh = thresh;
        spaces = new ArrayList<Space>();
        coordSpaces = new ArrayList<int[]>();
        bldgs = new HashMap<Building,String>();
    }

    public void getDims(){
        int[] dims = new int[2];
        int width = -1;
        int height;
        for(int i = 0; i < spaces.size()-1;i++){
            if(spaces.get(i).get_y() - spaces.get(i+1).get_y() < thresh){
                width ++;
            }
            else{break;}
        }
        height = spaces.size()%width;
        width = width -1;
        lotWidth = width;
        lotHeight = height;

    }

    public void addSpace(int spot,int x,int y,boolean status){
        spaces.add(new Space(spot,x,y,status));
    }

    public void addBuilding(Building bld,String location){
        this.bldgs.put(bld,location);
    }
    public HashMap<Building,String> getBldgs(){return this.bldgs;}


    public String getBest(){
        int[] pick = coordSpaces.get(0);
        Space theSpot = spaces.get(0);
        String spotInfo = "";
        for (Space space:spaces){
            if(space.get_coord() == pick){
                theSpot = space;
            }
        }
        spotInfo += (this.getName() + " "+ theSpot.get_spot());

        return spotInfo;
    }


    public void orgSpaces(){
        int y = 0;
        int x = 0;
        int [] dims= new int[2];

        this.getDims();
        int width = this.lotWidth;
        int height = this.lotHeight;
        arrSpaces = new String[height][width];
        y = 0;
        x = 0;
        String val = "";

        for(int i = 0; i < (width*height) ;i++){
            if(x<width){
                if (i < spaces.size()){val = String.valueOf(spaces.get(i).get_spot());spaces.get(i).set_coord(y,i%width);}
                else{val = "O";};
                arrSpaces[y][i%width] = val ;
                x++;
            }else{y++;x=0;i-=1;}
        }
    }
    public String priorityString(){
        String concat = "";
        for(int[] coord:coordSpaces){
            concat += "[" + coord[0]+","+coord[1]+"] ,";
        }
        return concat;
    }
    public void priorityLot(String type){

        coordSpaces = new ArrayList<int[]>();
        for (Space space:spaces){
            if (space.get_status()){
                coordSpaces.add(space.get_coord());
            }
        }
        coordSpaces = InsertionSort(coordSpaces,type);
    }
    public boolean Compare(int[] coord1,int[]coord2,String type){
        int xb = -1;
        int yb;
        switch (type) {
            case "L":
                yb = -1;
                break;
            case "C":
                yb = Math.floorDiv(lotWidth , 2);
                break;
            case "R":
                yb = lotWidth;
                break;
            default:
                yb = -1;
                break;
        }
        int x1 = coord1[0];
        int x2 = coord2[0];
        int y1 = coord1[1];
        int y2 = coord2[1];

        double dist1 = Math.sqrt(Math.pow((xb-x1),2)+Math.pow((yb-y1),2));
        double dist2 = Math.sqrt(Math.pow((xb-x2),2)+Math.pow((yb-y2),2));

        return (dist1 > dist2);
    }


    public ArrayList<int[]> InsertionSort(ArrayList<int[]> lst,String type){

        for (int i =0; i < lst.size()-1;i++){
            while (i > -1 && Compare(lst.get(i),lst.get(i+1),type)){
                if( i == lst.size()){
                    break;
                }else{
                    Swap(lst,i,i+1);
                }
                i -=1;}
        }
        return lst;
    }
    public ArrayList<int[]> Swap(ArrayList<int[]> lst,int x, int y){
        int [] tempVar = lst.get(x);
        lst.set(x,lst.get(y));
        lst.set(y,tempVar);
        return lst;
    }


    public String getName(){
        return this.ID;
    }
    public String toString(){
        String lotspaces = this.getName() + " with thresh "+ thresh +": \n    ";
        for(Space space: spaces){
            lotspaces += space.toString() + "\n    ";
        }
        lotspaces += "\n";

        for(Building bld: bldgs.keySet()){
            String a = bldgs.get(bld);
            lotspaces += "Building "+ bld.getName() +" is located at " + a+"\n";
        }
        return lotspaces;
    }
    public String arrString(){
        String arrstring = "";
        for(int a=0;a < lotHeight;a++){
            for(int b=0;b < lotWidth;b++){
                arrstring += arrSpaces[a][b] + " ";
            }
            arrstring += "\n";
        }
        return arrstring;
    }
}
