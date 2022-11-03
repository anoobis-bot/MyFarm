public class GameEnvironment
{
    private int systemDay;
    private final int xSize;
    private final int ySize;

    GameEnvironment(int xSize, int ySize)
    {
        this.xSize = xSize;
        this.ySize = ySize;
        this.systemDay = 0;
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
