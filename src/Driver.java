import Entities.GameEnvironment;
import Entities.Land;
import Entities.Player;

/**
 * file name: Driver.java
 * @author Francis De Leon
 * @author Mark Abergos
 *
 * This file contains the Main method.
 */
public class Driver
{
    public static void main(String[] args)
    {
        // Instantiates all the entities in the game
        GameEnvironment game = new GameEnvironment();
        if (Loading.loadGameSize(game))
        {
            Player player = new Player(GameEnvironment.STARTING_COIN, GameEnvironment.STARTING_EXP);

            Land[][] landMatrix = new Land[game.getYSize()][game.getXSize()];
            String GAME_TITLE = "My Farm"; // Game Name

            // initializing land object in each element of landMatrix[][] through loading
            Loading.loadLand(game, landMatrix);

            new IntroController(player, landMatrix, game, GAME_TITLE);
        }

    }
}
