/*
    This class is a blueprint for 1 object. That object is responsible for rendering the game based on
    the player, land, and game environment
 */

import Constants.PlayerActions;
import Constants.SeedAttributes;
import Constants.ToolAttributes;

public class Display
{
    // Aggregation is the type of relationship this class has with player, land, and game environment
    private final Player player;
    private final Land[][] landMatrix;
    private final GameEnvironment game;

    // Since the size of the game will not change, it is better to assign it to this class once
    // instead of assigning it every render() call
    private final int xSize;
    private final int ySize;

    // This class will keep track of the fields of each of these objects
    public Display(Player player, Land[][] landMatrix, GameEnvironment game)
    {
        this.player = player;
        this.landMatrix = landMatrix;
        this.game = game;

        this.xSize = game.getXSize();
        this.ySize = game.getYSize();
    }

    // This function renders all the state of player, land, and game environment in a console
    public void render()
    {
        // Prints current day, player level, and how much objectCoin the player has
        System.out.println("Day " + game.getCurrentDay() + " "
                        + player.getFarmerType().getNameType());
        System.out.println("Level " + player.getFarmerLvl()
                        + " - Exp " + player.getFarmerExp());
        System.out.println("Object Coin: " + player.getObjCoin());
        System.out.print("\n");

        // gets the current pointer position
        int xPointer = player.getPoint().getXCoordinate();
        int yPointer = player.getPoint().getYCoordinate();
        // Prints the land tiles
        for (int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
                // Prints pointer to which land
                if (xPointer == x && yPointer == y)
                    System.out.print(">");
                else
                    System.out.print(" ");

                // Prints the land tile based on its status (e.g. plowed, has rocks, has a particular crop)
                if (landMatrix[y][x].hasRocks())
                    System.out.print("R");
                else if (landMatrix[y][x].isWithered() && landMatrix[y][x].getCurrentSeed() != null)
                    System.out.print("W");
                // Prints the first letter of the crop
                else if (landMatrix[y][x].getCurrentSeed() != null)
                    System.out.print(landMatrix[y][x].getCurrentSeed().getSeedName().charAt(0));
                else if (landMatrix[y][x].isPlowed())
                    System.out.print("P");
                else
                System.out.print("L");  // Land that has nothing. not plowed, no seeds, no rocks.

                System.out.print(" ");
            }
            System.out.print("\n");
        }

        System.out.print("\n");

        // Prints the action options the user can do.
        // currOption keeps track which number is to be printed
        int currOption = 0;
        // Prints the tools
        for (ToolAttributes currTool : ToolAttributes.values())
        {
            // Displays * instead of number if that is currently selected
            System.out.print("[");
            if (currTool.toolName.equals(player.getTool().getToolName()))
                System.out.print("*");
            else
                System.out.print(currOption + 1);
            currOption++;
            System.out.print("]");

            System.out.print(currTool.toolName);
            System.out.print("  ");
        }
        System.out.print("\n");

        // prints seeds
        for (SeedAttributes currSeed : SeedAttributes.values())
        {
            // Displays * instead of number if that is currently selected
            System.out.print("[");
            if (currSeed.seedName.equals(player.getSeed().getSeedName()))
                System.out.print("*");
            else
                System.out.print(currOption + 1);
            currOption++;
            System.out.print("]");

            System.out.print(currSeed.seedName);
            System.out.print("  ");
        }
        System.out.print("\n");

        // Prints different actions
        for (PlayerActions currAction : PlayerActions.values())
        {
            System.out.print("[");
            System.out.print(currOption + 1);
            currOption++;
            System.out.print("]");

            System.out.print(currAction.actionName);
            System.out.print(" ");
        }

        System.out.print("\n----------------------------------------------------------------\n");

    }
}
