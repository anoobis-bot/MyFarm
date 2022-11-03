import Constants.ToolAttributes;

public class Equipment
{
    private String toolName;
    private float usageCost;

    private boolean requiredPlowed;
    private boolean requiredNoRocks;

    public boolean verifyUsage_Mny(float objCoin)
    {
        if (objCoin >= this.usageCost)
            return true;
        else
            return false;
    }

    public boolean verifyUsage_Lnd(boolean hasRocks, boolean isPlowed)
    {
        return true;
    }
}
