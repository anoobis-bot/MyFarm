package Entities;/*
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


    public void setXCoordinate(int xCoordinate)
    {
        this.xCoordinate = xCoordinate;
    }
    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}
