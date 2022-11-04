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
    public int advanceTime()
    {
        this.systemDay++;

        return this.systemDay;
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
