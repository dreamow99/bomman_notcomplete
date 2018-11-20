package Objects;

public class Crate extends Object {
    public Crate(int x, int y){
        super("crate", false);
        this.x = x;
        this.y = y;
        collidable = false;
        destroyable = true;
        visible = true;
    }

}
