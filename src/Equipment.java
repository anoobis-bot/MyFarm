import Constants.ToolAttributes;

public class Equipment
{
    private String toolName;
    private float usageCost;
    private boolean requiredPlowed, requiredRocksClear;

    public void setTool(ToolAttributes tool)
    {
        this.toolName = tool.toolName;
        this.usageCost = tool.costUsage;
        this.requiredPlowed = tool.requiredPlowed;
        this.requiredRocksClear = tool.requiredRocksClear;
    }

    public String getToolName() {
        return toolName;
    }

    public float getUsageCost() {
        return usageCost;
    }

    public boolean isRequiredPlowed() {
        return requiredPlowed;
    }

    public boolean isRequiredRocksClear() {
        return requiredRocksClear;
    }


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
