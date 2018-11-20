package Objects;

public class Portal extends Object {
    public Portal(int x, int y){
        super("portal", false);
        this.x = x;
        this.y = y;
        collidable = true;
        destroyable = false;
        visible = true;
    }
}
