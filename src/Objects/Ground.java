/**
 * @author Pham Duc Duy
 * @author Nguyen Huu Dat
 * @version 1.0
 * @since 14/11/2018
 */

package Objects;

public class Ground extends Object {
    /**
     * Contructor Ground
     * @param x
     * @param y
     */
    public Ground(int x, int y){
        super("ground", false);
        this.x = x;
        this.y = y;
        collidable = true;
        destroyable = true;
        visible = true;
    }
}
