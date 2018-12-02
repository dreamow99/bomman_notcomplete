/**
 * @author Pham Duc Duy
 * @author Nguyen Huu Dat
 * @version 1.0
 * @since 14/11/2018
 */

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


    /**
     * Contructor Object is use to init and store object such as enemy, player, ground, ...v.v
     * @param name
     * @param isGif
     */
    public Object(String name, boolean isGif){
        String imgPath;
        if (!isGif)
            imgPath = "./assets/img/res/" + name + ".png";
        else {
            imgPath = "./assets/img/res/" + name + ".gif";
        }

        ImageIcon ii = new ImageIcon(imgPath);
        objectImg = ii.getImage();

        width = objectImg.getWidth(null);
        height = objectImg.getHeight(null);

    }

    /**
     * set x
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * set y
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * set width
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * get Height
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Setting coolidable of objects
     * @param collidable
     */
    public void setCollidable(boolean collidable) {
        this.collidable = collidable;
    }

    /**
     * Setting visible of objects
     * @param visible
     */
    public void setVisible(boolean visible) { this.visible = visible; }

    /**
     * Setting destroyable of objects
     * @param destroyable
     */
    public void setDestroyable(boolean destroyable) {
        this.destroyable = destroyable;
    }

    /**
     * getting image of objects
     * @return
     */
    public Image getObjectImg() {
        return objectImg;
    }

    /**
     * getting X
     * @return X
     */
    public int getX() {
        return x;
    }

    /**
     * Getting Y
     * @return Y
     */
    public int getY() {
        return y;
    }

    /**
     * checking 2 objects is collidable
     * @return true/false
     */
    public boolean isCollidable() {
        return collidable;
    }

    /**
     * checking object is destroyable
     * @return true/false
     */
    public boolean isDestroyable() {
        return destroyable;
    }

    /**
     * checking object is visible
     * @return true/false
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * toString
     * @return String
     */
    public String toString(){
        return this.getClass().toString();
    }
}
