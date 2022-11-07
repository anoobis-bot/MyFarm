/*
    This class contains the equipment for the game
 */

import Constants.CropType;
import Constants.SeedAttributes;

public class Seed
{
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
    public int getAgeInDays() {
        return ageInDays;
    }

    // Increments seed age
    public void incrementAgeInDays()
    {
        this.ageInDays++;
    }

    // these methods below are the ones to be used if the seed is plant-able
    public boolean verifyUsage_Mny(float objCoin)
    {
        return objCoin >= this.seedCost;
    }
    public boolean verifyUsage_Lnd(boolean hasRocks, boolean isPlowed)
    {
        return !hasRocks && isPlowed;
    }

    // a method that deletes the seed in this land
    public void deleteSeed()
    {
        this.ageInDays = 0;
    }
}
