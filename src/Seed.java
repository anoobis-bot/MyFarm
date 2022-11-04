/*
    This class contains the equipment for the game
 */

import Constants.CropType;
import Constants.SeedAttributes;

public class Seed
{
    private final String seedName;
    private final CropType cropType;
    private final int hrvstDays, waterNeeds, fertilizerNeeds, producedQtyMin, producedQtyMax, seedCost, baseSellPrice;
    private int ageInDays;
    private final double expYield;
    private boolean withered;

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
        this.fertilizerNeeds = seed.fertilizerNeeds;
        this.producedQtyMin = seed.producedQtyMin;
        this.producedQtyMax = seed.producedQtyMax;
        this.seedCost = seed.seedCost;
        this.baseSellPrice = seed.baseSellPiece;
        this.expYield = seed.expYield;

        this.ageInDays = 0;
        this.withered = false;
    }
    public String getSeedName() {
        return seedName;
    }

    public CropType getCropType() {
        return cropType;
    }

    public int getHrvstDays() {return hrvstDays; }
    public boolean getWitherStat() {return withered; }

    public int getWaterNeeds() {
        return waterNeeds;
    }

    public int getFertilizerNeeds() {
        return fertilizerNeeds;
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
    }

    public int getAgeInDays() {
        return ageInDays;
    }

    public void incrementAgeInDays()
    {
        this.ageInDays++;
    }
}
