/*
    This class contains the equipment for the game
 */
import Constants.ToolAttributes;

public class Equipment
{
    private String toolName;
    private float usageCost;
    private double expGain;
    private boolean requiredPlowed, requiredRocksClear;

    /*
        These value are based on the constant enum values from ToolAttributes
        @param tool  input an enum field from ToolAttributes
    */
    public void setTool(ToolAttributes tool)
    {
        this.toolName = tool.toolName;
        this.usageCost = tool.costUsage;
        this.expGain = tool.expUsage;
        this.requiredPlowed = tool.requiredPlowed;
        this.requiredRocksClear = tool.requiredRocksClear;
    }

    // getters
    public String getToolName() {
        return toolName;
    }

    public float getUsageCost() {
        return usageCost;
    }

    public double getExpGain()
    {
        return expGain;
    }

    public boolean isRequiredPlowed() {
        return requiredPlowed;
    }

    public boolean isRequiredRocksClear() {
        return requiredRocksClear;
    }

    // these methods below are the ones to be used if the tool is usable
    public boolean verifyUsage_Mny(float objCoin)
    {
        return objCoin >= this.usageCost;
    }

    public boolean verifyUsage_Lnd(boolean hasRocks, boolean isPlowed)
    {
        if (this.requiredRocksClear && hasRocks)
            return false;
        else return !this.requiredPlowed || isPlowed;
    }
}
