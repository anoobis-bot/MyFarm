/*
    This enum contains the different tools in the game. Each field contains the constant values associated with
    the tool
 */

package Constants;

public enum ToolAttributes
{
    // <TOOL_NAME>(toolName, costUsage, expUsage, requiredPlowed, requiredRocksClear
    PLOW("Plow", 0, 0.5, false, true),
    WATERING_CAN("Watering Can", 0, 0.5, true, true),
    FERTILIZER("Fertilizer", 10, 4, true, true),
    PICKAXE("Pickaxe", 50, 15, false, false),
    SHOVEL("Shovel", 7, 2, false, false);

    public final String toolName;
    public final int costUsage;
    public final double expUsage;

    public final boolean requiredRocksClear;
    public final boolean requiredPlowed;

    ToolAttributes(String toolName, int costUsage, double expUsage, boolean requiredPlowed, boolean requiredRocksClear)
    {
        this.toolName = toolName;
        this.costUsage = costUsage;
        this.expUsage = expUsage;
        this.requiredRocksClear = requiredRocksClear;
        this.requiredPlowed = requiredPlowed;
    }

    public char firstLetter()
    {
        return name().charAt(0);
    }
}
