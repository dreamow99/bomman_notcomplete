package Screens;


import Objects.*;
import Objects.Character.Enemy1;
import Objects.Character.Oneal;
import Objects.Character.Player;
import Objects.Item.BombItem;
import Objects.Item.FlameItem;
import Objects.Item.Portal;
import Objects.Item.SpeedItem;
import Objects.Object;

import Sound.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Board class
 */
public class Board extends JPanel implements ActionListener {

    private Map map;
    private Player player;
    private boolean loose = false;
    private boolean win = false;
    private int enemyLeft;

    private Sound ingameSound;
    private Sound winSound;
    private Sound looseSound;

    /**
     * constructor
     */
    public Board(){
        initBoard();
    }

    /**
     * initiallize elements
     */
    private void initBoard() {
        Timer timer;
        int delay = 1;
        addKeyListener(new TAdapter());
        map = new Map(this);
        enemyLeft = map.getEnemy();
        timer = new Timer(delay, this);
        timer.start();
        this.setFocusable(true);
        initPlayer();
        ingameSound = new Sound("mainSound.wav");
        winSound = new Sound("win.wav");
        looseSound = new Sound("loose.wav");
        ingameSound.loop();
    }

    /**
     * get map
     * @return map
     */
    public Map getMap() {
        return map;
    }

    /**
     * initiallize player
     */
    private void initPlayer(){
        for (Object o : map.objectList){
            if (o instanceof Player)
                player = (Player) o;
        }
    }

    /**
     * get player's x
     * @return x
     */
    public int getXPlayer()
    {
        return player.getX();
    }

    /**
     * get player's y
     * @return y
     */
    public int getYPlayer()
    {
        return player.getY();
    }

    /**
     * update elements
     * @param g draw
     */
    public void update(Graphics g) {
        try
        { Thread.sleep(5); }
        catch(InterruptedException ex)
        { Thread.currentThread().interrupt(); }
        if (player.isAlive == 0){
            loose = true;
            ingameSound.stop();
            looseSound.play();
        }
        for (int i = 0; i < map.objectList.size(); i++)
        {
            Object o = map.objectList.get(i);
            if (o instanceof Enemy1)
                ((Enemy1) o).move(this);
            if (o instanceof Oneal)
                ((Oneal) o).move(this);
            if (o instanceof Crate)
                ((Crate) o).update(this);
            if (o instanceof Bomb && !((Bomb) o).timeout)
                ((Bomb) o).update(this);
            if (o instanceof Player)
                ((Player) o).update();
            if (o instanceof Enemy1)
                ((Enemy1) o).update();
            if (o instanceof Oneal)
                ((Oneal) o).update();
            if (o instanceof SpeedItem)
                ((SpeedItem) o).update(this);
            if (o instanceof BombItem)
                ((BombItem) o).update(this);
            if (o instanceof FlameItem)
                ((FlameItem) o).update(this);
            if (o instanceof Portal && enemyLeft == 0)
                ((Portal) o).update(this);
        }

        for (int i = 0; i < map.objectList.size(); i++)
        {
            Object o = map.objectList.get(i);
            if (o instanceof Bomb){
                Bomb b = (Bomb) o;
                if (b.timeout){
                    player.bombs.remove(b);
                    map.objectList.remove(b);
                }
            }
            if (o instanceof Crate){
                if (((Crate) o).isDestroyed == 1 && (System.currentTimeMillis() - ((Crate) o).countTime)/1000F >= 0.5)
                    map.objectList.remove(o);
            }
            if (o instanceof Enemy1){
                if(((Enemy1) o).isAlive == 0) {
                    enemyLeft--;
                    map.objectList.remove(o);
                }
            }
            if (o instanceof Oneal){
                if(((Oneal) o).isAlive == 0) {
                    enemyLeft--;
                    map.objectList.remove(o);
                }
            }
            if (o instanceof SpeedItem){
                if (!((SpeedItem) o).existence)
                    map.objectList.remove(o);
            }
            if (o instanceof BombItem){
                if (!((BombItem) o).existence)
                    map.objectList.remove(o);
            }
            if (o instanceof FlameItem){
                if (!((FlameItem) o).existence)
                    map.objectList.remove(o);
            }
            if (o instanceof Portal){
                if (!((Portal) o).existence) {
                    win = true;
                    ingameSound.stop();
                    winSound.play();
                    map.objectList.remove(o);
                }
            }
        }

        if (!player.bombs.isEmpty()){
            for (Bomb b : player.bombs){
                if (b.timeout){
                    continue;
                }
                b.live(player.getX(), player.getY(), player.getWidth(), player.getHeight(), this);
                map.add(b);
            }
        }

        for (int i = 0; i < map.objectList.size(); i++)
        {
            Object o = map.objectList.get(i);
            if (o instanceof Fire){
                if (!((Fire) o).isActive)continue;
                ((Fire) o).live();
            }
        }

        for (int i = 0; i < map.objectList.size(); i++)
        {
            Object o = map.objectList.get(i);
            if (o instanceof Fire){
                if (!((Fire) o).isActive){

                    map.objectList.remove(o);
                }
            }
        }
        repaint();
    }

    /**
     * add object to map
     * @param o object
     */
    public void addToMap(Object o){
        map.add(o);
    }

    /**
     * get map's width
     * @return width
     */
    public int getWidth(){
        return (map.getMapCollumn()) * 36 + 6;
    }

    /**
     * get map's height
     * @return height
     */
    public int getHeight(){
        return (map.getMapRow()) * 36 + 29;
    }

    /**
     * draw components
     * @param g graphic
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * draw elements
     * @param g graphic
     */
    private void doDrawing(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        if (!loose && !win){

            update(g);
            map.draw(g2d, this);
        }
        if (win && !loose) win(g);
        if (!win && loose) gameOver(g);
    }

    /**
     * draw win scene
     * @param g graphic
     */
    private void win(Graphics g){
        ingameSound.stop();
        Image gameOverImg;
        ImageIcon ii = new ImageIcon("assets/img/res/win.png");
        gameOverImg = ii.getImage();
        g.drawImage(gameOverImg, 0, 0, this);
    }

    /**
     * draw loose scene
     * @param g graphic
     */
    private void gameOver(Graphics g) {
        ingameSound.stop();
        Image gameOverImg;
        ImageIcon ii = new ImageIcon("assets/img/res/gameover.png");
        gameOverImg = ii.getImage();
        g.drawImage(gameOverImg, 0, 0, this);
    }

    /**
     * get current number of enemies
     * @return number of enemies
     */
    public int getCurrentEnemies(){
        return enemyLeft;
    }

    /**
     * handle action performed
     * @param e action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        step();
    }

    /**
     * make a move
     */
    private void step(){
        //player.updateMove();
        player.move(this);
        repaint();
    }

    /**
     * listen to key board
     */
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }
    }
}
