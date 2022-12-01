package Entities;
/*
    This class contains the equipment for the game
 */

import Constants.CropType;
import Constants.SeedAttributes;

public class Seed
{
    private final String enumName;
    private final String seedName;
    private final CropType cropType;
    private final int hrvstDays, waterNeeds, waterBonus, fertilizerNeeds, fertilizerBonus,
            producedQtyMin, producedQtyMax, seedCost, baseSellPrice;
    private int ageInDays;
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
        this.producedQtyMin = seed.producedQtyMin;
        this.producedQtyMax = seed.producedQtyMax;
        this.seedCost = seed.seedCost;
        this.baseSellPrice = seed.baseSellPiece;
        this.expYield = seed.expYield;

        this.ageInDays = 0;
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
    } //MCO2 use
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
    public int getProducedQtyMin() {
        return producedQtyMin;
    }
    public int getProducedQtyMax() {
        return producedQtyMax;
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
    public int getAgeInDays() {
        return ageInDays;
    }

    // Increments seed age
    public void incrementAgeInDays()
    {
        this.ageInDays++;
    }

    // these methods below are the ones to be used if the seed is plant-able
    public boolean verifyUsage_Mny(double objCoin)
    {
        return objCoin >= this.seedCost;
    }
    public boolean verifyUsage_Lnd(Land[][] landMatrix, GameEnvironment game, int yPointer, int xPointer)
    {
        Land currLand = landMatrix[yPointer][xPointer];

        // not plowed
        if (!currLand.isPlowed())
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
        // checks padding
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
