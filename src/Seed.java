import Constants.CropType;
import Constants.SeedAttributes;

public class Seed
{
    private String seedName;
    private CropType cropType;
    private int hrvstDays;
    private int waterNeeds;
    private int fertilizerNeeds;
    private int producedQtyMin;
    private int producedQtyMax;
    private int seedCost;
    private int baseSellPrice;
    private double expYield;

    private int ageInDays;

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
    }

    public String getSeedName() {
        return seedName;
    }

    public CropType getCropType() {
        return cropType;
    }

    public int getHrvstDays() {
        return hrvstDays;
    }

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
}
