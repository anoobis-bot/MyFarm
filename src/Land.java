import Constants.SeedAttributes;

public class Land
{
    private Seed crop;
    private int amtWater, amtFertilizer;
    private boolean isPlowed, hasRocks;

    Land()
    {
        this.amtWater = 0;
        this.amtFertilizer = 0;
        this.isPlowed = false;
        this.hasRocks = false;
    }

    public void setSeed(SeedAttributes seed)
    {
        this.crop = new Seed(seed);
    }

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

    public void waterLand()
    {
        this.amtWater++;
    }

    public void fertilizeLand()
    {
        this.amtFertilizer++;
    }

    public void resetValues()
    {
        this.amtWater = 0;
        this.amtFertilizer = 0;
    }
}
