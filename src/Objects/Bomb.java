package Objects;

public class Bomb extends Object {

    private int range;
    private long time;
    public boolean timeout;
    private int existTime = 3;


    public Bomb(int x, int y, int range){
        super("items/bomb", true);
        this.x = x;
        this.y = y;
        this.range = range;
        collidable = true;
        time = System.currentTimeMillis();
    }

    private void run(int playerX, int playerY, int playerW, int playerH){
        if (playerX > x + width || playerY > y + height || playerX + playerW < x || playerY + playerH < y){
            collidable = false;
        }

    }

    private void boom(){
        timeout = true;
    }

    public void live(int playerX, int playerY, int playerW, int playerH){
        if ((System.currentTimeMillis() - time)/1000F < existTime)
            run(playerX, playerY, playerW, playerH);

        else {
            boom();
        }
    }
}
