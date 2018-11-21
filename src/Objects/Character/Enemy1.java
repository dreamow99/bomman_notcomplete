package Objects.Character;

import Objects.Map;
import Objects.Object;
import Screens.Board;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Random;


public class Enemy1 extends Object implements Character {

    private int movePath = 0;
    private int dx = 1;
    private int dy = 1;
    private boolean canMoveLeft = true;
    private boolean canMoveRight = true;
    private boolean canMoveUp = true;
    private boolean canMoveDown = true;
    Random random = new Random();
    public Enemy1(int x, int y) {
        super("character/t/t1/default", false);
        this.x = x;
        this.y = y;
        collidable = true;
        destroyable = true;
        visible = true;
        width -= 10;
        height -= 3;
    }

    @Override
    public void updateMove(){
        // Random random = new Random();
        movePath = random.nextInt(4);
        if (movePath == 1)
        {
            dx = 1;
        }
        else if (movePath == 2)
        {
            dx = -1;
        }
        else if (movePath == 3)
        {
            dy = -1;
        }
        else if (movePath == 4)
        {
            dy = 1;
        }
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
    public void checkCollision(int x, int y, int dx, int dy, Map map) {
        // r l u d

        canMoveRight = true;
        canMoveLeft = true;
        canMoveUp = true;
        canMoveDown = true;

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

    @Override
    public void move(Board board) throws InterruptedException {
        //move horizontal

      /*  if (movePath == 0){

            dy = 0;
            updateMove();
            if (checkCollision(x, y, dx,0, board.getMap())){
                x += dx;
                TimeUnit.MICROSECONDS.sleep(100);
            } else {
                if (dx == 1) dx = -1;
                else if (dx == -1) dx = 1;
            }
        }
        //move vertical
        else if (movePath == 2){

            dx = 0;
            updateMove();
            if (checkCollision(x, y, 0, dy, board.getMap())){
                y += dy;
                TimeUnit.MICROSECONDS.sleep(100);
            } else {
                if (dy == 1) dy = -1;
                else if (dy == -1) dy = 1;
            }
        }*/


        movePath = (int )(Math.random() * 4);
        checkCollision(x, y, dx, dy, board.getMap());

        if (movePath == 0){
            dx = 1;
            dy = 0;
            if (canMoveRight) x+= dx;
        }

        if (movePath == 1){
            dx = -1;
            dy = 0;
            if (canMoveLeft) x += dx;
        }

        if (movePath == 2){
            dx = 0;
            dy = -1;
            if (canMoveUp) y += dy;
        }

        if (movePath == 3){
            dx = 0;
            dy = 1;
            if (canMoveDown) y += dy;
        }


        /*ArrayList<Integer> direction = new ArrayList<>();
        checkCollision(x, y, dx, dy, board.getMap());
        if (canMoveDown) direction.add(4);
        if (canMoveUp) direction.add(3);
        if (canMoveLeft) direction.add(2);
        if (canMoveRight) direction.add(1);



        if (direction.size() == 0) return;
        movePath = direction.get((int )(Math.random() * direction.size()));
        System.out.println(x + " " + y + " " + movePath);
        if (movePath == 1){
            dx = 1;
            dy = 0;
            x += dx;
            y += dy;
        }

        if (movePath == 2){
            dx = -1;
            dy = 0;
            x += dx;
            y += dy;
        }

        if (movePath == 3){
            dx = 0;
            dy = -1;
            x += dx;
            y += dy;
        }

        if (movePath == 4){
            dx = 0;
            dy = 1;
            x += dx;
            y += dy;
        }


        /*if (canMoveLeft && canMoveRight && canMoveUp && canMoveDown)
        {
            x+=dx;
            y+=dy;
            TimeUnit.MICROSECONDS.sleep(100);
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
            TimeUnit.MICROSECONDS.sleep(100);
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
            TimeUnit.MICROSECONDS.sleep(100);
            if (!canMoveUp)
            {
                canMoveUp = true;
            }
            else
            {
                canMoveDown = true;
            }
        }*/
    }

    public void setMovePath(int movePath) {
        this.movePath = movePath;
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
