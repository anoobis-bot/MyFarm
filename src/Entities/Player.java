package Entities;/*
    This class is the blueprint for the player object in the game.
 */

import Constants.FarmerTypeAttributes;
import Constants.ToolAttributes;
import Constants.SeedAttributes;
import java.lang.Math;

public class Player {
    private double farmerExp;
    private int farmerLvl, waterUsed, fertilizerUsed;
    private double objCoin;
    // The relationship of player with the objects below is composition
    private FarmerType farmerType;
    private int xPointer;
    private int yPointer;
    private final Equipment tool;
    private Seed seed;
    private String reason = null;

    private int operationType;
    public final int PLANT = 1;
    public final int USE_TOOL = 2;
    public final int HARVEST = 3;

    /*
        Initialize the object coin, farmerExp, and farmerLvl
        @param objCoin how much is the starting coins
        @param farmerExp how much is the starting experience
        @param farmerLvl what is the starting level of the farmer
     */
    public Player(int objCoin, double farmerExp)
    {
        this.farmerType = new FarmerType(FarmerTypeAttributes.FARMER);
        this.objCoin = objCoin;
        this.farmerExp = farmerExp;
        this.farmerLvl = (int) (farmerExp / 100);
        this.tool = new Equipment();

        this.waterUsed = 0;
        this.fertilizerUsed = 0;
    }

    public double getObjCoin() {
        return objCoin;
    }
    public void useObjCoin(double value)
    {
        objCoin += value;
    }
    public String getReason() {
        return reason;
    }
    public double getFarmerExp() {
        return farmerExp;
    }
    public int getFarmerLvl() {
        return farmerLvl;
    }
    public FarmerType getFarmerType() {
        return farmerType;
    }
    //    set farmer type depends on the conditions
    //    @param option - choices input by the user
    public void setFarmerType(int option)
    {
        switch (option){
            case 1:
                if (objCoin >= 200 && farmerLvl >= 5){
                    this.farmerType = new FarmerType(FarmerTypeAttributes.REGISTERED_FARMER);
                    objCoin -= farmerType.getRegistrationFee();
                }
                else System.out.println("Incorrect input!");
                break;
            case 2:
                if (objCoin >= 300 && farmerLvl >= 10){
                    this.farmerType = new FarmerType(FarmerTypeAttributes.DISTINGUISHED_FARMER);
                    objCoin -= farmerType.getRegistrationFee();
                }
                else System.out.println("Incorrect input!");
                break;
            case 3:
                if (objCoin >= 400 && farmerLvl >= 15){
                    this.farmerType = new FarmerType(FarmerTypeAttributes.LEGENDARY_FARMER);
                    objCoin -= farmerType.getRegistrationFee();
                }
                else System.out.println("Incorrect input!");
                break;
            default:
                System.out.println("Not in options");
        }
    }

    public int getXPointer() {
        return xPointer;
    }
    public int getYPointer() {
        return yPointer;
    }
    public void setXPointer(int x)
    {
        xPointer = x;
    }
    public void setYPointer(int y)
    {
        yPointer = y;
    }

    public int getOperationTypeType()
    {
        return operationType;
    }
    public void setOperationType(int opType)
    {
        this.operationType = opType;
    }


    public Equipment getTool() {
        return tool;
    }
    public Seed getSeed() {
        return seed;
    }
    /*
        Changes the tool the player is holding
        @param tool input from one of the enum field of ToolAttributes
     */
    public void changeTool(ToolAttributes tool) { this.tool.setTool(tool); }
    /*
        Uses the tool assigned to the land specified by the point
        @param landMatrix a 2D array from the Entities.Land class
    */
    public boolean useTool(Land[][] landMatrix)
    {
        Land currLand = landMatrix[yPointer][xPointer];
        if(tool.verifyUsage_Mny(objCoin) && tool.verifyUsage_Lnd(currLand.isPlowed(),
                                                                        currLand.hasSeed(),
                                                                        currLand.hasRocks(),
                                                                        currLand.isWithered()))
        {
            // Defines which tool will be used
            switch (ToolAttributes.valueOf(tool.getEnumName())) {
                case PLOW -> currLand.plowLand();
                case WATERING_CAN -> currLand.waterLand();
                case FERTILIZER -> currLand.fertilizeLand();
                case PICKAXE -> currLand.removeRocks();
                case SHOVEL -> currLand.shovelLand();
            }

            useObjCoin( - (tool.getUsageCost()) );
            return true;
        }
        else{
            if(!tool.verifyUsage_Mny(objCoin))
                reason = "Insufficient Money";
            else if (!tool.verifyUsage_Lnd(currLand.isPlowed(),
                    currLand.hasSeed(),
                    currLand.hasRocks(),
                    currLand.isWithered())) {
                // Defines which tool will be used
                switch (ToolAttributes.valueOf(tool.getEnumName())) {
                    case PLOW -> reason = "Plow can only be used for unplowed tile without rocks";
                    case WATERING_CAN -> reason = "Watering can can only be used on a plowed tile with a crop";
                    case FERTILIZER -> reason = "Fertilizer can only be used on a plowed tile with a crop";
                    case PICKAXE -> reason = "Pickaxe can only be used on tiles with rocks";
                    case SHOVEL -> reason = "Shovel is most effective in removing a crop (withered or not)";
                }
            }
            return false;
        }
    }

