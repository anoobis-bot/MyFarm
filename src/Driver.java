import Constants.ToolAttributes;

import java.util.Scanner;

public class Driver
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        GameEnvironment game = new GameEnvironment(1, 1);
        Player player = new Player(5, 0, 0);
        Land[][] landMatrix = new Land[game.getYSize()][game.getXSize()];

        Display display = new Display(player, landMatrix, game);

        int xSize = game.getXSize();
        int ySize = game.getYSize();

        boolean withered = false;

        do
        {
            for (int y = 0; y < ySize; y++)
                for (int x = 0; x < xSize; x++) {
                    landMatrix[y][x] = new Land();
                    if (landMatrix[y][x].isWithered())
                        withered = true;
                }
            display.render();
            game.advanceTime();

            player.subtractCoin(); // loopbreaker lang(pansamantala)

        }while (player.getObjCoin() > 0 || !withered); // ||
    }
}
