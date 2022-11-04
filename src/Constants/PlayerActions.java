/*
    This enum contains the action the user may take during the game.
    It is in enum since it will be useful when printing the options and
    assigning it with an associated integer.
 */

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
