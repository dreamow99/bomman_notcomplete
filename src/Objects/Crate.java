package Objects;

import Screens.Board;

import javax.swing.*;

/**
 * Crate class
 */
public class Crate extends Object {

    public int isDestroyed = 0;
    public long countTime;

    /**
     * constructor
     * @param x x coordinate
     * @param y y coordinate
     */
    public Crate(int x, int y){
        super("crate", false);
        this.x = x;
        this.y = y;
        collidable = false;
        destroyable = true;
        visible = true;
    }

    /**
     * update
     * @param board to get map
     */
    public void update(Board board){


        if (isDestroyed == 0){
            for (Object o : board.getMap().objectList){
                if (o.getX() == x && o.getY() == y && (o instanceof Fire))
                    isDestroyed = 2;
            }
        }

        if (isDestroyed == 2){
            ImageIcon ii = new ImageIcon("./assets/img/res/destroyCrate.png");
            this.objectImg = ii.getImage();
            countTime = System.currentTimeMillis();
            for (Object o : board.getMap().objectList){
                if (o.getX() == x && o.getY() == y && (o instanceof Fire))
                    isDestroyed = 1;
            }
        }
    }

}
