package Entities;

/**
    This class is the blueprint for the land object in the game.
 */
public class Land
{
    /**
     * The land has an aggregation relationship with the crop
     * Its fields describe what is the attribute of the land tile: how much water, fertilizer it has
     * and if it has rocks or a withered crop, or the land is plowed
     */
    private Seed crop;
    private int amtWater, amtFertilizer;
    private boolean hasRocks, isWithered;

    private boolean isPlowed;

    /**
        Initializes all land with 0 water, fertilizer, not plowed, and has no rocks
     */
    public Land()
    {
        this.amtWater = 0;
        this.amtFertilizer = 0;
        this.isPlowed = false;
        this.isWithered = false;
    }

    /**
        Plants the seed into the land via aggregation.
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

    /**
     * Checks if the land has a crop/seed in it
     *
     * @return boolean true if the land is populated with a seed
     */
    public boolean hasSeed()
    {
        return (this.crop != null);
    }
    /**
     *  Validates if the land has the right condition for the crop's water and fertilizer needs
     */
    public boolean validateWaterFertilizer()
    {
        if (this.amtWater < this.crop.getWaterNeeds())
        {
            return false;
        }
        else if (this.amtFertilizer < this.crop.getFertilizerNeeds())
        {
            return false;
        }

        return true;
    }

    // GET METHODS
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

    /**
     * @return current crop's Water need (Reason Purposes)
     */
    public int cropWaterNeeds()
    {
        return crop.getWaterNeeds();
    }

    /**
     *
     * @return current crop's Fertilizer need (Reason Purposes)
     */
    public int cropFertilizerNeeds()
    {
        return crop.getFertilizerNeeds();
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

    /**
     * Sets rocks on load
     * @param value true or false if the land should have rocks
     */
    public void setHasRocks(boolean value)
    {
        this.hasRocks = value;
    }


    /*
     * The codes below changes the attributes of the land
     */

    /**
     * Increments on how many times the land has been watered
     * @param bonusFromFarmer the bonusWaterLimit of the player's farmer type
     */
    public void waterLand(int bonusFromFarmer)
    {
        if (this.amtWater < crop.getWaterBonus() + bonusFromFarmer)
            this.amtWater++;
    }

    /**
     * Increments on how many times the land has been fertilized
     * @param bonusFromFarmer bonusFromFarmer of the player's farmer type
     */
    public void fertilizeLand(int bonusFromFarmer)
    {
        if (this.amtFertilizer < crop.getFertilizerBonus() + bonusFromFarmer)
            this.amtFertilizer++;
    }

    /**
     * Plows land
     */
    public void plowLand()
    {
        this.isPlowed = true;
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

    /**
     * resets how many times the land has been watered and fertilized to 0
     */
    public void resetValues()
    {
        this.amtWater = 0;
        this.amtFertilizer = 0;
        this.isPlowed = false;
        this.hasRocks = false;
        this.isWithered = false;
        this.crop = null;
    }
}
