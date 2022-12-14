package Entities;
import Constants.FarmerTypeAttributes;

/**
    This class contains the Farmer Type for the game
    When the user decides to change tool, a new object is instantiated with it constructor
    That is why it is not necessary to have setter methods
 */
public class FarmerType
{
    private final String nameType, enumName;
    private final int bonusCoin, seedReductionCost, bonusLimitIncrease, bonusFertilizeIncrease, registrationFee;

    /**
        These value are based on the constant enum values from FarmerTypeAttributes
        @param farmerAttributes  input an enum field from FarmerTypeAttributes
    */
    public FarmerType(FarmerTypeAttributes farmerAttributes)
    {
        this.nameType = farmerAttributes.nameType;
        this.enumName = farmerAttributes.name();
        this.bonusCoin = farmerAttributes.bonusCoin;
        this.seedReductionCost = farmerAttributes.seedReductionCost;
        this.bonusLimitIncrease = farmerAttributes.bonusLimitIncrease;
        this.bonusFertilizeIncrease = farmerAttributes.bonusFertilizeIncrease;
        this.registrationFee = farmerAttributes.registrationFee;
    }

    // Getters for the private fields
    public String getNameType() {
        return nameType;
    }
    public String getEnumName() {
        return enumName;
    }

    public int getBonusCoin() {
        return bonusCoin;
    }
    public int getRegistrationFee() {
        return registrationFee;
    }

    public int getSeedReductionCost() {
        return seedReductionCost;
    }

    public int getBonusWaterLimitIncrease() {
        return bonusLimitIncrease;
    }

    public int getBonusFertilizeIncrease() {
        return bonusFertilizeIncrease;
    }
}
