package Objects.Character;

import Objects.Fire;
import Objects.Map;
import Objects.Object;
import Screens.Board;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Oneal extends Object implements Character {

    private int dx = 0;
    private int dy = 0;
    private Board board;

    public int isAlive; // 0 - no; 1 - yes; 2 - dying

    // r l u d 1 2 3 4
    private int direction = 0;

    public Oneal(int x, int y, Board board) {
        super("character/t/t2/default", false);
        this.x = x;
        this.y = y;
        this.board = board;
        collidable = true;
        destroyable = true;
        visible = true;
        isAlive = 1;
    }

    @Override
    public void updateMove(){
        //turning right
        if (dx == 1 && dy == 0){
            ImageIcon ii = new ImageIcon("./assets/img/res/character/t/t2/turningright.gif");
            objectImg = ii.getImage();
        }

        //turning left
        if (dx == -1 && dy == 0){
            ImageIcon ii = new ImageIcon("./assets/img/res/character/t/t2/turningleft.gif");
            objectImg = ii.getImage();
        }

        //going up
        if (dy == -1 && dx == 0){
            ImageIcon ii = new ImageIcon("./assets/img/res/character/t/t2/goingup.gif");
            objectImg = ii.getImage();
        }

        // going down
        if (dy == 1 && dx == 0){
            ImageIcon ii = new ImageIcon("./assets/img/res/character/t/t2/goingdown.gif");
            objectImg = ii.getImage();
        }

    }


    @Override
    public boolean preCheckCollision(int x, int y, int dx, int dy, Map map) {
        return true;
    }

    @Override
    public void checkCollision(int x, int y, int dx, int dy, Map map) {
        // r l u d


    }

    @Override
    public void move(Board board) {
        //nếu chết thì ko đi nữa
        if (isAlive == 2){
            dx = 0;
            dy = 0;
            return;
        }




    }

    private boolean catchFire(int x, int y, Map map){
        for (int i = 0; i < map.objectList.size(); i++){
            Object checkObject = map.objectList.get(i);
            if (checkObject instanceof Fire){
                if (x == checkObject.getX() && y == checkObject.getY())
                    return true;
                if (y < checkObject.getY() + checkObject.getHeight() && x + width > checkObject.getX() && checkObject.getX() > x
                        && y + height > checkObject.getY())
                    return true;
                if (y < checkObject.getY() + checkObject.getHeight() && x  < checkObject.getX() + checkObject.getWidth()
                        && checkObject.getX() < x
                        && y + height > checkObject.getY())
                    return true;
                if (y  < checkObject.getY() + checkObject.getHeight() && x < checkObject.getX() + checkObject.getWidth()
                        && checkObject.getX() < x + width
                        && checkObject.getY() < y)
                    return true;
                if (y + height > checkObject.getY() && x < checkObject.getX() + checkObject.getWidth()
                        && checkObject.getX() < x + width
                        && checkObject.getY() > y)
                    return true;
            }
        }
        return false;
    }

    @Override
    public void update() {
        if (isAlive == 1){
            if (catchFire(x, y, board.getMap())){
                isAlive = 2;
            }
        }
        if (isAlive == 2){
            ImageIcon ii = new ImageIcon("./assets/img/res/character/t/t2/dying.png");
            this.objectImg = ii.getImage();
            boolean noFireLeft = true;
            for (Object o : board.getMap().objectList){
                if (o instanceof Fire) noFireLeft = false;
            }
            if (noFireLeft) isAlive = 0;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
