package Objects;

public class Ground extends Object {
    public Ground(int x, int y){
        super("ground", false);
        this.x = x;
        this.y = y;
        collidable = true;
        destroyable = false;
        visible = true;
    }
}
