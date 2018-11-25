package Objects;


import GameManager.MapGenerate;
import Objects.Character.Enemy1;
import Objects.Character.Player;
import Screens.Board;

import java.awt.*;
import java.util.ArrayList;

public class Map {
    public ArrayList<Object> objectList;
    private MapGenerate mapGenerator;

    public Map(Board board) {
        mapGenerator = new MapGenerate(this, board);
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
     * @param g2d graphic
     * @param board board to draw to
     */
    public void draw(Graphics2D g2d, Board board) {
        update();
        for (int i = 0; i < objectList.size(); i++) {
            Object o = objectList.get(i);
            if (o instanceof Player) continue;
            if (o instanceof Enemy1) continue;
            g2d.drawImage(o.getObjectImg(), o.getX(), o.getY(), board);
        }
        for (int i = 0; i < objectList.size(); i++) {
            Object o = objectList.get(i);
            if (o instanceof Player) g2d.drawImage(o.getObjectImg(), o.getX(), o.getY(), board);
            if (o instanceof Enemy1) g2d.drawImage(o.getObjectImg(), o.getX(), o.getY(), board);
        }
    }

}
