public class Display
{
    private Player player;
    private Land[][] landMatrix;
    private GameEnvironment environment;

    public Display(Player player, Land[][] landMatrix, GameEnvironment environment)
    {
        this.player = player;
        this.landMatrix = landMatrix;
        this.environment = environment;
    }

    public void render()
    {
        System.out.println("Hello World!");
    }
}
