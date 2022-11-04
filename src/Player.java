import Constants.FarmerTypeAttributes;
import Constants.ToolAttributes;
import Constants.SeedAttributes;

public class Player {
    private FarmerType farmerType;
    private double farmerExp;
    private int farmerLvl, objCoin;
    private Point point;
    private Equipment tool;
    private Seed seed;

    Player(int objCoin, double farmerExp, int farmerLvl) {
        this.farmerType = new FarmerType(FarmerTypeAttributes.FARMER);
        this.objCoin = objCoin;
        this.farmerExp = farmerExp;
        this.farmerLvl = farmerLvl;

        this.point = new Point();
        this.tool = new Equipment();
        this.tool.setTool(ToolAttributes.PLOW);
        this.grabSeed(SeedAttributes.TURNIP);
    }

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

    public Seed getSeed()
    {
        return seed;
    }

    public void grabSeed(SeedAttributes seed)
    {
        this.seed = new Seed(seed);
    }
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
