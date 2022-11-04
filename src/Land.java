/*
    This class is the blueprint for the land object in the game.
 */

public class Land
{
    private Seed crop;
    private int amtWater, amtFertilizer, dayPlanted;
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

    // Getters
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
        if(crop.getWitherStat())
            return isWithered = true;
        else
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

    // resets how many times the land has been watered and fertilized to 0
    public void resetValues()
    {
        this.amtWater = 0;
        this.amtFertilizer = 0;
    }
}
