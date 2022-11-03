import Constants.ToolAttributes;

public class Equipment
{
    private String toolName;
    private float usageCost;

    private boolean requiredPlowed;
    private boolean requiredRocksClear;

    public boolean verifyUsage_Mny(float objCoin)
    {
        if (objCoin >= this.usageCost)
            return true;
        else
            return false;
    }

    public boolean verifyUsage_Lnd(boolean hasRocks, boolean isPlowed)
    {
        if (this.requiredRocksClear && hasRocks)
            return false;
        else if (this.requiredPlowed && !(isPlowed))
            return false;

        return true;
    }
}
