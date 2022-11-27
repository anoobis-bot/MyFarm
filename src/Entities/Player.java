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
    private final Point point;
    private final Equipment tool;
    private Seed seed;

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
        this.point = new Point();
        this.tool = new Equipment();

        this.waterUsed = 0;
        this.fertilizerUsed = 0;

        // The default tool is plow and the default seed that the player is holding is turnip
        this.tool.setTool(ToolAttributes.PLOW);
        this.grabSeed(SeedAttributes.TURNIP);

        this.operationType = USE_TOOL;
    }

    /*
        Get methods
    */
    public double getFarmerExp() {
        return farmerExp;
    }
    public int getFarmerLvl() {
        return farmerLvl;
    }
    public double getObjCoin() {
        return objCoin;
    }
    public Point getPoint() {
        return point;
    }
    public FarmerType getFarmerType() {
        return farmerType;
    }
    public Equipment getTool() {
        return tool;
    }
    public int getOperationTypeType()
    {
        return operationType;
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
    public void useTool(Land[][] landMatrix)
    {
        if(tool.verifyUsage_Mny(this.objCoin) &&
                tool.verifyUsage_Lnd(landMatrix[point.getYCoordinate()][point.getXCoordinate()].hasRocks(),
                landMatrix[point.getYCoordinate()][point.getXCoordinate()].isPlowed()))
        {
            // Defines which tool will be used
            switch (tool.getToolName())
            {
                case "Plow":
                    if (tool.isRequiredRocksClear() && landMatrix[point.getYCoordinate()][point.getXCoordinate()].getCurrentSeed() == null
                        && !landMatrix[point.getYCoordinate()][point.getXCoordinate()].isPlowed())
                    {
                        this.objCoin -= tool.getUsageCost();
                        this.farmerExp += tool.getExpGain();

                        landMatrix[point.getYCoordinate()][point.getXCoordinate()].plowLand(); //plow method from Entities.Land class
                    } else
                        System.out.println("Can't use plow tool");
                    break;

                case "Watering Can":
                    if (tool.isRequiredPlowed() && landMatrix[point.getYCoordinate()][point.getXCoordinate()].getCurrentSeed() != null) {
                        if(waterUsed < seed.getWaterBonus() + farmerType.getBonusWaterLimitIncrease())
                        {
                            this.objCoin -= tool.getUsageCost();
                            this.farmerExp += tool.getExpGain();

                            landMatrix[point.getYCoordinate()][point.getXCoordinate()].waterLand(); //waterLand method from Entities.Land class
                            waterUsed++;
                        }else System.out.println("Limit Reached");
                    } else
                        System.out.println("plow land first to use tool");
                    break;

                case "Fertilizer":
                    if (tool.isRequiredPlowed() && landMatrix[point.getYCoordinate()][point.getXCoordinate()].getCurrentSeed() != null) {
                        if (fertilizerUsed < seed.getFertilizerBonus() + farmerType.getBonusFertilizeIncrease())
                        {
                            this.objCoin -= tool.getUsageCost();
                            this.farmerExp += tool.getExpGain();

                            landMatrix[point.getYCoordinate()][point.getXCoordinate()].fertilizeLand(); //fertilizeLand method from Entities.Land class
                            fertilizerUsed++;
                        } else
                            System.out.println("Limit Reached");
                    } else
                        System.out.println("clear rocks to plow");
                    break;

                default:
                    System.out.println("Coming Soon!"); //MCO2
            }
        } else System.out.println("Tool cannot be used");
    }
    /*
        @return Entities.Seed. returns the seed object
     */
    public Seed getSeed(){ return seed; }
    /*
        Changes the seed the player is holding
        @param seed input from one of the enum field of SeedAttributes
     */
    public void grabSeed(SeedAttributes seed){ this.seed = new Seed(seed); }
    /*
        @param landMatrix input the landMatrix object. It is to be altered if a seed is planted
     */
    public void plantSeed(Land[][] landMatrix)
    {
        // if the land is plowed or there are no rocks
        if (seed.verifyUsage_Lnd(landMatrix[point.getYCoordinate()][point.getXCoordinate()].hasRocks(),
                                    landMatrix[point.getYCoordinate()][point.getXCoordinate()].isPlowed()))
        {
            // if the player has enough money
            if (seed.verifyUsage_Mny(this.objCoin))
            {
                // if the land is not populated with seed
                if (landMatrix[point.getYCoordinate()][point.getXCoordinate()].getCurrentSeed() == null)
                {
                    landMatrix[point.getYCoordinate()][point.getXCoordinate()].setSeed(seed);   // plant the seed
                    this.objCoin -= seed.getSeedCost() + farmerType.getSeedReductionCost();   // subtract the cost
                } else System.out.println("Entities.Land has seed already");
            } else System.out.println("Not enough object coin to use");
        }else System.out.println("Entities.Land is not yet plowed");
    }

    /*
       harvests the seed specified by the point
       @param landMatrix a 2D array from the Entities.Land class
     */
    public void harvestSeed(Land[][] landMatrix)
    {
        int produced;
        double harvestTotal, waterBonus, fertilizerBonus, finalHarvestPrice;

        if (landMatrix[point.getYCoordinate()][point.getXCoordinate()].getCurrentSeed().getHrvstDays() == landMatrix[point.getYCoordinate()][point.getXCoordinate()].getCurrentSeed().getAgeInDays()
                && landMatrix[point.getYCoordinate()][point.getXCoordinate()].getAmtWater() >= landMatrix[point.getYCoordinate()][point.getXCoordinate()].getCurrentSeed().getWaterNeeds()
                && landMatrix[point.getYCoordinate()][point.getXCoordinate()].getAmtFertilizer() >= landMatrix[point.getYCoordinate()][point.getXCoordinate()].getCurrentSeed().getFertilizerNeeds())
        {
            produced = (int) ( Math.random()*( seed.getProducedQtyMax()-seed.getProducedQtyMin()+1) + seed.getProducedQtyMin());
            harvestTotal = produced * (seed.getBaseSellPrice() + getFarmerType().getBonusCoin());
            waterBonus = harvestTotal * 0.2 *(landMatrix[point.getYCoordinate()][point.getXCoordinate()].getAmtWater() - 1);
            fertilizerBonus = harvestTotal * 0.5 * landMatrix[point.getYCoordinate()][point.getXCoordinate()].getAmtFertilizer();
            finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;
            System.out.println("Revenue: " + finalHarvestPrice + ", Products Produced: " + produced);

            objCoin += finalHarvestPrice + farmerType.getBonusCoin();
            farmerExp += seed.getExpYield();

            //reset the tile to unplowed
            landMatrix[point.getYCoordinate()][point.getXCoordinate()].resetValues();
        } else
            System.out.println("Cannot Harvest");
    }
    /*
        set farmer type depends on the conditions
        @param option - choices input by the user
    */
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

    public void setPoint(int y, int x)
    {
        point.setYCoordinate(y);
        point.setXCoordinate(x);
    }

    public void setOperationType(int opType)
    {
        this.operationType = opType;
    }

    public void changeValObjCoin(double value)
    {
        objCoin += value;
    }
}
