import Constants.ToolAttributes;

public class Driver
{
    public static void main(String[] args)
    {
        GameEnvironment game = new GameEnvironment(2, 3);
        Player player = new Player(1000, 0, 0);
        Land[][] landMatrix = new Land[game.getYSize()][game.getXSize()];

        Display display = new Display(player, landMatrix, game);

        int xSize = game.getXSize();
        int ySize = game.getYSize();
        for (int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
                landMatrix[y][x] = new Land();
            }
        }

        display.render();
    }
}
