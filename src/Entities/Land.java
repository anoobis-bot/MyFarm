package Entities;
import java.util.Random;
/*
    This class is the blueprint for the land object in the game.
 */

public class Land
{
    private Seed crop;
    private int amtWater, amtFertilizer;
    private boolean hasRocks, isWithered;

    private boolean isPlowed;

    /*
        Initializes all land with 0 water, fertilizer, not plowed, and has no rocks
     */
    public Land()
    {
        this.amtWater = 0;
        this.amtFertilizer = 0;
        this.isPlowed = false;
        this.isWithered = false;

        //random distribution of rocks
        Random random = new Random();
        this.hasRocks = random.nextBoolean();
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

    // returns current crop's Water need (Reason Purposes)
    public int cropWaterNeeds()
    {
        return crop.getWaterNeeds();
    }
    // returns current crop's Fertilizer need (Reason Purposes)
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

    // Increments on how many times the land has been watered
    public void waterLand(int bonusFromFarmer)
    {
        if (this.amtWater < crop.getWaterBonus() + bonusFromFarmer)
            this.amtWater++;
    }

    // Increments on how many times the land has been fertilized
    public void fertilizeLand(int bonusFromFarmer)
    {
        if (this.amtFertilizer < crop.getFertilizerBonus() + bonusFromFarmer)
            this.amtFertilizer++;
    }

    // Plow land
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

    // resets how many times the land has been watered and fertilized to 0
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
