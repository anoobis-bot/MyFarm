import Constants.PlayerActions;
import Constants.SeedAttributes;
import Constants.ToolAttributes;

import java.util.Scanner;

public class Driver
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int userInput = -1;

        int toolPopulation = ToolAttributes.values().length;
        int seedPopulation = SeedAttributes.values().length;
        int actionsPopulation = PlayerActions.values().length;

        GameEnvironment game = new GameEnvironment(1, 1);
        Player player = new Player(10, 0, 0);
        Land[][] landMatrix = new Land[game.getYSize()][game.getXSize()];

        Display display = new Display(player, landMatrix, game);

        int xSize = game.getXSize();
        int ySize = game.getYSize();

        boolean withered = false;

        // initializing land
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                landMatrix[y][x] = new Land();
            }
        }

        do
        {
//            if (landMatrix[player.getPoint().getYCoordinate()][player.getPoint().getXCoordinate()].isWithered())
//                withered = true;
            display.render();

            // getting user input
            userInput = input.nextInt();
            if (userInput > 0 && userInput <= toolPopulation)
            {
                player.changeTool(ToolAttributes.values()[userInput - 1]);
            }
            else if (userInput <= toolPopulation + seedPopulation)
            {
                player.grabSeed(SeedAttributes.values()[userInput - toolPopulation - 1]);
            }
            else if (userInput <= toolPopulation + seedPopulation + actionsPopulation)
            {
                if (userInput == toolPopulation + seedPopulation + 1)
                    player.plantSeed(landMatrix);
            }

            System.out.print("\n\n");

            game.advanceTime();

            player.subtractCoin(); // loopbreaker lang(pansamantala)

        }while (player.getObjCoin() > 0 || !withered); // ||
    }
}
