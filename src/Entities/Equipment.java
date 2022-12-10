/*
    This class contains the equipment for the game
 */

package Entities;
import Constants.ToolAttributes;

public class Equipment
{
    private String enumName;
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
        this.enumName = tool.name();

        this.toolName = tool.toolName;
        this.usageCost = tool.costUsage;
        this.expGain = tool.expUsage;
        this.noRequirement = tool.noRequirement;
        this.requiredPlowed = tool.requiredPlowed;
        this.requiredPlant = tool.requiredPlant;
        this.requiredRocks = tool.requiredRocks;
    }

    // Getters being used in the game
    public String getEnumName()
    {
        return enumName;
    }
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


    /*
     * This method verifies whether the player has enough coins to use a particular tool
     * @param objCoin The amount of coin that the player has
     */
    public boolean verifyUsage_Mny(double objCoin)
    {
        return objCoin >= this.usageCost;
    }
    /*
     * This method verifies whether the player clan plant a seed on a specific land based on the seed's land requirements
     * @param isPlowed: isPlowed field of the land tile
     * @param hasPlant: hasPlant field of the land tile
     * @param hasRocks: hasRocks field of the land tile
     * @param isWithered: isWithered field of the land tile
     */
    public boolean verifyUsage_Lnd(boolean isPlowed, boolean hasPlant, boolean hasRocks, boolean isWithered)
    {
        // Guard clauses
        // Immediately returns true if there are no requirements for using the tool (shovel)
        if (this.noRequirement)
        {
            return true;
        }

        // If the land is withered, no other tools can be used
        if (isWithered)
        {
            return false;
        }
        // These checks if the land condition matches the conditional use of the current tool
        else if (this.requiredPlowed != isPlowed)
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

        // Returns true if it passes all verification
        return true;
    }
}
