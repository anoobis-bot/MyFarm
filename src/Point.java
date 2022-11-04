/*
    This class is the pointer to which it points which element land the player is pointing to
 */

public class Point
{
    private int xCoordinate, yCoordinate;

    public Point()
    {
        xCoordinate = 0;
        yCoordinate = 0;
    }

    // Getters and setters
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