    /*
        Changes the seed the player is holding
        @param seed input from one of the enum field of SeedAttributes
     */
    public void grabSeed(SeedAttributes seed){ this.seed = new Seed(seed); }
    /*
        @param landMatrix input the landMatrix object. It is to be altered if a seed is planted
     */
    public boolean plantSeed(Land[][] landMatrix, GameEnvironment game)
    {
        // Guard Clauses. Returns false if the if-else statements failed.
        // Otherwise, if it reaches the bottom, all verifications are met.

        // Checks all related verification in the land:
        // Plowed, Rocks, existing seed already, Fruit Tree padding
        if (!seed.verifyUsage_Lnd(landMatrix, game, yPointer, xPointer))
        {
            reason = "seed can only be planted on unplowed tile without rocks, seed, and a tile further to sa tree crop.";
            return false;
        }
        // if the player has not enough money
        else if (!seed.verifyUsage_Mny(this.objCoin))
        {
            reason = "Insufficient Money";
            return false;
        }

        landMatrix[yPointer][xPointer].setSeed(seed);   // plant the seed
        useObjCoin(- (seed.getSeedCost() + farmerType.getSeedReductionCost()) );    // subtract the cost
        // instantiate a new seed in player since the seed that the player was holding is in the land object already
        grabSeed(SeedAttributes.valueOf(seed.getEnumName()));
        return true;
    }
    /*
       harvests the seed specified by the point
       @param landMatrix a 2D array from the Entities.Land class
     */
//    public void harvestSeed(Land[][] landMatrix)
//    {
//        int produced;
//        double harvestTotal, waterBonus, fertilizerBonus, finalHarvestPrice;
//
//        if (landMatrix[yPointer][xPointer].getCurrentSeed().getHrvstDays() == landMatrix[yPointer][xPointer].getCurrentSeed().getAgeInDays()
//                && landMatrix[yPointer][xPointer].getAmtWater() >= landMatrix[yPointer][xPointer].getCurrentSeed().getWaterNeeds()
//                && landMatrix[yPointer][xPointer].getAmtFertilizer() >= landMatrix[yPointer][xPointer].getCurrentSeed().getFertilizerNeeds())
//        {
//            produced = (int) ( Math.random()*( seed.getProducedQtyMax()-seed.getProducedQtyMin()+1) + seed.getProducedQtyMin());
//            harvestTotal = produced * (seed.getBaseSellPrice() + getFarmerType().getBonusCoin());
//            waterBonus = harvestTotal * 0.2 *(landMatrix[yPointer][xPointer].getAmtWater() - 1);
//            fertilizerBonus = harvestTotal * 0.5 * landMatrix[yPointer][xPointer].getAmtFertilizer();
//            finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;
//            System.out.println("Revenue: " + finalHarvestPrice + ", Products Produced: " + produced);
//
//            objCoin += finalHarvestPrice + farmerType.getBonusCoin();
//            farmerExp += seed.getExpYield();
//
//            //reset the tile to unplowed
//            landMatrix[yPointer][xPointer].resetValues();
//        } else
//            System.out.println("Cannot Harvest");
//    }

    /*
      This method resets all variables in the class
    */
    public void resetPlayer()
    {
        this.farmerType = new FarmerType(FarmerTypeAttributes.FARMER);
        this.objCoin = 100;
        this.farmerExp = 0.0;
        this.farmerLvl = 0;

        this.waterUsed = 0;
        this.fertilizerUsed = 0;

        this.tool.setTool(ToolAttributes.PLOW);
        this.grabSeed(SeedAttributes.TURNIP);
    }
}
