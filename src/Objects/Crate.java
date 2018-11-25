package Objects;

import Screens.Board;

import javax.swing.*;

public class Crate extends Object {

    public int isDestroyed = 0;
    public long countTime;

    public Crate(int x, int y){
        super("crate", false);
        this.x = x;
        this.y = y;
        collidable = false;
        destroyable = true;
        visible = true;
    }
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
