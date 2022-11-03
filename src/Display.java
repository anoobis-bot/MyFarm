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
        System.out.println("Day " + game.getCurrentDay());

        System.out.print(player.getFarmerType().getNameType());
        System.out.print(" - Level " + player.getFarmerLvl());
        System.out.print("\n\n");

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
            if (currSeed.seedName.equals(playerToolName))
                System.out.print("~");
            else
                System.out.print(" ");

            System.out.print(currSeed.seedName);
            System.out.print(" ");
        }

    }
}
