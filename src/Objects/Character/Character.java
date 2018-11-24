package Objects.Character;

import Objects.Map;
import Screens.Board;

import java.awt.event.KeyEvent;

public interface Character {

    /**
     * update motion when moving
     */
    void updateMove();

    /**
     * check collision
     * @param x x coordinate
     * @param y y coordinate
     * @param dx x velocity
     * @param dy y velocity
     */
    void checkCollision(int x, int y, int dx, int dy, Map map);


    boolean preCheckCollision(int x, int y, int dx, int dy, Map map);

    /**
     * make a move
     */
    void move(Board board) throws InterruptedException;


    /**
     * handle key pressed
     * @param e key event
     */
    void keyPressed(KeyEvent e);

    /**
     * handle key released
     * @param e key event
     */
    void keyReleased(KeyEvent e);
}
