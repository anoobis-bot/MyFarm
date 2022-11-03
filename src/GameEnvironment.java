public class GameEnvironment
{
    private int systemDay;
    private final int xSize, ySize;

    GameEnvironment(int ySize, int xSize)
    {
        this.xSize = xSize;
        this.ySize = ySize;
        this.systemDay = 1;
    }

    public int advanceTime()
    {
        this.systemDay++;

        return this.systemDay;
    }

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
