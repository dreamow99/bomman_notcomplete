package Objects.Character;

import Objects.Map;
import Objects.Object;
import Screens.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class Character {
    private int dx;
    private int dy;
    private int x = 36;
    private int y = 36;
    private int width;
    private int height;
    private Image image;

    // r l u d
    private int direction = 0;

    /**
     * constructor
     * @param name image file name
     */
    public Character(String name) {
        loadImage(name);
    }

    /**
     * load default image
     * @param name image file name
     */
    private void loadImage(String name) {

        ImageIcon ii = new ImageIcon("./assets/img/res/" + name + "-2.png");
        image = ii.getImage();

        width = image.getWidth(null);
        height = image.getHeight(null);

        //load moving down images



    }

    /**
     * update motion when moving
     */
    public void updateMove(Board board) {
        if (direction == 0){
            ImageIcon ii = new ImageIcon("./assets/img/res/" + "ct" + "-2.png");
            image = ii.getImage();
            board.repaint();
        }

        //turning right
        if (direction == 1 ){
            ImageIcon ii = new ImageIcon("./assets/img/res/" + "turningright.gif");
            image = ii.getImage();
            board.repaint();
        }

        //turning left
        if (direction == 2 ){
            ImageIcon ii = new ImageIcon("./assets/img/res/" + "turningleft.gif");
            image = ii.getImage();
            board.repaint();
        }

        //going up
        if (direction == 3 ){
            ImageIcon ii = new ImageIcon("./assets/img/res/" + "goingup.gif");
            image = ii.getImage();
            board.repaint();
        }

        // going down
        if (direction == 4 ){
            ImageIcon ii = new ImageIcon("./assets/img/res/" + "goingdown.gif");
            image = ii.getImage();
            board.repaint();
        }

    }

    /**
     * check collision
     * @param x x coordinate
     * @param y y coordinate
     * @param dx x velocity
     * @param dy y velocity
     * @return true/false
     */
    private boolean checkCollision(int x, int y, int dx, int dy){
        String currentMap = "currMap";
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

    /**
     * make a move
     */
    public void move() {
        if (checkCollision(x, y, dx, dy) == true)
        {
            System.out.println(direction);
            x += dx;
            y += dy;
        }
    }

    /**
     * get x
     * @return x
     */
    public int getX() {

        return x;
    }

    /**
     * get y
     * @return y
     */
    public int getY() {

        return y;
    }

    /**
     * get width
     * @return width
     */
    public int getWidth() {

        return width;
    }

    /**
     * get height
     * @return height
     */
    public int getHeight() {

        return height;
    }

    /**
     * get image
     * @return image
     */
    public Image getImage() {

        return image;
    }

    /**
     * handle key pressed
     * @param e
     */
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

    /**
     * handle key released
     * @param e
     */
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
