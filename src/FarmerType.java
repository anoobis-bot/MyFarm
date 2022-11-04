import Constants.FarmerTypeAttributes;

public class FarmerType
{
    private String nameType;
    private int bonusCoin, seedReductionCost, bonusLimitIncrease, bonusFertilizeIncrease;

    FarmerType(FarmerTypeAttributes farmerAttributes)
    {
        this.nameType = farmerAttributes.nameType;
        this.bonusCoin = farmerAttributes.bonusCoin;
        this.seedReductionCost = farmerAttributes.seedReductionCost;
        this.bonusLimitIncrease = farmerAttributes.bonusLimitIncrease;
        this.bonusFertilizeIncrease = farmerAttributes.bonusFertilizeIncrease;
    }

    public String getNameType() {
        return nameType;
    }

    public int getBonusCoin() {
        return bonusCoin;
    }

    public int getSeedReductionCost() {
        return seedReductionCost;
    }

    public int getBonusLimitIncrease() {
        return bonusLimitIncrease;
    }

    public int getBonusFertilizeIncrease() {
        return bonusFertilizeIncrease;
    }
}
