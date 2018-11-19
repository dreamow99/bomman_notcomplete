package Objects;


import GameManager.MapGenerate;
import Objects.Character.Player;
import Screens.Board;

import java.awt.*;
import java.util.ArrayList;

public class Map {
    public ArrayList<Object> objectList;
    private MapGenerate mapGenerator;

    public Map() {
        mapGenerator = new MapGenerate(this);
    }


    public void add(Object o) {
        objectList.add(o);
    }

    public int getMapRow() {
        return mapGenerator.getRow();
    }

    public int getMapCollumn() {
        return mapGenerator.getCollumn();
    }

    /**
     * update map as objects change
     */
    private void update() {
    }

    /**
     * draw the map
     * @param g2d
     * @param board
     */
    public void draw(Graphics2D g2d, Board board) {
        update();
        for (int i = 0; i < objectList.size(); i++) {
            Object o = objectList.get(i);
            g2d.drawImage(o.getObjectImg(), o.getX(), o.getY(), board);
        }
    }

    public Player getPlayer(){
        Player p = null;
        for (int i = 0; i < objectList.size(); i++) {
            Object o = objectList.get(i);
            if (o instanceof Player) p = (Player)o;
        }
        return p;
    }
}
