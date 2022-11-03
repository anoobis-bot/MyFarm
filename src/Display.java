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
        System.out.println(this.player.getFarmerType().getNameType());
        System.out.print("\n");

        int xSize = game.getXSize();
        int ySize = game.getYSize();
        for (int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
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
    }
}
