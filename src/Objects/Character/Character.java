package Objects.Character;

import Objects.Map;
import Objects.Object;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Character {
    private int dx;
    private int dy;
    private int x = 32;
    private int y = 32;
    private int w;
    private int h;
    private Image image;
    private boolean canMoveLeft = true;
    private boolean canMoveRight = true;
    private boolean canMoveUp = true;
    private boolean canMoveDown = true;

    public Character(String name) {
        loadImage(name);
    }

    private void loadImage(String name) {

        ImageIcon ii = new ImageIcon("./assets/img/res/" + name + ".png");
        image = ii.getImage();

        w = image.getWidth(null);
        h = image.getHeight(null);
    }

    private boolean checkCollision(int x, int y, int dx, int dy){
        Map map = new Map();

        for (int i = 0; i < map.objectList.size(); i++){
            Object checkObject = map.objectList.get(i);
            if (y < checkObject.getY() + 32 && x + dx + 24 > checkObject.getX() && checkObject.getX() > x
                    && y + 29 > checkObject.getY())
            {
                if (!checkObject.isCollidable())
                {
                    canMoveRight = false;
                    canMoveLeft = true;
                    canMoveUp = true;
                    canMoveDown = true;
                    System.out.println(checkObject.getX() + " " + checkObject.getY() + " " + checkObject.toString());
                    return false;
                }
            }
            if (y < checkObject.getY() + 32 && x + dx < checkObject.getX() + 32 && checkObject.getX() < x
                    && y + 29 > checkObject.getY()) {
                if (!checkObject.isCollidable()){
                    canMoveLeft = false;
                    canMoveRight = true;
                    canMoveUp = true;
                    canMoveDown = true;
                    System.out.println(checkObject.getX() + " " + checkObject.getY() + " " + checkObject.toString());
                    return false;
                }
            }
            if (y + dy < checkObject.getY() + 32 && x < checkObject.getX() + 32 && checkObject.getX() < x + 24
                    && checkObject.getY() < y) {
                if (!checkObject.isCollidable()){
                    canMoveLeft = true;
                    canMoveRight = true;
                    canMoveUp = false;
                    canMoveDown = true;
                    System.out.println(checkObject.getX() + " " + checkObject.getY() + " " + checkObject.toString());
                    return false;
                }
            }
            if (y + dy + 29 > checkObject.getY() && x < checkObject.getX() + 32 && checkObject.getX() < x + 24
                    && checkObject.getY() > y) {
                if (!checkObject.isCollidable()){
                    canMoveLeft = false;
                    canMoveRight = true;
                    canMoveUp = true;
                    canMoveDown = false;
                    System.out.println(checkObject.getX() + " " + checkObject.getY() + " " + checkObject.toString());
                    return false;
                }
            }
        }
        return true;
    }

    public void move() {
        if (checkCollision(x, y, dx, dy) == true)
        {
            x += dx;
            y += dy;
        }
        else if (!checkCollision(x, y, dx, dy) == true)
        {
            if (!canMoveLeft)
            {
                //x += dx;

                if (checkCollision(x, y, dx, dy) == true)
                {
                    y += dy;
                }
            }
            if (!canMoveRight)
            {
                //x += dx;
                if (checkCollision(x, y, dx, dy) == true)
                {
                    y += dy;
                }
            }
            if (!canMoveUp)
            {
                if (checkCollision(x, y, dx, dy) == true)
                {
                    x += dx;
                }
            }
            if (!canMoveDown)
            {

                if (checkCollision(x, y, dx, dy) == true)
                {
                    x += dx;
                }
            }
        }



    }

    public int getX() {

        return x;
    }

    public int getY() {

        return y;
    }

    public int getWidth() {

        return w;
    }

    public int getHeight() {

        return h;
    }

    public Image getImage() {

        return image;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -1;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 1;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}
