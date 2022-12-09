/*
    This class contains the game status information
    It has the board size and the current game day
 */

package Entities;

public class GameEnvironment {
    // Dictates what day the game is currently in
    private int systemDay;
    // Size of the land farm (how many tiles are there)
    private final int xSize, ySize;

    /*
        Constructor for the GameEnvironment
        @param ySize  the y size of the board
        @param xSize  the x size of the board
     */
    public GameEnvironment(int ySize, int xSize) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.systemDay = 1;
    }

    /*
        Increments the day and updates the harvest time of the crops of each land tile.
        The time system for harvesting is as follows:
        Harvest time is applicable when the hrvstDays of the crop is equal to 0
        Each crop is given a hrvstDays, which is decremented daily.

        @param landMatrix This variable accepts the set of land that is in the game
     */
    public void advanceTime(Land[][] landMatrix) {
        this.systemDay++;
        // Checks each land object if it has a seed, then increment its age
        for (int y = 0; y < ySize; y++)
        {
            for (int x = 0; x < xSize; x++)
            {
                Seed currSeed_Land = landMatrix[y][x].getCurrentSeed();
                if (currSeed_Land != null)
                {
                    currSeed_Land.decrementHrvstDays();

                    if (currSeed_Land.getHrvstDays() == Seed.HARVEST_TIME)
                    {
                        // If the needs of the plant is not met by the time of harvesting, the plant is to be withered
                        if (landMatrix[y][x].validateWaterFertilizer() == false)
                            landMatrix[y][x].setWithered();
                    }

                    else if (currSeed_Land.getHrvstDays() < Seed.HARVEST_TIME)
                        landMatrix[y][x].setWithered();

                }
            }
        }
    }

    // Get methods
    public int getCurrentDay() {
        return this.systemDay;
    }

    public int getXSize() {
        return xSize;
    }

    public int getYSize() {
        return ySize;
    }
}
