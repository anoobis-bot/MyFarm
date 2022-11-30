package Entities;/*
    This class contains the equipment for the game
 */
import Constants.ToolAttributes;

public class Equipment
{
    private String toolName;
    private float usageCost;
    private double expGain;
    private boolean noRequirement;
    private boolean requiredPlowed;
    private boolean requiredPlant;
    private boolean requiredRocks;

    /*
        These value are based on the constant enum values from ToolAttributes
        @param tool  input an enum field from ToolAttributes
    */
    public void setTool(ToolAttributes tool)
    {
        this.toolName = tool.toolName;
        this.usageCost = tool.costUsage;
        this.expGain = tool.expUsage;
        this.noRequirement = tool.noRequirement;
        this.requiredPlowed = tool.requiredPlowed;
        this.requiredPlant = tool.requiredPlant;
        this.requiredRocks = tool.requiredRocks;
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

    public boolean requiredPlowed() {
        return requiredPlowed;
    }

    public boolean requiredRocks() {
        return requiredRocks;
    }

    // these methods below are the ones to be used if the tool is usable
    public boolean verifyUsage_Mny(double objCoin)
    {
        return objCoin >= this.usageCost;
    }
    public boolean verifyUsage_Lnd(boolean isPlowed, boolean hasPlant, boolean hasRocks)
    {
        // Guard clauses
        if (this.noRequirement)
        {
            return true;
        }

        if (this.requiredPlowed != isPlowed)
        {
            return false;
        }
        else if (this.requiredPlant != hasPlant)
        {
            return false;
        }
        else if (this.requiredRocks != hasRocks)
        {
            return false;
        }


        return true;
    }
}
