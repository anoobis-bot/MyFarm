/*
    This enum class contains the different farmer types in the game.
    The fields contain the constant values (benefits) each farmer type has
 */

package Constants;

public enum FarmerTypeAttributes
{
    FARMER("Farmer", 0, 0, 0,
            0, 0, 0),
    REGISTERED_FARMER("Registered Farmer", 5, 1, -1,
            0, 0, 200),
    DISTINGUISHED_FARMER("Distinguished Farmer", 10, 2, -2,
            1, 0, 300),
    LEGENDARY_FARMER("Legendary Farmer", 15, 4, -3,
            2, 1, 400);

    public final String nameType;
    public final int levelRequirement;
    public final int bonusCoin;
    public final int seedReductionCost;
    public final int bonusLimitIncrease;
    public final int bonusFertilizeIncrease;
    public final int registrationFee;

    FarmerTypeAttributes(String nameType, int levelRequirement, int bonusCoin, int seedReductionCost,
                         int bonusLimitIncrease, int bonusFertilizeIncrease, int registrationFee)
    {
        this.nameType = nameType;
        this.levelRequirement = levelRequirement;
        this.bonusCoin = bonusCoin;
        this.seedReductionCost = seedReductionCost;
        this.bonusLimitIncrease = bonusLimitIncrease;
        this.bonusFertilizeIncrease = bonusFertilizeIncrease;
        this.registrationFee = registrationFee;
    }
}
