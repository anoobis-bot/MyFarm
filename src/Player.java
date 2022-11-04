/*
    This class is the blueprint for the player object in the game.
 */

import Constants.FarmerTypeAttributes;
import Constants.ToolAttributes;
import Constants.SeedAttributes;

public class Player {
    private double farmerExp;
    private int farmerLvl, objCoin;
    // The relationship of player with the objects below is composition
    private FarmerType farmerType;
    private final Point point;
    private Equipment tool;
    private Seed seed;

    /*
        Initialize the object coin, farmerExp, and farmerLvl
        @param objCoin how much is the starting coins
        @param farmerExp how much is the starting experience
        @param farmerLvl what is the starting level of the farmer
     */
    public Player(int objCoin, double farmerExp, int farmerLvl) {
        this.farmerType = new FarmerType(FarmerTypeAttributes.FARMER);
        this.objCoin = objCoin;
        this.farmerExp = farmerExp;
        this.farmerLvl = farmerLvl;

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
    public void changeTool(ToolAttributes tool) {
        this.tool.setTool(tool);
    }

    //    public boolean useTool(Land[][] landMatrix)
//    {
//        return true;
//    }

    public int subtractCoin() {
        this.objCoin--;
        return objCoin;
    }

    /*
        @return Seed. returns the seed object
     */
    public Seed getSeed()
    {
        return seed;
    }

    /*
        Changes the seed the player is holding
        @param seed input from one of the enum field of SeedAttributes
     */
    public void grabSeed(SeedAttributes seed)
    {
        this.seed = new Seed(seed);
    }

    /*
        @param landMatrix input the landMatrix object. It is to be altered if a seed is planted
        @return true if a plant is planted. False if a seed is not planted
     */
    public boolean plantSeed(Land[][] landMatrix)
    {
        if (!(landMatrix[point.getYCoordinate()][point.getXCoordinate()].hasRocks()))
        {
            if (landMatrix[point.getYCoordinate()][point.getXCoordinate()].isPlowed())
            {
                if (this.objCoin >= seed.getSeedCost())
                {
                    if (landMatrix[point.getYCoordinate()][point.getXCoordinate()].getCurrentSeed() == null)
                    {
                        landMatrix[point.getYCoordinate()][point.getXCoordinate()].setSeed(seed);
                        this.objCoin = this.objCoin - seed.getSeedCost();
                        return true;
                    }
                }
            }
        }

        return false;
    }



}
