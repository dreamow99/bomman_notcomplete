package Objects;

import Screens.Board;
import Sound.Sound;

public class Bomb extends Object {

    private int range;
    private long time;
    public boolean timeout = false;


    public Bomb(int x, int y, int range){
        super("items/bomb", true);
        this.x = x;
        this.y = y;
        this.range = range;
        collidable = true;
        time = System.currentTimeMillis();
    }

    private void run(int itemX, int playerY, int playerW, int playerH){
        if (itemX > x + width || playerY > y + height || itemX + playerW < x || playerY + playerH < y){
            collidable = false;
        }
    }

    private void boom(Board board){
        Sound boomSound;
        boomSound = new Sound("boom.wav");
        boomSound.play();
        timeout = true;
        Fire fire = new Fire (x, y, range, board);
        board.addToMap(fire);
    }

    private boolean catchFire(int x, int y, Map map){
        for (int i = 0; i < map.objectList.size(); i++){
            Object checkObject = map.objectList.get(i);
            if (checkObject instanceof Fire){
                if (x == checkObject.getX() && y == checkObject.getY())
                    return true;
            }
        }
        return false;
    }

    public void update(Board board){
        //System.out.println(catchFire(x,y,board.getMap()));
        if (catchFire(x, y, board.getMap())){
            boom(board);
        }
    }


    public void live(int playerX, int playerY, int playerW, int playerH, Board board){
        int existTime = 3;
        if ((System.currentTimeMillis() - time)/1000F < existTime){
            run(playerX, playerY, playerW, playerH);
            //update(board);
        }
        else {
            if (!timeout)
                boom(board);
        }
    }
}
