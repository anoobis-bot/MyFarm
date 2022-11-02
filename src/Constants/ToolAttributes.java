package Constants;

public enum ToolAttributes
{
    PLOW("Plow", 0, 0.5, false, true),
    WATERING_CAN("Watering Can", 0, 0.5, true, true),
    FERTILIZER("Fertilizer", 10, 4, true, true),
    PICKAXE("Pickaxe", 50, 15, false, false),
    SHOVEL("Shovel", 7, 2, false, false);

    String toolName;
    int costUsage;
    double expUsage;

    boolean requiredRocksClear;
    boolean requiredPlowed;

    ToolAttributes(String toolName, int costUsage, double expUsage, boolean requiredPlowed, boolean requiredRocksClear)
    {
        this.toolName = toolName;
        this.costUsage = costUsage;
        this.expUsage = expUsage;
        this.requiredRocksClear = requiredRocksClear;
        this.requiredPlowed = requiredPlowed;
    }
}
