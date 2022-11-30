package Entities;/*
    This class is the blueprint for the land object in the game.
 */

public class Land
{
    private Seed crop;
    private int amtWater, amtFertilizer;
    private boolean hasRocks, isWithered;

    private int isPlowed;
    public static final int PLOWED = 1;
    public static final int NOT_PLOWED = 0;

    /*
        Initializes all land with 0 water, fertilizer, not plowed, and has no tocks
     */
    public Land()
    {
        this.amtWater = 0;
        this.amtFertilizer = 0;
        this.isPlowed = NOT_PLOWED;
        this.hasRocks = false;
        this.isWithered = false;
    }

    /*
        *plants* the seed into the land via aggregation.
        @param seed  put here the seed object from the player.
     */
    public void setSeed(Seed seed)
    {
        this.crop = seed;
    }

    public void setWithered()
    {
        this.isWithered = true;
    }

    /*
        Get methods
    */
    public Seed getCurrentSeed()
    {
        return this.crop;
    }
    public boolean hasSeed()
    {
        if (this.crop == null)
            return false;

        return true;
    }
    public int getAmtWater()
    {
        return amtWater;
    }
    public int getAmtFertilizer()
    {
        return amtFertilizer;
    }
    public int isPlowed()
    {
        return isPlowed;
    }
    public boolean hasRocks()
    {
        return hasRocks;
    }
    public boolean isWithered()
    {
        return isWithered;
    }

    // Increments on how many times the land has been watered
    public void waterLand()
    {
        this.amtWater++;
    }

    // Increments on how many times the land has been fertilized
    public void fertilizeLand()
    {
        this.amtFertilizer++;
    }

    // Plow land
    public void plowLand()
    {
        this.isPlowed = PLOWED;
    }

    public void removeRocks()
    {
        this.hasRocks = false;
    }

    public void shovelLand()
    {
        if (!this.hasRocks)
            resetValues();
    }

    // resets how many times the land has been watered and fertilized to 0
    public void resetValues()
    {
        this.amtWater = 0;
        this.amtFertilizer = 0;
        this.isPlowed = NOT_PLOWED;
        this.hasRocks = false;
        this.isWithered = false;
        this.crop = null;
    }
}
