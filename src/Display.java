import Constants.PlayerActions;
import Constants.SeedAttributes;
import Constants.ToolAttributes;

public class Display
{
    private final Player player;
    private final Land[][] landMatrix;
    private final GameEnvironment game;

    Display(Player player, Land[][] landMatrix, GameEnvironment game)
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
                else if (landMatrix[y][x].getCurrentSeed() != null)
                    System.out.print(landMatrix[y][x].getCurrentSeed().getSeedName().charAt(0));
                else if (landMatrix[y][x].isPlowed())
                    System.out.print("P");
//               else if (landMatrix[y][x].isWithered())
//                    System.out.print("W");
                else
                System.out.print("L");

                System.out.print(" ");
            }
            System.out.print("\n");
        }

        System.out.print("\n");

        int currOption = 0;
        for (ToolAttributes currTool : ToolAttributes.values())
        {
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

        for (SeedAttributes currSeed : SeedAttributes.values())
        {
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
        System.out.print("Enter an input: ");

    }
}
