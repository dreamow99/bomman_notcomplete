package Objects.Character;

import Objects.Map;
import Objects.Object;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class Enemy1 extends Object implements Character {

    private int movePath;
    private int dx = 1;
    private int dy = 1;

    public Enemy1(int x, int y) {
        super("character/t/t1/default");
        this.x = x;
        this.y = y;
        collidable = true;
        destroyable = true;
        visible = true;
        width -= 10;
        height -= 3;
        movePath = (int )(Math.random() * 50 + 1) & 2;
    }

    @Override
    public void updateMove(){

        //turning right
        if (dx == 1 && dy == 0){
            ImageIcon ii = new ImageIcon("./assets/img/res/character/t/t1/turningright.gif");
            objectImg = ii.getImage();
        }

        //turning left
        if (dx == -1 && dy == 0){
            ImageIcon ii = new ImageIcon("./assets/img/res/character/t/t1/turningleft.gif");
            objectImg = ii.getImage();
        }

        //going up
        if (dy == -1 && dx == 0){
            ImageIcon ii = new ImageIcon("./assets/img/res/character/t/t1/goingup.gif");
            objectImg = ii.getImage();
        }

        // going down
        if (dy == 1 && dx == 0){
            ImageIcon ii = new ImageIcon("./assets/img/res/character/t/t1/goingdown.gif");
            objectImg = ii.getImage();
        }



    }

    @Override
    public boolean checkCollision(int x, int y, int dx, int dy) {
        Map map = new Map();
        // r l u d

        for (int i = 0; i < map.objectList.size(); i++){
            Object checkObject = map.objectList.get(i);
            if (y < checkObject.getY() + checkObject.getHeight() && x + dx + width > checkObject.getX() && checkObject.getX() > x
                    && y + height > checkObject.getY())
            {
                if (!checkObject.isCollidable())
                {
                    System.out.println(checkObject.getX() + " " + checkObject.getY() + " " + checkObject.toString());
                    return false;
                }
            }
            if (y < checkObject.getY() + checkObject.getHeight() && x + dx < checkObject.getX() + checkObject.getWidth()
                    && checkObject.getX() < x
                    && y + height > checkObject.getY()) {
                if (!checkObject.isCollidable()){
                    System.out.println(checkObject.getX() + " " + checkObject.getY() + " " + checkObject.toString());
                    return false;
                }
            }
            if (y + dy < checkObject.getY() + checkObject.getHeight() && x < checkObject.getX() + checkObject.getWidth()
                    && checkObject.getX() < x + width
                    && checkObject.getY() < y) {
                if (!checkObject.isCollidable()){
                    System.out.println(checkObject.getX() + " " + checkObject.getY() + " " + checkObject.toString());
                    return false;
                }
            }
            if (y + dy + height > checkObject.getY() && x < checkObject.getX() + checkObject.getWidth()
                    && checkObject.getX() < x + width
                    && checkObject.getY() > y) {
                if (!checkObject.isCollidable()){
                    System.out.println(checkObject.getX() + " " + checkObject.getY() + " " + checkObject.toString());
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void move() throws InterruptedException {
        //move horizontal
        if (movePath == 0){

            dy = 0;
            updateMove();
            if (checkCollision(x, y, dx,0)){
                x += dx;
                TimeUnit.MICROSECONDS.sleep(100);
            } else {
                if (dx == 1) dx = -1;
                else if (dx == -1) dx = 1;
            }
        }

        //move vertical
        else{

            dx = 0;
            updateMove();
            if (checkCollision(x, y, 0,dy)){
                y += dy;
                TimeUnit.MICROSECONDS.sleep(100);
            } else {
                if (dy == 1) dy = -1;
                else if (dy == -1) dy = 1;
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
