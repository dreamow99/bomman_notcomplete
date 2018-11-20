package Screens;


import Objects.Character.Enemy1;
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
    private final int delay = 1;
    private Player player;

    public Board(){
        initBoard();
    }

    private void initBoard(){
        addKeyListener(new TAdapter());
        map = new Map();
        timer = new Timer(delay, this);
        timer.start();
        player = map.getPlayer();
        this.setFocusable(true);
    }

    public void update() throws InterruptedException{
        for (int i = 0; i < map.objectList.size(); i++)
        {
            Object o = map.objectList.get(i);
            if (o instanceof Enemy1)
                ((Enemy1) o).move();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        try {
            update();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        player.updateMove();
        player.move();
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
