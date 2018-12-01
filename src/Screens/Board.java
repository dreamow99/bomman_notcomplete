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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements ActionListener {

    private Map map;
    private Player player;
    private boolean loose = false;
    private boolean win = false;
    private int enemyLeft;

    public Board(){
        initBoard();
    }

    private void initBoard(){
        Timer timer;
        int delay = 1;
        addKeyListener(new TAdapter());
        map = new Map(this);
        enemyLeft = map.getEnemy();
        timer = new Timer(delay, this);
        timer.start();
        this.setFocusable(true);
        initPlayer();
    }

    public Map getMap() {
        return map;
    }

    private void initPlayer(){
        for (Object o : map.objectList){
            if (o instanceof Player)
                player = (Player) o;
        }
    }

    public void update(Graphics g) {
        if (player.isAlive == 0){
            loose = true;
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
                if (!((Fire) o).isActive) map.objectList.remove(o);
            }
        }


        repaint();
    }

    public void addToMap(Object o){
        map.add(o);
    }

    public int getWidth(){
        return (map.getMapCollumn()) * 36 + 6;
    }

    public int getHeight(){
        return (map.getMapRow()) * 36 + 29;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        if (!loose && !win){
            update(g);
            map.draw(g2d, this);
        }
        if (win && !loose) win(g);
        if (!win && loose) gameOver(g);
    }

    private void win(Graphics g){
        Image gameOverImg;
        ImageIcon ii = new ImageIcon("assets/img/res/win.png");
        gameOverImg = ii.getImage();
        g.drawImage(gameOverImg, 0, 0, this);
    }

    private void gameOver(Graphics g) {
        Image gameOverImg;
        ImageIcon ii = new ImageIcon("assets/img/res/gameover.png");
        gameOverImg = ii.getImage();
        g.drawImage(gameOverImg, 0, 0, this);
    }

    public int getCurrentEnemies(){
        return enemyLeft;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        step();
    }

    private void step(){
        //player.updateMove();
        player.move(this);
        repaint();
    }

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
