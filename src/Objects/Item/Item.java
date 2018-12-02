package Objects.Item;

import Objects.Character.Player;
import Objects.Object;
import Screens.Board;
import Sound.Sound;


/**
 * item class
 */
public abstract class Item extends Object {

    public boolean existence = true;
    public boolean collideWithPlayer = false;

    /**
     * constructor
     * @param x x
     * @param y y
     * @param name image file name
     */
    Item(int x, int y, String name){
        super("items/" + name, false);
        this.x = x;
        this.y = y;
        collidable = true;
        destroyable = false;
        visible = true;
    }

    /**
     * check collision
     * @param x x
     * @param y y
     * @param playerX x player
     * @param playerY y player
     * @param playerW player's width
     * @param playerH player's height
     * @return true/false
     */
    private boolean checkCollision(int x, int y, int playerX, int playerY, int playerW, int playerH){
        if (playerX + playerW > x && playerY + playerH > y &&
                playerX + playerW < x + width && playerY + playerH < y + height) return true;
        if (playerX + playerW > x && playerY < y + height &&
                playerX + playerW < x + width && playerY > y) return true;
        if (playerX < x + width && playerY + playerH > y &&
                playerX > x && playerY + playerH < y + height) return true;
        return playerX < x + width && playerY < y + height &&
                playerX > x && playerY > y;
    }

    /**
     * update status
     * @param board board
     */
    public void update(Board board){
        for (Object o : board.getMap().objectList){
            if (o instanceof Player){
                if (checkCollision(x, y, o.getX(), o.getY(), o.getWidth(), o.getHeight())) {
                    Sound eatSound = new Sound("eating.wav");
                    eatSound.play();
                    collideWithPlayer = true;
                }
            }
        }
    }
}
