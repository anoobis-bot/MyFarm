
public class Land
{
    private Seed crop;
    private int amtWater, amtFertilizer, dayPlanted;
    private boolean isPlowed, hasRocks, isWithered;

    Land()
    {
        this.amtWater = 0;
        this.amtFertilizer = 0;
        this.isPlowed = false;
        this.hasRocks = false;
        this.isWithered = false;
    }

    public void setSeed(Seed seed)
    {
        this.crop = seed;
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

    public boolean isWithered()
    {
        if(crop.getWitherStat())
            return isWithered = true;
        else
            return isWithered;
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
