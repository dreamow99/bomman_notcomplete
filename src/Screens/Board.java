package Screens;


import Objects.Character.Player;
import Objects.Map;
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
    private final int delay = 10;
    private Player player;

    public Board(){
        initBoard();
    }

    private void initBoard(){
        addKeyListener(new TAdapter());
        loadImage();
        map = new Map();
        timer = new Timer(delay, this);
        timer.start();
        player = new Player();
        this.setFocusable(true);
    }

    private void loadImage(){

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        for (int i = 0; i < map.objectList.size(); i++){
            Object o = map.objectList.get(i);
            g2d.drawImage(o.getObjectImg(), o.getX(), o.getY(), this);

        }
        g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        step();
    }

    private void step(){
        player.move();

        repaint(player.getX()-1, player.getY()-2, player.getWidth()+2, player.getHeight()+3);
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
