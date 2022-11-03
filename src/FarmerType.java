import Constants.FarmerTypeAttributes;

public class FarmerType
{
    private String nameType;
    private int bonusCoin;
    private int seedReductionCost;
    private int bonusLimitIncrease;
    private int bonusFertilizeIncrease;

    FarmerType(FarmerTypeAttributes farmerAttributes)
    {
        this.nameType = farmerAttributes.nameType;
        this.bonusCoin = farmerAttributes.bonusCoin;
        this.seedReductionCost = farmerAttributes.seedReductionCost;
        this.bonusLimitIncrease = farmerAttributes.bonusLimitIncrease;
        this.bonusFertilizeIncrease = farmerAttributes.bonusFertilizeIncrease;
    }
}
