public class Point
{
    private int xCoordinate, yCoordinate;

    Point()
    {
        xCoordinate = 0;
        yCoordinate = 0;
    }

    public int getXCoordinate()
    {
        return xCoordinate;
    }

    public void moveXCoordinate(int xCoordinate)
    {
        this.xCoordinate = xCoordinate;
    }

    public int getYCoordinate()
    {
        return yCoordinate;
    }

    public void moveYCoordinate(int yCoordinate)
    {
        this.yCoordinate = yCoordinate;
    }
}
