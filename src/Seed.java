import Constants.CropType;
import Constants.SeedAttributes;

public class Seed
{
    private String seedName;
    private CropType cropType;
    private int hrvstDays, waterNeeds, fertilizerNeeds, producedQtyMin, producedQtyMax, seedCost, baseSellPrice, ageInDays;
    private double expYield;
    private boolean withered;
    Seed(SeedAttributes seed)
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
