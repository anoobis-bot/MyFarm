import Entities.GameEnvironment;
import Entities.Land;
import Entities.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

public class GameOverController implements ActionListener {
    private static final String CODE_RESTART = "RESTART";
    private static final String CODE_QUIT = "QUIT";
    GameOverWindow gameOverWindow;
    public GameOverController(){
        // Creates the GUI of game over
        this.gameOverWindow = new GameOverWindow(this);
        gameOverWindow.gameOverFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StringTokenizer eventInfo = new StringTokenizer(e.getActionCommand(), ",");
        String opType = eventInfo.nextToken();

        // Buttons event after actionPerformed
        if (opType.equals(CODE_RESTART)){
            System.out.println("restart");
            this.gameOverWindow.gameOverFrame.dispose();

            // DOES THE SAME IN CREATING THE GAME IN MAIN
            GameEnvironment game = new GameEnvironment(5, 10); // farm size
            Player player = new Player(100, 0); // starting money/lvl

            // 2D array of Land(lot) to create whole farm
            Land[][] landMatrix = new Land[game.getYSize()][game.getXSize()];
            String GAME_NAME = "My Farm"; // Game Name

            // initializing land object in each element of landMatrix[][]
            for (int y = 0; y < game.getYSize(); y++)
                for (int x = 0; x < game.getXSize(); x++)
                    landMatrix[y][x] = new Land();

            new Controller(player, landMatrix, game, GAME_NAME);
        }
        else if (opType.equals(CODE_QUIT)){
            this.gameOverWindow.gameOverFrame.dispose(); //closes game over window
        }
    }
    public static String getCodeRestart(){return CODE_RESTART;}
    public static String getCodeQuit(){return CODE_QUIT;}
}
