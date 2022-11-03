public class Driver
{
    public static void main(String[] args)
    {
        GameEnvironment game = new GameEnvironment(2, 3);
        Player player = new Player(1000, 0, 0);
        Land[][] landFarm = new Land[game.getYSize()][game.getXSize()];

        Display display = new Display(player, landFarm, game);

        for (int y = 0; y < game.getXSize(); y++)
        {
            for (int x = 0; x < game.getYSize(); x++)
            {
                landFarm[y][x] = new Land();
            }
        }

        display.render();
    }
}
