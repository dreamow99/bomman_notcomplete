package Objects;

import javax.swing.*;
import java.awt.*;

public abstract class Object {
    protected Image objectImg;
    protected int x;
    protected int y;
    protected boolean collidable;
    protected boolean destroyable;
    protected boolean visible;

    protected int width;
    protected int height;


    public Object(String name){
        String imgPath = "./assets/img/res/" + name + ".png";
        ImageIcon ii = new ImageIcon(imgPath);
        objectImg = ii.getImage();

        width = objectImg.getWidth(null);
        height = objectImg.getHeight(null);

    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setCollidable(boolean collidable) {
        this.collidable = collidable;
    }

    public void setDestroyable(boolean destroyable) {
        this.destroyable = destroyable;
    }

    public Image getObjectImg() {
        return objectImg;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isCollidable() {
        return collidable;
    }

    public boolean isDestroyable() {
        return destroyable;
    }

    public boolean isVisible() {
        return visible;
    }

    public String toString(){
        return this.getClass().toString();
    }
}
