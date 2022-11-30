/*
    This enum contains the different tools in the game. Each field contains the constant values associated with
    the tool
 */

package Constants;

public enum ToolAttributes
{
    // <TOOL_NAME>(toolName, costUsage, expUsage, requiredPlowed, requiredRocksClear
    PLOW("Plow", 0, 0.5, LandPlowed.NOT_REQUIRED, false, false),
    WATERING_CAN("Watering Can", 0, 0.5, LandPlowed.REQUIRED, true, false),
    FERTILIZER("Fertilizer", 10, 4, LandPlowed.REQUIRED, true, false),
    PICKAXE("Pickaxe", 50, 15, LandPlowed.IRRELEVANT, false, true),
    SHOVEL("Shovel", 7, 2, LandPlowed.IRRELEVANT, true, true);

    public final String toolName;
    public final int costUsage;
    public final double expUsage;

    public final int requiredPlowed;
    public final boolean requiredPlant;
    public final boolean requiredRocks;

    ToolAttributes(String toolName, int costUsage, double expUsage,
                   int requiredPlowed, boolean requiredPlant, boolean requiredRocks)
    {
        this.toolName = toolName;
        this.costUsage = costUsage;
        this.expUsage = expUsage;
        this.requiredPlowed = requiredPlowed;
        this.requiredPlant = requiredPlant;
        this.requiredRocks = requiredRocks;
    }

    public char firstLetter()
    {
        return name().charAt(0);
    }

    public static class LandPlowed
    {
        public static final int REQUIRED = 1;
        public static final int NOT_REQUIRED = 0;
        public static final int IRRELEVANT = -1;
    }
}
