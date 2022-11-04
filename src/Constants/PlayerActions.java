package Constants;

public enum PlayerActions
{
    PLANT("Plant"),
    USE_TOOL("Use Tool"),
    NEXT_DAY("Next Day"),
    UPGRADE_STATUS("Upgrade Status");

    public final String actionName;

    PlayerActions(String actionName)
    {
        this.actionName = actionName;
    }
}
