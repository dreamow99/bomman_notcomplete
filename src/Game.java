import GameManager.MapGenerate;
import Objects.FixedWall;
import Screens.Board;

import javax.swing.JFrame;
import java.awt.EventQueue;

public class Game extends JFrame {
    public final int gameWidth = 1280;
    public final int gameHeight = 720;
    private final String gameTitle = "Bomberman";

    public Game(){
        initUI();
    }
    private void initUI(){
            add(new Board());
            pack();
            setTitle(gameTitle);
            setSize(gameWidth, gameHeight);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setResizable(false);

        }
            public static void main(String[] args){
                EventQueue.invokeLater( () -> {
            Game ex = new Game();
            ex.setVisible(true);
        });
    }
}
