import Entities.GameEnvironment;
import Entities.Land;
import Entities.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

public class GameOverController implements ActionListener {
    private static final String CODE_RESTART = "RESTART";
    private static final String CODE_QUIT = "QUIT";
    Controller controller;
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
            // DOES THE SAME IN CREATING THE GAME IN MAIN
            GameEnvironment game = new GameEnvironment();
            Loading.loadGameSize(game);

            Player player = new Player(100, 0);

            Land[][] landMatrix = new Land[game.getYSize()][game.getXSize()];
            String GAME_TITLE = "My Farm"; // Game Name

            // initializing land object in each element of landMatrix[][]
            Loading.loadLand(game, landMatrix);

            this.gameOverWindow.gameOverFrame.dispose();
            controller = new Controller(player, landMatrix, game, GAME_TITLE);
        }
        else if (opType.equals(CODE_QUIT)){
            this.gameOverWindow.gameOverFrame.dispose(); //closes game over window
        }
    }
    public static String getCodeRestart(){return CODE_RESTART;}
    public static String getCodeQuit(){return CODE_QUIT;}
}
