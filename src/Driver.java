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
        /* Initializing the input, player, game, and land, and display objects */
        Scanner input = new Scanner(System.in);
        int userInput;
        boolean close = false;

        GameEnvironment game = new GameEnvironment(1, 1);
        Player player = new Player(100, 0, 0);
        Land[][] landMatrix = new Land[game.getYSize()][game.getXSize()];

        Display display = new Display(player, landMatrix, game);

        // To lessen cpu usage, these value are already assigned. This will be used when asking for input
        int toolPopulation = ToolAttributes.values().length;
        int seedPopulation = SeedAttributes.values().length;
        int actionsPopulation = PlayerActions.values().length;

        int xSize = game.getXSize();
        int ySize = game.getYSize();

        // initializing land object in each element of landMatrix[][]
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                landMatrix[y][x] = new Land();
            }
        }

        /* Start of game loop until game over*/
        do
        {
            do {
                display.render();

                // getting user input
                userInput = input.nextInt();
                // Catch user error, negative numbers & greater than options are not in range of possible inputs
                if (userInput < 0 || userInput > 18)
                    System.out.println("Incorrect Input");
                // Option to close the program
                if (userInput == 0) {
                    System.out.println("Program closed");
                    break;
                }
                // if the user's input are the number range for tools
                else if (userInput <= toolPopulation) {
                    player.changeTool(ToolAttributes.values()[userInput - 1]);
                }
                // if the user's input are the number range for seeds
                else if (userInput <= toolPopulation + seedPopulation) {
                    player.grabSeed(SeedAttributes.values()[userInput - toolPopulation - 1]);
                }
                // if the user's input are the number range for player actions
                else if (userInput <= toolPopulation + seedPopulation + actionsPopulation) {
                    // if the user decided to plant seed
                    if (userInput == 14)
                        player.plantSeed(landMatrix);
                        // if the user decided to use tool
                    else if (userInput == 15)
                        player.useTool(landMatrix);
                        // if the user decided to harvest
                    else if (userInput == 16)
                        // TODO code for harvesting
                        ;
                        // if the user decided to proceed to next day
                    else if (userInput == 17) {
                        game.advanceTime(landMatrix);

                        int wthrdCnt = 0; //count for withered crops

                        //checks all tiles that is withered
                        for (int y = 0; y < ySize; y++) {
                            for (int x = 0; x < xSize; x++) {
                                if (!(landMatrix[y][x].getAmtWater() >= landMatrix[y][x].getReqWater())) {
                                    wthrdCnt++;
                                    landMatrix[y][x].isWithered();
                                }
                            }
                        }

                        if (wthrdCnt >= 1) // MCO1 has 1 tile only
                        {
                            display.render();
                            System.out.println("Every Crop has died");
                            break;
                        }
                    }
                    // if the user decided to upgrade status
                    else if (userInput == 18) {

                    }
                }
                System.out.print("\n\n");
            }while (player.getObjCoin() > 0);
            System.out.println("Game Over!");

            /* This asks the player for restart option or quit game */
            System.out.println("restart game? [y/n]");
            Scanner s = new Scanner(System.in);
            switch (s.next().charAt(0))
            {
                case 'Y':
                case 'y':
                    for (int y = 0; y < ySize; y++)
                        for (int x = 0; x < xSize; x++)
                            landMatrix[y][x].resetValues();
                    break;
                case 'N':
                case 'n':
                    close = true;
                    break;
                default:
                    System.out.println("wrong input");
            }
        }while (close != true);
    }
}
