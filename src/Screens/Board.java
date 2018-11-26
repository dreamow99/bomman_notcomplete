package Screens;


import Objects.*;
import Objects.Character.Enemy1;
import Objects.Character.Player;
import Objects.Object;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Map map;
    private final int delay = 1;
    private Player player;

    public Board(){
        initBoard();
    }

    private void initBoard(){
        addKeyListener(new TAdapter());
        map = new Map(this);
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
            //TO DO : lam game over
        }
        for (int i = 0; i < map.objectList.size(); i++)
        {
            Object o = map.objectList.get(i);
            if (o instanceof Enemy1)
                ((Enemy1) o).move(this);
            if (o instanceof Crate)
                ((Crate) o).update(this);
            if (o instanceof Player)
                ((Player) o).update();
            if (o instanceof Enemy1)
                ((Enemy1) o).update();
            if (o instanceof SpeedItem)
                ((SpeedItem) o).update(this);
            if (o instanceof BombItem)
                ((BombItem) o).update(this);
            if (o instanceof FlameItem)
                ((FlameItem) o).update(this);
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
                if(((Enemy1) o).isAlive == 0)
                    map.objectList.remove(o);
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

    public void deleteObject(Object o){
        map.objectList.remove(o);
    }

    @Override
    protected void paintComponent(Graphics g) {
        update(g);
        super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        map.draw(g2d, this);
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
