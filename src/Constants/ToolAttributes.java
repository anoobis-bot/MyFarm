/*
    This enum contains the different tools in the game. Each field contains the constant values associated with
    the tool
 */

package Constants;

public enum ToolAttributes
{
    // <TOOL_NAME>(toolName, costUsage, expUsage, requiredPlowed, requiredRocksClear
    PLOW("Plow", 0, 0.5,
            false, false, false, false),
    WATERING_CAN("Watering Can", 0, 0.5,
            false, true, true, false),
    FERTILIZER("Fertilizer", 10, 4,
            false, true, true, false),
    PICKAXE("Pickaxe", 50, 15,
            false, false, false, true),
    SHOVEL("Shovel", 7, 2,
            true, true, true, true);

    public final String toolName;
    public final int costUsage;
    public final double expUsage;

    public final boolean noRequirement;
    public final boolean requiredPlowed;
    public final boolean requiredPlant;
    public final boolean requiredRocks;

    ToolAttributes(String toolName, int costUsage, double expUsage,
                   boolean noRequirement, boolean requiredPlowed, boolean requiredPlant, boolean requiredRocks)
    {
        this.toolName = toolName;
        this.costUsage = costUsage;
        this.expUsage = expUsage;

        this.noRequirement = noRequirement;
        this.requiredPlowed = requiredPlowed;
        this.requiredPlant = requiredPlant;
        this.requiredRocks = requiredRocks;
    }

    public char firstLetter()
    {
        return name().charAt(0);
    }
}
