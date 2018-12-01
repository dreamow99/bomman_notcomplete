
import Screens.Board;

import javax.swing.JFrame;
import java.awt.EventQueue;

public class Game extends JFrame {
    private int gameWidth;
    private int gameHeight;
    private final String gameTitle = "Bomberman";
    private Board board;

    private Game(){
        initUI();
    }
    private void initUI(){
        board = new Board();
        gameWidth = board.getWidth();
        gameHeight = board.getHeight();
        add(board);
        setResizable(false);
        pack();
        setTitle(gameTitle);
        setSize(gameWidth, gameHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args){
        EventQueue.invokeLater( () -> {
            Game ex = new Game();
            ex.setVisible(true);
        });
    }
}
