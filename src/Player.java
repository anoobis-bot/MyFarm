/*
    This class is the blueprint for the player object in the game.
 */

import Constants.FarmerTypeAttributes;
import Constants.ToolAttributes;
import Constants.SeedAttributes;
import java.lang.Math;

public class Player {
    private double farmerExp;
    private int farmerLvl, objCoin;
    // The relationship of player with the objects below is composition
    private FarmerType farmerType;
    private final Point point;
    private final Equipment tool;
    private Seed seed;

    /*
        Initialize the object coin, farmerExp, and farmerLvl
        @param objCoin how much is the starting coins
        @param farmerExp how much is the starting experience
        @param farmerLvl what is the starting level of the farmer
     */
    public Player(int objCoin, double farmerExp) {
        this.farmerType = new FarmerType(FarmerTypeAttributes.FARMER);
        this.objCoin = objCoin;
        this.farmerExp = farmerExp;
        this.farmerLvl = (int) (farmerExp / 100);
        this.point = new Point();
        this.tool = new Equipment();
        // The default tool is plow and the default seed that the player is holding is turnip
        this.tool.setTool(ToolAttributes.PLOW);
        this.grabSeed(SeedAttributes.TURNIP);
    }

    // Getters
    public double getFarmerExp() {
        return farmerExp;
    }

    public int getFarmerLvl() {
        return farmerLvl;
    }

    public int getObjCoin() {
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

    /*
        Changes the tool the player is holding
        @param tool input from one of the enum field of ToolAttributes
     */
    public void changeTool(ToolAttributes tool) { this.tool.setTool(tool); }

    public void useTool(Land[][] landMatrix)
    {
        boolean hasRocks = landMatrix[point.getYCoordinate()][point.getXCoordinate()].hasRocks(),
                hasSeed = landMatrix[point.getYCoordinate()][point.getXCoordinate()].getCurrentSeed() != null,
                Plowed = landMatrix[point.getYCoordinate()][point.getXCoordinate()].isPlowed();

        switch (tool.getToolName()){
            case "Plow":
                if (hasRocks){ System.out.println("Can't use on rocks!"); }
                else if (hasSeed) { System.out.println("Can't use on land with seed!"); }
                else if (Plowed) { System.out.println("Plowed already");}
                else
                    landMatrix[point.getYCoordinate()][point.getXCoordinate()].plowLand();
                    farmerExp += 0.5;
                break;

            case "Watering Can":
                if(Plowed){
                    if (seed.getWaterBonus() >= landMatrix[point.getYCoordinate()][point.getXCoordinate()].getAmtWater() + 1)
                    {
                        if (hasSeed)
                        {
                            farmerExp += 0.5;
                            landMatrix[point.getYCoordinate()][point.getXCoordinate()].waterLand();
                            System.out.println("Successfully Watered");
                        }
                    } else
                        System.out.println("Can't use watering can");
                } else System.out.println("Can't use on land without seed");
                break;

            case "Fertilizer":
                if(hasSeed){
                    if ((seed.getFertilizerBonus() >= landMatrix[point.getYCoordinate()][point.getXCoordinate()].getAmtFertilizer())
                            && objCoin > 10){
                        farmerExp += 4;
                        landMatrix[point.getYCoordinate()][point.getXCoordinate()].fertilizeLand();
                        objCoin -= 10;
                        System.out.println("Successfully Fertilized");
                    }else
                        System.out.println("Can't use Fertilizer");
                }else System.out.println("Can't use on land without seed");
                break;

            default:
                System.out.println("Coming Soon!");
                break;
        }
    }

    /*
        @return Seed. returns the seed object
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
                    this.objCoin = this.objCoin - seed.getSeedCost();   // subtract the cost
                }
            }
        }
    }

    public void harvestSeed(Land[][] landMatrix)
    {
        int produced;
        double harvestTotal, waterBonus, fertilizerBonus, finalHarvestPrice;
        if (seed.getHrvstDays() == seed.getAgeInDays())
        {
            produced = (int) ( Math.random()*( seed.getProducedQtyMax()-seed.getProducedQtyMin()+1) + seed.getProducedQtyMin());
            harvestTotal = produced * 0.2 * (seed.getBaseSellPrice() + getFarmerType().getBonusCoin());
            waterBonus = harvestTotal * 0.2 *(landMatrix[point.getYCoordinate()][point.getXCoordinate()].getAmtWater() - 1);
            fertilizerBonus = harvestTotal * 0.5 * landMatrix[point.getYCoordinate()][point.getXCoordinate()].getAmtFertilizer();
            finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;
            System.out.println(finalHarvestPrice + "\n" + produced);
            objCoin += finalHarvestPrice;

            landMatrix[point.getYCoordinate()][point.getXCoordinate()].resetValues();
        } else
            System.out.println("Cannot Harvest");
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

        this.tool.setTool(ToolAttributes.PLOW);
        this.grabSeed(SeedAttributes.TURNIP);
    }
}
