package Objects;

public class FixedWall extends Object {
    public FixedWall(int x, int y){
        super("wall");
        this.x = x;
        this.y = y;
        collidable = false;
        destroyable = false;
        visible = true;
    }
}