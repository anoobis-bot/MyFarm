/*
    This class contains the equipment for the game
 */

package Entities;

import Constants.CropType;
import Constants.SeedAttributes;

import java.util.concurrent.ThreadLocalRandom;

public class Seed
{
    private final String enumName;
    private final String seedName;
    private final CropType cropType;
    private int hrvstDays;
    // The hrvstDays decrement requirement to be able to harvest
    public static final int HARVEST_TIME = 0;
    private final int waterNeeds, waterBonus, fertilizerNeeds, fertilizerBonus,
            producedQty, seedCost, baseSellPrice;
    private final double expYield;

    /*
        When the user decides to change tool, a new object is instantiated with it constructor
        That is why it is not necessary to have setter methods
        @param seed input an enum field from SeedAttributes
     */
    public Seed(SeedAttributes seed)
    {
        this.enumName = seed.name();

        this.seedName = seed.seedName;
        this.cropType = seed.cropType;
        this.hrvstDays = seed.hrvstDays;
        this.waterNeeds = seed.waterNeeds;
        this.waterBonus = seed.waterBonus;
        this.fertilizerNeeds = seed.fertilizerNeeds;
        this.fertilizerBonus = seed.fertilizerBonus;
        this.seedCost = seed.seedCost;
        this.baseSellPrice = seed.baseSellPiece;
        this.expYield = seed.expYield;

        int producedQtyMin = seed.producedQtyMin;
        int producedQtyMax = seed.producedQtyMax;
        // Random value from Min to Max
        this.producedQty = ThreadLocalRandom.current().nextInt(producedQtyMin, producedQtyMax + 1);
    }

    /* get methods */

    public String getEnumName()
    {
        return enumName;
    }
    public String getSeedName() {
        return seedName;
    }
    public CropType getCropType() {
        return cropType;
    }
    public int getHrvstDays() {return hrvstDays; }
    public int getWaterNeeds() {
        return waterNeeds;
    }
    public int getWaterBonus() {
        return waterBonus;
    }
    public int getFertilizerNeeds() {
        return fertilizerNeeds;
    }
    public int getFertilizerBonus()
    {
        return fertilizerBonus;
    }
    public int getProducedQty() {
        return producedQty;
    }
    public int getSeedCost() {
        return seedCost;
    }
    public int getBaseSellPrice() {
        return baseSellPrice;
    }
    public double getExpYield() {
        return expYield;
    } //MCO2 use
    public int getCropPadding()
    {
        return this.cropType.paddingRequired;
    }

    /*
     * The system for harvesting is decrement. A plant has a specific number of maturity. If reaches the next day
     * The time it gets to reach to its harvest point is decremented until it reaches 0 (harvest time)
     */
    public void decrementHrvstDays()
    {
        this.hrvstDays--;
    }

    // these methods below are the ones to be used if the seed is plant-able
    public boolean verifyUsage_Mny(double objCoin)
    {
        return objCoin >= this.seedCost;
    }
    public boolean verifyUsage_Lnd(Land[][] landMatrix, GameEnvironment game, int yPointer, int xPointer)
    {
        // Shorten syntax
        Land currLand = landMatrix[yPointer][xPointer];

        if (currLand.isWithered())
        {
            return false;
        }
        // not plowed
        else if (!currLand.isPlowed())
        {
            return false;
        }
        else if (currLand.hasRocks())
        {
            return false;
        }
        else if (currLand.hasSeed())
        {
            return false;
        }
        // checks padding for fruit tree requirement
        else if (this.cropType.equals(CropType.FRUIT_TREE))
        {
            // CORNERS CHECKING
            // x component
            if (xPointer - this.getCropPadding() < 0  ||  xPointer + this.getCropPadding() >= game.getXSize())
            {
                return false;
            }
            // y component
            else if (yPointer - this.getCropPadding() < 0  ||  yPointer + this.getCropPadding() >= game.getYSize())
            {
                return false;
            }

            // CHECKING IF THERE ARE ANY ADJACENT SEEDS
            // Checks each adjacent box surrounding the [yPoint]pxPoint]
            int currYSeeker = - this.getCropPadding();
            int currXSeeker = - this.getCropPadding();
            int maxYSeek = this.getCropPadding();
            int maxXSeek = this.getCropPadding();
            while (currYSeeker <= maxYSeek)
            {
                while (currXSeeker <= maxXSeek)
                {
                    if (landMatrix[yPointer + currYSeeker][xPointer + currXSeeker].hasSeed())
                    {
                        return false;
                    }

                    currXSeeker++;
                }

                currYSeeker++;
            }
        }

        // if all validations passed, return true
        return true;

    }
}
