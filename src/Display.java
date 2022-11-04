import Constants.SeedAttributes;
import Constants.ToolAttributes;

import javax.tools.Tool;

public class Display
{
    private Player player;
    private Land[][] landMatrix;
    private GameEnvironment game;

    public Display(Player player, Land[][] landMatrix, GameEnvironment game)
    {
        this.player = player;
        this.landMatrix = landMatrix;
        this.game = game;
    }

    public void render()
    {
        System.out.println("Day " + game.getCurrentDay() + " "
                        + player.getFarmerType().getNameType());
        System.out.println("Level " + player.getFarmerLvl()
                        + " - Exp " + player.getFarmerExp());
        System.out.println("Object Coin: " + player.getObjCoin());
        System.out.print("\n");

        int xSize = game.getXSize();
        int ySize = game.getYSize();
        int xPointer = player.getPoint().getXCoordinate();
        int yPointer = player.getPoint().getYCoordinate();
        for (int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
                if (xPointer == x && yPointer == y)
                    System.out.print(">");
                else
                    System.out.print(" ");

                if (landMatrix[y][x].hasRocks())
                    System.out.print("R");
                else if (landMatrix[y][x].isPlowed())
                    System.out.print("P");
               else if (landMatrix[y][x].isWithered())
                    System.out.print("W");
                else
                System.out.print("L");

                System.out.print(" ");
            }
            System.out.print("\n");
        }

        System.out.print("\n");

        String playerToolName = player.getTool().getToolName();
        for (ToolAttributes currTool : ToolAttributes.values())
        {
            if (currTool.toolName.equals(playerToolName))
                System.out.print("~");
            else
                System.out.print(" ");


            System.out.print(currTool.toolName);
            System.out.print(" ");
        }

        System.out.print("\n");

        String selectedSeed = player.getTool().getToolName();
        for (SeedAttributes currSeed : SeedAttributes.values())
        {
            int i = 1;
            if (currSeed.seedName.equals(playerToolName))
                System.out.print("~");
            else
                System.out.print(" ");

            System.out.print("[" + i + "}");
            System.out.print(currSeed.seedName);
            System.out.print(" ");
        }

        System.out.print("\n----------------------------------------------------------------\n");

    }
}
