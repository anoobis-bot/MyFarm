package Entities;/*
    This class is the blueprint for the land object in the game.
 */

public class Land
{
    private Seed crop;
    private int amtWater, amtFertilizer;
    private boolean isPlowed, hasRocks, isWithered;

    /*
        Initializes all land with 0 water, fertilizer, not plowed, and has no tocks
     */
    public Land()
    {
        this.amtWater = 0;
        this.amtFertilizer = 0;
        this.isPlowed = false;
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
    public int getAmtWater()
    {
        return amtWater;
    }
    public int getAmtFertilizer()
    {
        return amtFertilizer;
    }
    public boolean isPlowed()
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
        this.isPlowed = true;
    }

    // resets how many times the land has been watered and fertilized to 0
    public void resetValues()
    {
        this.crop.deleteSeed();
        this.amtWater = 0;
        this.amtFertilizer = 0;
        this.isPlowed = false;
        this.hasRocks = false;
        this.isWithered = false;
        this.crop = null;
    }
}
