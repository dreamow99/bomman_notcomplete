package Objects.Character;

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
     * @return true/false
     */
    boolean checkCollision(int x, int y, int dx, int dy);

    /**
     * make a move
     */
    void move() throws InterruptedException;


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
