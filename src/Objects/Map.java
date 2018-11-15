package Objects;


import GameManager.MapGenerate;

import java.util.ArrayList;

public class Map {
    public ArrayList<Object> objectList;
    private MapGenerate mapGenerator;

    public Map(){
        mapGenerator = new MapGenerate(this);
    }

    public Map(String mapFile) {

    }

    public void add(Object o){
        objectList.add(o);
    }

    public int getMapRow() { return mapGenerator.getRow(); }
    public int getMapCollumn() { return mapGenerator.getCollumn(); }


}
