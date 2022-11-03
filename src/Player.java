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

    public void changeTool(ToolAttributes tool)
    {
        this.tool.setTool(tool);
    }

    public boolean useTool(Land[][] landMatrix)
    {
        return true;
    }



}
