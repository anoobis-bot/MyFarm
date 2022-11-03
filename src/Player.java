import Constants.FarmerTypeAttributes;
import Constants.ToolAttributes;

public class Player
{
    private FarmerType farmerType;
    private double farmerExp;
    private int farmerLvl;
    private int objCoin;

    private Point point;
    private Equipment tool;

    Player(int objCoin, double farmerExp, int farmerLvl)
    {
        this.farmerType = new FarmerType(FarmerTypeAttributes.FARMER);
        this.objCoin = objCoin;
        this.farmerExp = farmerExp;
        this.farmerLvl = farmerLvl;

        this.point = new Point();
        this.tool = new Equipment();
        this.tool.setTool(ToolAttributes.PLOW);
    }

    public double getFarmerExp()
    {
        return farmerExp;
    }

    public int getFarmerLvl()
    {
        return farmerLvl;
    }

    public int getObjCoin()
    {
        return objCoin;
    }

    public Point getPoint()
    {
        return point;
    }

    public FarmerType getFarmerType()
    {
        return farmerType;
    }

    public Equipment getTool()
    {
        return tool;
    }

    public void changeTool(ToolAttributes tool)
    {
        this.tool.setTool(tool);
    }

    //    public boolean useTool(Land[][] landMatrix)
//    {
//        return true;
//    }



}