package Objects;

import Objects.Character.Player;
import Screens.Board;

public class SpeedItem extends Object {
    public boolean existence = true;
    public boolean collideWithPlayer = false;

    public SpeedItem(int x, int y){
        super("items/speedItem", false);
        this.x = x;
        this.y = y;
        collidable = true;
        destroyable = false;
        visible = true;
    }

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

    public void update(Board board){
        for (Object o : board.getMap().objectList){
            if (o instanceof Player){
                if (checkCollision(x, y, o.getX(), o.getY(), o.getWidth(), o.getHeight()))
                    collideWithPlayer = true;
            }
        }
    }
}
