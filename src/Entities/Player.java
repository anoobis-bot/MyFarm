/*
    This class is the blueprint for the player object in the game.
 */

package Entities;

import Constants.CropType;
import Constants.FarmerTypeAttributes;
import Constants.ToolAttributes;
import Constants.SeedAttributes;

import javax.swing.*;
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
            //UpgradeFarmer REASON #1 == Max Upgrade
            reason = "Max Farmer Upgrade Reached ";
            return false;
        }
        // If the level requirement is not met
        else if (upgradeList[farmerNextLevel].levelRequirement > this.playerLvl)
        {
            //UpgradeFarmer REASON #2 == Level not Reached
            reason = "Level not yet reached\nLevel Required: " + upgradeList[farmerNextLevel].levelRequirement;
            return false;
        }
        // if the player does not have enough coin
        else if (upgradeList[farmerNextLevel].registrationFee > this.objCoin)
        {
            //UpgradeFarmer REASON #3 == Insufficient money
            reason = "Insufficient Coin\nRegistration Fee: " + upgradeList[farmerNextLevel].registrationFee;
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
                case PLOW: currLand.plowLand(); break;
                case WATERING_CAN: currLand.waterLand(farmerType.getBonusWaterLimitIncrease()); break;
                case FERTILIZER: currLand.fertilizeLand(farmerType.getBonusFertilizeIncrease()); break;
                case PICKAXE: currLand.removeRocks(); break;
                case SHOVEL:
                    if (!currLand.hasSeed())
                        JOptionPane.showMessageDialog(null, "Crop not found." +
                                "\nUsing this ineffectively costs 7 object coins as well." +
                                "\nOnly use this on removing active or withered crops");
                    currLand.shovelLand();

                    break;
            }

            // subtract cost and get exp
            useObjCoin( - (tool.getUsageCost()) );
            changeFarmerExp(tool.getExpGain());
            return true;
        }
        else{
            //Tool REASON #1 == Insufficient money
            if(!tool.verifyUsage_Mny(objCoin))
                reason = "Insufficient Money";
            else if (!tool.verifyUsage_Lnd(currLand.isPlowed(),
                                            currLand.hasSeed(),
                                            currLand.hasRocks(),
                                            currLand.isWithered()))
            {
                // Defines which tool will be used
                switch (ToolAttributes.valueOf(tool.getEnumName())) {

                    //Tool REASON #2 == PLOW TOOL
                    case PLOW:
                        if (currLand.isPlowed())
                            reason = "Land is plowed already"; //Tool REASON #2.1 == Land is already plowed
                        else if (currLand.hasRocks())
                            reason = "Land has rocks"; //Tool REASON #2.2 == Land has rocks
                        break;

                    //Tool REASON #3 & #4 == Watering Can & Fertilizer
                    case WATERING_CAN:
                    case FERTILIZER:
                        reason = "Crop not found."; //Tool REASON #3.1 == non planted seed
                        break;

                    //Tool REASON #5 == Pickaxe
                    case PICKAXE:
                        reason = "No rocks here!";
                        break;
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

        Land currLand = landMatrix[yPointer][xPointer];

        // Checks all related verification in the land:
        // Plowed, Rocks, existing seed already, Fruit Tree padding
        if (!seed.verifyUsage_Lnd(landMatrix, game, yPointer, xPointer))
        {
            // PLANTING reason #1 - land has rocks
            if(currLand.hasRocks())
                reason = "Land has rocks. Use pickaxe first";

            // PLANTING reason #2 - land isn't plowed
            else if(!currLand.isPlowed())
                reason = "Plow Land to plant!. Use plow tool first";

            // PLANTING reason #3 - land already has seed
            else if (currLand.hasSeed())
                reason = "A crop has already been planted here";

            // PLANTING reason #4 - fruit tree planting
            else if (seed.getCropType().typeName=="Fruit Tree"){
                //if()
                reason = "Fruit Tree should be planted 1 tile away from any planted crop";
            }
            return false;
        }
        // if the player has not enough money
        else if (!seed.verifyUsage_Mny(this.objCoin))
        {
            // PLANTING reason #5
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
        if (!currLand.hasSeed()){
            // Harvest reason #1
            reason = "Land doesn't have seed planted";
            return false;
        }

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
            JOptionPane.showMessageDialog(null, "Object Coins earned: " + finalHarvestPrice
                    + "\nProducts Produced: " + currSeedInLand.getProducedQty());

            // Gain coins and exp
            useObjCoin(finalHarvestPrice + farmerType.getBonusCoin());
            changeFarmerExp(currSeedInLand.getExpYield());

            //reset the tile to unplowed
            currLand.resetValues();

            return true;
        }
        else{
            // Harvest reason #2
            int daysReq, waterReq, fertilizerReq;
            daysReq = currLand.getCurrentSeed().getHrvstDays()- Seed.HARVEST_TIME;
            waterReq = currLand.cropWaterNeeds() - currLand.getAmtWater();
            if (waterReq < 0)
                waterReq = 0;
            fertilizerReq = currLand.cropFertilizerNeeds() - currLand.getAmtFertilizer();
            if (fertilizerReq < 0)
                fertilizerReq = 0;

            //set reason (days required, water needs, fertilizer needs)
            reason = "Crop isn't harvestable yet" +
                    "\nDays Required:" + daysReq +
                    "\nWater Need:" + waterReq +
                    "\nFertilizer Need:" + fertilizerReq;
            return false;
        }
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
