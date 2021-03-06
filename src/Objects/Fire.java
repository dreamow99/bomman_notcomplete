/**
 * @author Pham Duc Duy
 * @author Nguyen Huu Dat
 * @version 1.0
 * @since 14/11/2018
 */

package Objects;

import Objects.Item.Portal;
import Screens.Board;

public class Fire extends Object{

    private int range;
    private Board board;
    public boolean isActive;
    private long time;

    /**
     * Contructor Fire. is that fire after boomb explosion
     * @param x
     * @param y
     * @param range
     * @param board
     */
    Fire(int x, int y, int range, Board board){
        super("/bombs/fire-center", false);
        this.x = x;
        this.y = y;
        this.range = range;
        this.board = board;
        collidable = true;
        destroyable = false;
        visible = true;
        isActive = true;
        time = System.currentTimeMillis();
        initFire();
    }

    /**
     * Contructor Fire. is that fire after boomb explosion but It is private
     * @param name
     * @param x
     * @param y
     * @param range
     * @param board
     */
    private Fire(String name, int x, int y, int range, Board board){
        super("/bombs/fire-" + name, false);
        this.x = x;
        this.y = y;
        this.range = range;
        this.board = board;
        collidable = true;
        destroyable = false;
        visible = true;
        isActive = true;
        time = System.currentTimeMillis();
    }

    /**
     * initFire method is use to init Fire
     */
    private void initFire(){

        // go down
        int curX = x;
        int curY = y;
        for (int i = 0; i < range; i++){
            curY += 36;
            if (checkCollision(curX, curY, board.getMap()) == 0) break;
            if (checkCollision(curX, curY, board.getMap()) == 1) {
                board.addToMap(new Fire(" ", curX, curY, 1, board));
                break;
            }
            if (i != range - 1)
                board.addToMap(new Fire("vertical", curX, curY, 1, board));
            else board.addToMap(new Fire("vertical-bot", curX, curY, 1, board));
        }

        // go up
        curX = x;
        curY = y;
        for (int i = 0; i < range; i++){
            curY -= 36;
            if (checkCollision(curX, curY, board.getMap()) == 0) break;
            if (checkCollision(curX, curY, board.getMap()) == 1) {
                board.addToMap(new Fire(" ", curX, curY, 1, board));
                break;
            }
            if (i != range - 1)
                board.addToMap(new Fire("vertical", curX, curY, 1, board));
            else board.addToMap(new Fire("vertical-top", curX, curY, 1, board));
        }

        // turn right
        curX = x;
        curY = y;
        for (int i = 0; i < range; i++){
            curX += 36;
            if (checkCollision(curX, curY, board.getMap()) == 0) break;
            if (checkCollision(curX, curY, board.getMap()) == 1) {
                board.addToMap(new Fire(" ", curX, curY, 1, board));
                break;
            }
            if (i != range - 1)
                board.addToMap(new Fire("horizontal", curX, curY, 1, board));
            else board.addToMap(new Fire("horizontal-right", curX, curY, 1, board));
        }

        // turn left
        curX = x;
        curY = y;
        for (int i = 0; i < range; i++){
            curX -= 36;
            if (checkCollision(curX, curY, board.getMap()) == 0) break;
            if (checkCollision(curX, curY, board.getMap()) == 1) {
                board.addToMap(new Fire(" ", curX, curY, 1, board));
                break;
            }
            if (i != range - 1)
                board.addToMap(new Fire("horizontal", curX, curY, 1, board));
            else board.addToMap(new Fire("horizontal-left", curX, curY, 1, board));
        }

    }

    /**
     * Overriding checkCollision method
     * @param x
     * @param y
     * @param map
     * @return
     */
    private int checkCollision(int x, int y, Map map) {
        for (Object o : map.objectList){
            if (o instanceof Portal) continue;
            if (o.getX() == x && o.getY() == y && o instanceof FixedWall){
                //System.out.println(x + " " + y + " " + o.toString());
                return 0;
            }
            if (o.getX() == x && o.getY() == y && o instanceof Crate)
                return 1;
        }
        return 2;
    }

    /**
     * Live method
     */
    public void live(){
        if ((System.currentTimeMillis() - time)/1000F >= 0.5){
            isActive = false;
        }
    }
}
