package Objects.Character;

import Objects.Bomb;
import Objects.Map;
import Objects.Object;
import Screens.Board;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player extends Object implements Character {

    private int dx;
    private int dy;

    public ArrayList<Bomb> bombs;

    // r l u d
    private int direction = 0;

    public Player(int x, int y){
        super("character/ct/default", false);
        this.x = x;
        this.y = y;
        collidable = true;
        destroyable = true;
        visible = true;
        width -= 10;
        height -= 3;
        bombs = new ArrayList<>();
    }


    @Override
    public void updateMove() {
        if (direction == 0){
            ImageIcon ii = new ImageIcon("./assets/img/res/character/ct/default.png");
            this.objectImg = ii.getImage();
        }

        //turning right
        if (direction == 1 ){
            ImageIcon ii = new ImageIcon("./assets/img/res/character/ct/turningright.gif");
            objectImg = ii.getImage();
        }

        //turning left
        if (direction == 2 ){
            ImageIcon ii = new ImageIcon("./assets/img/res/character/ct/turningleft.gif");
            objectImg = ii.getImage();
        }

        //going up
        if (direction == 3 ){
            ImageIcon ii = new ImageIcon("./assets/img/res/character/ct/goingup.gif");
            objectImg = ii.getImage();
        }

        // going down
        if (direction == 4 ){
            ImageIcon ii = new ImageIcon("./assets/img/res/character/ct/goingdown.gif");
            objectImg = ii.getImage();
        }
    }


    @Override
    public boolean checkCollision(int x, int y, int dx, int dy, Map map) {

        // r l u d

        for (int i = 0; i < map.objectList.size(); i++){
            Object checkObject = map.objectList.get(i);
            if (y < checkObject.getY() + checkObject.getHeight() && x + dx + width > checkObject.getX() && checkObject.getX() > x
                    && y + height > checkObject.getY())
            {
                if (!checkObject.isCollidable())
                {
                    //System.out.println(checkObject.getX() + " " + checkObject.getY() + " " + checkObject.toString());
                    return false;
                }
            }
            if (y < checkObject.getY() + checkObject.getHeight() && x + dx < checkObject.getX() + checkObject.getWidth()
                    && checkObject.getX() < x
                    && y + height > checkObject.getY()) {
                if (!checkObject.isCollidable()){
                    //System.out.println(checkObject.getX() + " " + checkObject.getY() + " " + checkObject.toString());
                    return false;
                }
            }
            if (y + dy < checkObject.getY() + checkObject.getHeight() && x < checkObject.getX() + checkObject.getWidth()
                    && checkObject.getX() < x + width
                    && checkObject.getY() < y) {
                if (!checkObject.isCollidable()){
                    //System.out.println(checkObject.getX() + " " + checkObject.getY() + " " + checkObject.toString());
                    return false;
                }
            }
            if (y + dy + height > checkObject.getY() && x < checkObject.getX() + checkObject.getWidth()
                    && checkObject.getX() < x + width
                    && checkObject.getY() > y) {
                if (!checkObject.isCollidable()){
                    //System.out.println(checkObject.getX() + " " + checkObject.getY() + " " + checkObject.toString());
                    return false;
                }
            }
        }
        return true;
    }

    private void bombHasBeenPlanted(int x, int y, int range){
        //System.out.println("plan a bomb");

        bombs.add(new Bomb(x, y, range));

    }

    @Override
    public void move(Board board) {
        if (checkCollision(x, y, dx, dy, board.getMap()))
        {
            //System.out.println(direction);
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

        if (key == KeyEvent.VK_SPACE){
            bombHasBeenPlanted(x, y, 2);
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
