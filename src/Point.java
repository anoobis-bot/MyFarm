/*
    This class is the pointer to which it points which element land the player is pointing to
 */

public class Point
{
    private final int xCoordinate, yCoordinate;

    public Point()
    {
        xCoordinate = 0;
        yCoordinate = 0;
    }

    /*
        Get methods
    */
    public int getXCoordinate()
    {
        return xCoordinate;
    }
    public int getYCoordinate()
    {
        return yCoordinate;
    }

    /*
        MCO2 use

    public void moveXCoordinate(int xCoordinate)
    {
        this.xCoordinate = xCoordinate;
    }
    public void moveYCoordinate(int yCoordinate)
    {
        this.yCoordinate = yCoordinate;
    }*/
}
