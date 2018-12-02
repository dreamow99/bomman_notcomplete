/**
 * @author Pham Duc Duy
 * @author Nguyen Huu Dat
 * @version 1.0
 * @since 14/11/2018
 */

package Objects;

public class FixedWall extends Object {
    /**
     * Contructor FixedWall is init wall can't destroy
     * @param x
     * @param y
     */
    public FixedWall(int x, int y){
        super("wall", false);
        this.x = x;
        this.y = y;
        collidable = false;
        destroyable = false;
        visible = true;
    }
}
