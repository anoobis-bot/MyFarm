/*
    This class is the blueprint for the player object in the game.
 */

package Entities;

import Constants.CropType;
import Constants.FarmerTypeAttributes;
import Constants.ToolAttributes;
import Constants.SeedAttributes;
import java.lang.Math;

public class Player {
    private double playerExp;
    private int playerLvl, waterUsed, fertilizerUsed;
    private double objCoin;
    // The relationship of player with the objects below is composition
    private FarmerType farmerType;
    private int xPointer;
    private int yPointer;
    private final Equipment tool;
    private Seed seed;
    private String reason;

    private int operationType;
    public final int PLANT = 1;
    public final int USE_TOOL = 2;
    public final int HARVEST = 3;

    /*
        Initialize the object coin, playerExp, and farmerLvl
        @param objCoin how much is the starting coins
        @param playerExp how much is the starting experience
        @param farmerLvl what is the starting level of the farmer
     */
    public Player(int objCoin, double playerExp)
    {
        this.farmerType = new FarmerType(FarmerTypeAttributes.FARMER);
        this.objCoin = objCoin;
        this.playerExp = playerExp;
        this.playerLvl = (int) (playerExp / 100);
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

    public void changeFarmerExp(double value)
    {
        playerExp += value;
        playerLvl = (int) playerExp / 100;
    }

    public double getPlayerExp() {
        return playerExp;
    }

    public String getReason() {
        return reason;
    }

    public int getPlayerLvl() {
        return playerLvl;
    }
    public FarmerType getFarmerType() {
        return farmerType;
    }

    /*
     * set farmer type depends on the conditions
     * @param option - choices input by the user
     *
     * @ return boolean true if the player is able to upgrade the farmer
     */
    public boolean upgradeFarmerType()
    {
        // Gets which is the next level to upgrade to
        int farmerNextLevel = FarmerTypeAttributes.valueOf(farmerType.getEnumName()).ordinal() + 1;

        FarmerTypeAttributes[] upgradeList = FarmerTypeAttributes.values();

        // if there are no more upgrades / the maximum upgrade is reached, do no upgrade anymore
        if (farmerNextLevel >= upgradeList.length)
        {
            return false;
        }
        // If the level requirement is not met
        else if (upgradeList[farmerNextLevel].levelRequirement > this.playerLvl)
        {
            return false;
        }
        // if the player does not have enough coin
        else if (upgradeList[farmerNextLevel].registrationFee > this.objCoin)
        {
            return false;
        }

        // If all verifications passes, upgrade to the next farmer type
        this.farmerType = new FarmerType(upgradeList[farmerNextLevel]);
        // deduct cost
        useObjCoin(- (this.farmerType.getRegistrationFee()));
        return true;
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


    public int getOperationType()
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

        @ return boolean true if it successfully uses the tool
    */
    public boolean useTool(Land[][] landMatrix)
    {
        Land currLand = landMatrix[yPointer][xPointer];
        if(tool.verifyUsage_Mny(objCoin) && tool.verifyUsage_Lnd(currLand.isPlowed(),
                                                                        currLand.hasSeed(),
                                                                        currLand.hasRocks(),
                                                                        currLand.isWithered()))
        {
            // Defines which method will be used based on the current tool of the player
            switch (ToolAttributes.valueOf(tool.getEnumName())) {
                case PLOW -> currLand.plowLand();
                case WATERING_CAN -> currLand.waterLand(farmerType.getBonusWaterLimitIncrease());
                case FERTILIZER -> currLand.fertilizeLand(farmerType.getBonusFertilizeIncrease());
                case PICKAXE -> currLand.removeRocks();
                case SHOVEL -> currLand.shovelLand();
            }

            // subtract cost and get exp
            useObjCoin( - (tool.getUsageCost()) );
            changeFarmerExp(tool.getExpGain());
            return true;
        }
        else{
            // Sets the reason for failing to use the tool
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
        @param game input the game object. It uses the size of the land for Fruit Tree planting

        @return boolean true if it successfully plants the seed in the land
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

       @return boolean true if it has a successful harvest
     */
    public boolean harvestCrop(Land[][] landMatrix)
    {
        double harvestTotal, waterBonus, fertilizerBonus, finalHarvestPrice;

        Land currLand = landMatrix[yPointer][xPointer];

        // If the land has no seed, do not harvest
        if (!currLand.hasSeed())
            return false;

        // Shorten syntax
        Seed currSeedInLand = currLand.getCurrentSeed();

        // If the player is able to harvest
        if (currSeedInLand.getHrvstDays() == Seed.HARVEST_TIME
                && currLand.validateWaterFertilizer())
        {
            // Formula from MP Specs
            harvestTotal = currSeedInLand.getProducedQty() *
                            (currSeedInLand.getBaseSellPrice() + getFarmerType().getBonusCoin());
            waterBonus = harvestTotal * 0.2 * (currLand.getAmtWater() - 1);
            fertilizerBonus = harvestTotal * 0.5 * currLand.getAmtFertilizer();
            finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;
            if (currSeedInLand.getCropType().equals(CropType.FLOWER))
                finalHarvestPrice *= 1.1;
            System.out.println("Revenue: " + finalHarvestPrice + ", Products Produced: " +
                                currSeedInLand.getProducedQty());

            // Gain coins and exp
            useObjCoin(finalHarvestPrice + farmerType.getBonusCoin());
            changeFarmerExp(currSeedInLand.getExpYield());

            //reset the tile to unplowed
            currLand.resetValues();

            return true;
        }
        else
            return false;
    }

    /*
      This method resets all variables in the class
    */
    public void resetPlayer()
    {
        this.farmerType = new FarmerType(FarmerTypeAttributes.FARMER);
        this.objCoin = 100;
        this.playerExp = 0.0;
        this.playerLvl = 0;

        this.waterUsed = 0;
        this.fertilizerUsed = 0;

        this.tool.setTool(ToolAttributes.PLOW);
        this.grabSeed(SeedAttributes.TURNIP);
    }
}
