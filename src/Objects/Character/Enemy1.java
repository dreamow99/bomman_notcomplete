package Objects.Character;

import Objects.Fire;
import Objects.Map;
import Objects.Object;
import Screens.Board;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * low-class enemy class
 */
public class Enemy1 extends Object implements Character {

    private int dx = 0;
    private int dy = 0;
    private Board board;

    public int isAlive; // 0 - no; 1 - yes; 2 - dying

    // r l u d 1 2 3 4
    private int direction = 0;

    /**
     * constructor
     * @param x x coordinate
     * @param y y coordinate
     * @param board board
     */
    public Enemy1(int x, int y, Board board) {
        super("character/t/t1/default", false);
        this.x = x;
        this.y = y;
        this.board = board;
        collidable = true;
        destroyable = true;
        visible = true;
        isAlive = 1;
    }

    /**
     * update move animation
     */
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

    /**
     * check whether can move or not
     * @param x current x
     * @param y current y
     * @param dx next x
     * @param dy next y
     * @param map map to check
     * @return true or false
     */
    @Override
    public boolean preCheckCollision(int x, int y, int dx, int dy, Map map) {
        for (int i = 0; i < map.objectList.size(); i++){
            Object checkObject = map.objectList.get(i);
            if (y < checkObject.getY() + checkObject.getHeight() && x + dx + width > checkObject.getX() && checkObject.getX() > x
                    && y + height > checkObject.getY() && dx == 1 && dy == 0)
            {
                if (!checkObject.isCollidable())
                {
                    //System.out.println(checkObject.getX() + " " + checkObject.getY() + " " + checkObject.toString());
                    return false;
                }
            }
            if (y < checkObject.getY() + checkObject.getHeight() && x + dx < checkObject.getX() + checkObject.getWidth()
                    && checkObject.getX() < x
                    && y + height > checkObject.getY() && dx == -1 && dy == 0) {
                if (!checkObject.isCollidable()){
                    //System.out.println(checkObject.getX() + " " + checkObject.getY() + " " + checkObject.toString());
                    return false;
                }
            }
            if (y + dy < checkObject.getY() + checkObject.getHeight() && x < checkObject.getX() + checkObject.getWidth()
                    && checkObject.getX() < x + width
                    && checkObject.getY() < y && dx == 0 && dy == -1) {
                if (!checkObject.isCollidable()){
                    //System.out.println(checkObject.getX() + " " + checkObject.getY() + " " + checkObject.toString());
                    return false;
                }
            }
            if (y + dy + height > checkObject.getY() && x < checkObject.getX() + checkObject.getWidth()
                    && checkObject.getX() < x + width
                    && checkObject.getY() > y && dx == 0 && dy == 1) {
                if (!checkObject.isCollidable()){
                    //System.out.println(checkObject.getX() + " " + checkObject.getY() + " " + checkObject.toString());
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * check collision
     * @param x x coordinate
     * @param y y coordinate
     * @param dx x velocity
     * @param dy y velocity
     * @param map map to check
     */
    @Override
    public void checkCollision(int x, int y, int dx, int dy, Map map) {
        // r l u d
    }

    /**
     * make a move
     * @param board board to render
     */
    @Override
    public void move(Board board) {
        if (isAlive == 2){
            dx = 0;
            dy = 0;
            return;
        }

        boolean canMoveUp = preCheckCollision(x, y, 0, -1, board.getMap());
        boolean canMoveDown = preCheckCollision(x, y, 0, 1, board.getMap());
        boolean canMoveLeft = preCheckCollision(x, y, -1, 0, board.getMap());
        boolean canMoveRight = preCheckCollision(x, y, 1, 0, board.getMap());

        //System.out.println(canMoveLeft + " " + canMoveRight + " " + canMoveUp + " " + canMoveDown);
        //System.out.println(direction);
        // 1 way to go - up
        if (!canMoveLeft && !canMoveRight && canMoveUp && !canMoveDown){
            direction = 3;
            dy = -1;
            dx = 0;
        }
        // 1 way to go - down
        if (!canMoveLeft && !canMoveRight && !canMoveUp && canMoveDown){
            direction = 4;
            dy = 1;
            dx = 0;
        }
        // 1 way to go - left
        if (canMoveLeft && !canMoveRight && !canMoveUp && !canMoveDown){
            direction = 2;
            dy = 0;
            dx = -1;
        }
        // 1 way to go - right
        if (!canMoveLeft && canMoveRight && !canMoveUp && !canMoveDown){
            direction = 1;
            dy = 0;
            dx = 1;
        }

        // 2 ways to go - up down
        if (!canMoveLeft && !canMoveRight && canMoveUp && canMoveDown){
            if (direction != 3 && direction != 4){
                direction = (int) (Math.random() * 50) % 2;
                if (direction == 0) {
                    direction = 3;
                    dx = 0;
                    dy = -1;
                }
                else {
                    direction = 4;
                    dx = 0;
                    dy = 1;
                }
            }
            else {
                if (direction == 3) {
                    dx = 0;
                    dy = -1;
                }
                else {
                    dx = 0;
                    dy = 1;
                }
            }
        }

        //2 ways to go - left right
        if (canMoveLeft && canMoveRight && !canMoveUp && canMoveDown){
            if (direction != 1 && direction != 2){
                direction = (int) (Math.random() * 50) % 2;
                if (direction == 0) {
                    direction = 1;
                    dx = 1;
                    dy = 0;
                }
                else {
                    direction = 2;
                    dx = -1;
                    dy = 0;
                }
            }
            else {
                if (direction == 1) {
                    dx = 1;
                    dy = 0;
                }
                else {
                    dx = -1;
                    dy = 0;
                }
            }
        }

        // 2 ways to go - up right
        if (!canMoveLeft && canMoveRight && canMoveUp && !canMoveDown){
            if (direction != 1 && direction != 3){
                direction = (int) (Math.random() * 50) % 2;
                if (direction == 0) {
                    direction = 1;
                    dx = 1;
                    dy = 0;
                }
                else {
                    direction = 3;
                    dx = 0;
                    dy = -1;
                }
            }
            else {
                if (direction == 1) {
                    dx = 1;
                    dy = 0;
                }
                else {
                    dx = 0;
                    dy = -1;
                }
            }
        }

        // 2 ways to go - up left
        if (canMoveLeft && !canMoveRight && canMoveUp && !canMoveDown){
            if (direction != 2 && direction != 3){
                direction = (int) (Math.random() * 50) % 2;
                if (direction == 0) {
                    direction = 2;
                    dx = -1;
                    dy = 0;
                }
                else {
                    direction = 3;
                    dx = 0;
                    dy = -1;
                }
            }
            else {
                if (direction == 2) {
                    dx = -1;
                    dy = 0;
                }
                else {
                    dx = 0;
                    dy = -1;
                }
            }
        }

        // 2 ways to go - down left
        if (canMoveLeft && !canMoveRight && !canMoveUp && canMoveDown){
            if (direction != 2 && direction != 4){
                direction = (int) (Math.random() * 50) % 2;
                if (direction == 0) {
                    direction = 2;
                    dx = -1;
                    dy = 0;
                }
                else {
                    direction = 4;
                    dx = 0;
                    dy = 1;
                }
            }
            else {
                if (direction == 2) {
                    dx = -1;
                    dy = 0;
                }
                else {
                    dx = 0;
                    dy = 1;
                }
            }
        }

        // 2 ways to go - down right
        if (!canMoveLeft && canMoveRight && !canMoveUp && canMoveDown){
            if (direction != 1 && direction != 4){
                direction = (int) (Math.random() * 50) % 2;
                if (direction == 0) {
                    direction = 1;
                    dx = 1;
                    dy = 0;
                }
                else {
                    direction = 4;
                    dx = 0;
                    dy = 1;
                }
            }
            else {
                if (direction == 1) {
                    dx = 1;
                    dy = 0;
                }
                else {
                    dx = 0;
                    dy = 1;
                }
            }
        }


        // 3 ways to go - up down right
        if (!canMoveLeft && canMoveRight && canMoveUp && canMoveDown){
            //int turnOrNot = (int )(Math.random() * 50) % 2;
            if (direction == 2 || direction == 0){
                direction = (int) (Math.random() * 50) % 3;
                if (direction == 0) {
                    direction = 1;
                    dx = 1;
                    dy = 0;
                }
                else if (direction == 1)
                {
                    direction = 4;
                    dx = 0;
                    dy = 1;
                }
                else {
                    direction = 3;
                    dx = 0;
                    dy = -1;
                }
            }
            else {
                if (direction == 1){
                    dx = 1;
                    dy = 0;
                }
                if (direction == 3){
                    dx = 0;
                    dy = -1;
                }
                if (direction == 4){
                    dx = 0;
                    dy = 1;
                }
            }
        }

        // 3 ways to go - up down left
        if (canMoveLeft && !canMoveRight && canMoveUp && canMoveDown){
            if (direction == 1 || direction == 0){
                direction = (int) (Math.random() * 50) % 3;
                if (direction == 0) {
                    direction = 2;
                    dx = -1;
                    dy = 0;
                }
                else if (direction == 1)
                {
                    direction = 4;
                    dx = 0;
                    dy = 1;
                }
                else {
                    direction = 3;
                    dx = 0;
                    dy = -1;
                }
            }
            else {
                if (direction == 2){
                    dx = -1;
                    dy = 0;
                }
                if (direction == 3){
                    dx = 0;
                    dy = -1;
                }
                if (direction == 4){
                    dx = 0;
                    dy = 1;
                }
            }
        }

        // 3 ways to go - left right down
        if (canMoveLeft && canMoveRight && !canMoveUp && canMoveDown){
            if (direction == 3 || direction == 0){
                direction = (int) (Math.random() * 50) % 3;
                if (direction == 0) {
                    direction = 2;
                    dx = -1;
                    dy = 0;
                }
                else if (direction == 1)
                {
                    direction = 4;
                    dx = 0;
                    dy = 1;
                }
                else {
                    direction = 1;
                    dx = 1;
                    dy = 0;
                }
            }
            else {
                if (direction == 2){
                    dx = -1;
                    dy = 0;

                }
                if (direction == 1){
                    dx = 1;
                    dy = 0;
                }
                if (direction == 4){
                    dx = 0;
                    dy = 1;
                }
            }
        }

        // 3 ways to go - left right up
        if (canMoveLeft && canMoveRight && canMoveUp && !canMoveDown){
            if (direction == 4 || direction == 0){
                direction = (int) (Math.random() * 50) % 3;
                if (direction == 0) {
                    direction = 2;
                    dx = -1;
                    dy = 0;
                }
                else if (direction == 1)
                {
                    direction = 3;
                    dx = 0;
                    dy = -1;
                }
                else {
                    direction = 1;
                    dx = 1;
                    dy = 0;
                }
            }
            else {
                if (direction == 2){
                    dx = -1;
                    dy = 0;
                }
                if (direction == 1){
                    dx = 1;
                    dy = 0;
                }
                if (direction == 3){
                    dx = 0;
                    dy = -1;
                }
            }
        }


        // 4 ways to go
        if (canMoveLeft && canMoveRight && canMoveUp && canMoveDown){
            if (direction == 0){
                direction = (int) (Math.random() * 50) % 4;
                if (direction == 0) {
                    direction = 2;
                    dx = -1;
                    dy = 0;
                }
                if (direction == 1)
                {
                    direction = 3;
                    dx = 0;
                    dy = -1;
                }
                if (direction == 2){
                    direction = 1;
                    dx = 1;
                    dy = 0;
                }
                else {
                    direction = 4;
                    dx = 0;
                    dy = 1;
                }
            }
            else {
                if (direction == 2){
                    dx = -1;
                    dy = 0;
                }
                if (direction == 1){
                    dx = 1;
                    dy = 0;

                }
                if (direction == 3){
                    dx = 0;
                    dy = -1;
                }
                if (direction == 4){
                    dx = 0;
                    dy = 1;
                }
            }
        }
        updateMove();
        y += dy;
        x += dx;

    }

    /**
     * check when catch fire
     * @param x x coordinate
     * @param y y coordinate
     * @param map map to check
     * @return true/false
     */
    // TO DO: Check hoi ngao
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

    /**
     * update enemy status
     */
    @Override
    public void update() {
        if (isAlive == 1){
            if (catchFire(x, y, board.getMap())){
                isAlive = 2;
            }
        }
        if (isAlive == 2){
            ImageIcon ii = new ImageIcon("./assets/img/res/character/t/t1/dying.png");
            this.objectImg = ii.getImage();
            boolean noFireLeft = true;
            for (Object o : board.getMap().objectList){
                if (o instanceof Fire) noFireLeft = false;
            }
            if (noFireLeft) isAlive = 0;
        }
    }

    /**
     * check key pressed
     * @param e key event
     */
    @Override
    public void keyPressed(KeyEvent e) {

    }

    /**
     * check key released
     * @param e key event
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
