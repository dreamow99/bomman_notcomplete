package Objects.Character;

import Objects.Bomb;
import Objects.Map;
import Objects.Object;
import Screens.Board;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player extends Object implements Character {

    private int dx = 0;
    private int dy = 0;
    private boolean canMoveLeft = true;
    private boolean canMoveRight = true;
    private boolean canMoveUp = true;
    private boolean canMoveDown = true;

    private int bombRange = 2;
    private int bombSlot = 1;
    private Board board;


    public ArrayList<Bomb> bombs;

    // r l u d
    private int direction = 0;

    public Player(int x, int y, Board board){
        super("character/ct/default", false);
        this.x = x;
        this.y = y;
        collidable = true;
        destroyable = true;
        visible = true;
        width -= 10;
        height -= 3;
        bombs = new ArrayList<>();
        this.board = board;
    }

    public void addBombSlot(){
        bombSlot++;
    }


    @Override
    public void updateMove() {

        if (dx == 0 && dy == 0){
            ImageIcon ii = new ImageIcon("./assets/img/res/character/ct/default.png");
            this.objectImg = ii.getImage();
        }

        //going up
        if (dy == -1){
            ImageIcon ii = new ImageIcon("./assets/img/res/character/ct/goingup.gif");
            objectImg = ii.getImage();
        }

        // going down
        if (dy == 1){
            ImageIcon ii = new ImageIcon("./assets/img/res/character/ct/goingdown.gif");
            objectImg = ii.getImage();
        }


        //turning right
        if (dx == 1){
            ImageIcon ii = new ImageIcon("./assets/img/res/character/ct/turningright.gif");
            objectImg = ii.getImage();
        }

        //turning left
        if (dx == -1){
            ImageIcon ii = new ImageIcon("./assets/img/res/character/ct/turningleft.gif");
            objectImg = ii.getImage();
        }
    }

    @Override
    public boolean preCheckCollision(int x, int y, int dx, int dy, Map map) {
        return false;
    }

    @Override
    public void checkCollision(int x, int y, int dx, int dy, Map map) {

        // r l u d

        for (int i = 0; i < map.objectList.size(); i++){
            Object checkObject = map.objectList.get(i);
            if (y < checkObject.getY() + checkObject.getHeight() && x + dx + width > checkObject.getX() && checkObject.getX() > x
                    && y + height > checkObject.getY())
            {
                if (!checkObject.isCollidable())
                {
                    canMoveRight = false;
                }
            }
            if (y < checkObject.getY() + checkObject.getHeight() && x + dx < checkObject.getX() + checkObject.getWidth()
                    && checkObject.getX() < x
                    && y + height > checkObject.getY()) {
                if (!checkObject.isCollidable()){
                    canMoveLeft = false;
                }
            }
            if (y + dy < checkObject.getY() + checkObject.getHeight() && x < checkObject.getX() + checkObject.getWidth()
                    && checkObject.getX() < x + width
                    && checkObject.getY() < y) {
                if (!checkObject.isCollidable()){
                    canMoveUp = false;
                }
            }
            if (y + dy + height > checkObject.getY() && x < checkObject.getX() + checkObject.getWidth()
                    && checkObject.getX() < x + width
                    && checkObject.getY() > y) {
                if (!checkObject.isCollidable()){
                    canMoveDown = false;
                }
            }
        }
    }

    private boolean canPlantBomb(int bombX, int bombY){
        int count = 0;
        for (Bomb i : bombs){
            if (bombX == i.getX() && bombY == i.getY()) return false;
            if (!i.timeout) count++;
        }
        for (Object o : board.getMap().objectList){
            if (bombX == o.getX() && bombY == o.getY() && !o.isCollidable()) return false;
        }
        return count < bombSlot;
    }

    private void bombHasBeenPlanted(int x, int y, int range){
        x += width/2;
        y += height/2;
        int bombX, bombY;
        bombX =36 * (x / 36);
        bombY = 36 * (y / 36);

        if (canPlantBomb(bombX, bombY)){
            bombs.add(new Bomb(bombX, bombY, range));
        }
    }


    @Override
    public void move(Board board) {
        checkCollision(x, y, dx, dy, board.getMap());
        if (canMoveLeft && canMoveRight && canMoveUp && canMoveDown)
        {
            x+=dx;
            y+=dy;
        }
        else if (!canMoveUp && !canMoveLeft)
        {
            canMoveLeft = true;
            canMoveUp = true;
        }
        else if (!canMoveUp && !canMoveRight)
        {
            canMoveRight = true;
            canMoveUp = true;
        }
        else if (!canMoveDown && !canMoveLeft)
        {
            canMoveDown = true;
            canMoveLeft = true;
        }
        else if (!canMoveDown && !canMoveRight)
        {
            canMoveRight = true;
            canMoveDown = true;
        }
        else if (!canMoveLeft || !canMoveRight)
        {
            y += dy;
            x += 0.1;
            if (!canMoveRight)
            {
                canMoveRight = true;
            }
            else
            {
                canMoveLeft = true;
            }
        }
        else if (!canMoveUp || !canMoveDown)
        {
            x += dx;
            y += 0.1;
            if (!canMoveUp)
            {
                canMoveUp = true;
            }
            else
            {
                canMoveDown = true;
            }
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
            direction = 1;
            updateMove();
        }

        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
            direction = 2;
            updateMove();
        }

        if (key == KeyEvent.VK_UP) {
            dy = -1;
            direction = 3;
            updateMove();
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 1;
            direction = 4;
            updateMove();
        }

        if (key == KeyEvent.VK_SPACE){
            bombHasBeenPlanted(x, y, bombRange);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
            updateMove();
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
            updateMove();
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
            updateMove();
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
            updateMove();
        }
    }
}
