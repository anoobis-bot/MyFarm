/*
    This java file contains the main method
 */

import Constants.PlayerActions;
import Constants.SeedAttributes;
import Constants.ToolAttributes;

import java.util.Scanner;

public class Driver
{
    public static void main(String[] args)
    {
        // Initializing the input, player, game, and land, and display objects
        Scanner input = new Scanner(System.in);
        int userInput;

        GameEnvironment game = new GameEnvironment(1, 1);
        Player player = new Player(10, 0, 0);
        Land[][] landMatrix = new Land[game.getYSize()][game.getXSize()];

        Display display = new Display(player, landMatrix, game);

        // To lessen cpu usage, these value are already assigned. This will be used when asking for input
        int toolPopulation = ToolAttributes.values().length;
        int seedPopulation = SeedAttributes.values().length;
        int actionsPopulation = PlayerActions.values().length;

        int xSize = game.getXSize();
        int ySize = game.getYSize();

        boolean withered = false;

        // initializing land object in each element of landMatrix[][]
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                landMatrix[y][x] = new Land();
            }
        }

        // Start of game loop until game over
        do
        {
//            if (landMatrix[player.getPoint().getYCoordinate()][player.getPoint().getXCoordinate()].isWithered())
//                withered = true;

            // Render to console
            display.render();

            // getting user input
            userInput = input.nextInt();
            // if the user's input are the number range for tools
            if (userInput <= 0)
                // TODO catch error when user enters 0 or negative input
                ;
            else if (userInput <= toolPopulation)
            {
                player.changeTool(ToolAttributes.values()[userInput - 1]);
            }
            // if the user's input are the number range for seeds
            else if (userInput <= toolPopulation + seedPopulation)
            {
                player.grabSeed(SeedAttributes.values()[userInput - toolPopulation - 1]);
            }
            // if the user's input are the number range for player actions
            else if (userInput <= toolPopulation + seedPopulation + actionsPopulation)
            {
                // if the user decided to plant seed
                if (userInput == toolPopulation + seedPopulation + PlayerActions.PLANT.ordinal() + 1)
                    player.plantSeed(landMatrix);
                // if the user decided to use tool
                else if (userInput == toolPopulation + seedPopulation + PlayerActions.USE_TOOL.ordinal() + 1)
                    // TODO code for using tool
                    ;
                // if the user decided to proceed to next day
                else if (userInput == toolPopulation + seedPopulation + PlayerActions.NEXT_DAY.ordinal() + 1)
                {
                    game.advanceTime();
                    // Checks each land object if it has a seed, then increment its age
                    for (int y = 0; y < ySize; y++)
                    {
                        for (int x = 0; x < xSize; x++)
                        {
                            if (landMatrix[y][x].getCurrentSeed() != null)
                                landMatrix[y][x].getCurrentSeed().incrementAgeInDays();
                        }
                    }
                }
            }

            System.out.print("\n\n");

        }while (player.getObjCoin() > 0 || !withered); // ||
    }
}
