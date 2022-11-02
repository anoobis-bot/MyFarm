package Constants;

public enum SeedAttributes
{
    TURNIP("Turnip", Constants.CropType.ROOT_CROP, 2, 1, 2, 0,
            1, 1, 2, 5, 6, 5),

    CARROT("Carrot", Constants.CropType.ROOT_CROP, 3, 1, 2, 0,
            1, 1, 2, 10, 9, 7.5),

    POTATO("Potato", Constants.CropType.ROOT_CROP, 5, 3, 4, 1,
            2, 1, 10, 20, 3, 12.5),

    ROSE("Rose", Constants.CropType.FLOWER, 1, 1, 2, 0,
            1, 1, 1, 5, 5, 2.5),

    TULIPS("Tulips", Constants.CropType.FLOWER, 2, 2, 3, 0,
            1, 1, 1, 10, 9, 5),

    SUNFLOWER("Sunflower", Constants.CropType.FLOWER, 3, 2, 3, 1,
            2, 1, 1, 20, 19, 7.5),

    MANGO("Mango", Constants.CropType.FRUIT_TREE, 10, 7, 7, 4,
            4, 5, 15, 100, 8, 25),

    APPLE("Apple", Constants.CropType.FRUIT_TREE, 10, 7, 7, 5,
            5, 10, 15, 200, 5, 25);


    String seedName;
    CropType CropType;

    int hrvstDays;

    int waterNeeds;
    int waterBonus;
    int fertilizerNeeds;
    int fertilizerBonus;

    int producedQtyMin;
    int producedQtyMax;

    int seedCost;
    int baseSellPiece;
    double expYield;

    SeedAttributes(String seedName, CropType cropType, int hrvstDays, int waterNeeds, int waterBonus,
                   int fertilizerNeeds, int fertilizerBonus, int producedQtyMin, int producedQtyMax, int seedCost,
                   int baseSellPiece, double expYield)
    {
        this.seedName = seedName;
        this.CropType = cropType;
        this.hrvstDays = hrvstDays;
        this.waterNeeds = waterNeeds;
        this.waterBonus = waterBonus;
        this.fertilizerNeeds = fertilizerNeeds;
        this.fertilizerBonus = fertilizerBonus;
        this.producedQtyMin = producedQtyMin;
        this.producedQtyMax = producedQtyMax;
        this.seedCost = seedCost;
        this.baseSellPiece = baseSellPiece;
        this.expYield = expYield;
    }
}
