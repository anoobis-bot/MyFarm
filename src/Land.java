import Constants.SeedAttributes;

public class Land
{
    private Seed crop;
    private int amtWater, amtFertilizer;

    Land()
    {
        this.amtWater = 0;
        this.amtFertilizer = 0;
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
