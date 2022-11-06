/*
    This class contains the game status information
    It has the board size and the current game day
 */

public class GameEnvironment
{
    private int systemDay;
    private final int xSize, ySize;

    /*
        @param ySize  the y size of the board
        @param xSize  the x size of the board
     */
    public GameEnvironment(int ySize, int xSize)
    {
        this.xSize = xSize;
        this.ySize = ySize;
        this.systemDay = 1;
    }

    /*
        Increments systemDay
        @return returns int systemDay
     */
    public void advanceTime(Land[][] landMatrix)
    {
        this.systemDay++;
        // Checks each land object if it has a seed, then increment its age
        for (int y = 0; y < ySize; y++)
            for (int x = 0; x < xSize; x++)
                if (landMatrix[y][x].getCurrentSeed() != null)
                    landMatrix[y][x].getCurrentSeed().incrementAgeInDays();
    }

    // Getters
    public int getCurrentDay()
    {
        return this.systemDay;
    }

    public int getXSize()
    {
        return xSize;
    }

    public int getYSize()
    {
        return ySize;
    }
}
