package Objects.Character;

import Objects.Map;
import Objects.Object;
import Screens.Board;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Player extends Object implements Character {

    private int dx;
    private int dy;

    // r l u d
    private int direction = 0;

    public Player(int x, int y){
        super("ct-2");
        this.x = x;
        this.y = y;
        collidable = true;
        destroyable = true;
        visible = true;
    }

    @Override
    public void updateMove(Board board) {
        if (direction == 0){
            ImageIcon ii = new ImageIcon("./assets/img/res/" + "ct" + "-2.png");
            this.objectImg = ii.getImage();
            board.repaint();
        }

        //turning right
        if (direction == 1 ){
            ImageIcon ii = new ImageIcon("./assets/img/res/" + "turningright.gif");
            objectImg = ii.getImage();
            board.repaint();
        }

        //turning left
        if (direction == 2 ){
            ImageIcon ii = new ImageIcon("./assets/img/res/" + "turningleft.gif");
            objectImg = ii.getImage();
            board.repaint();
        }

        //going up
        if (direction == 3 ){
            ImageIcon ii = new ImageIcon("./assets/img/res/" + "goingup.gif");
            objectImg = ii.getImage();
            board.repaint();
        }

        // going down
        if (direction == 4 ){
            ImageIcon ii = new ImageIcon("./assets/img/res/" + "goingdown.gif");
            objectImg = ii.getImage();
            board.repaint();
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
    public void move() {
        if (checkCollision(x, y, dx, dy))
        {
            System.out.println(direction);
            x += dx;
            y += dy;
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
            direction = 1;
        }

        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
            direction = 2;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -1;
            direction = 3;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 1;
            direction = 4;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
            direction = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
            direction = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
            direction = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
            direction = 0;
        }
    }
}
