/*
 * file name: Controller.java
 * Developers:  Francis De Leon
 *              Mark Abergos
 */

// Importing user-defined classes
import Constants.SeedAttributes;
import Constants.ToolAttributes;
import Entities.GameEnvironment;
import Entities.Player;
import Entities.Land;

// Pre-defined classes mostly used for GUI
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

 /*
  * This class serves as the controller for the front end. It handles the communication between
  * the entities and the GUI.
  */
public class Controller implements ActionListener {
    /*
     * The type of relationship that controller has with other entities is aggregation.
     * The controller changes aspects of the player and the land based on the user input
     */
    Render render;
    private final Player player;
    private final GameEnvironment game;
    private final Land[][] landMatrix;

    /*
     * These String codes is the language that the front end and the back end uses to communicate with each other
     * When the user clicks a specific button in a GUI, one of these codes will be delivered to the back-end
     */
    private static final String CODE_LAND = "LAND";
    private static final String CODE_TOOL = "TOOL";
    private static final String CODE_SEED = "SEED";
    private static final String CODE_HARVEST = "HARVEST";
    private static final String CODE_UPGRADE = "UPGRADE";
    private static final String CODE_NEXT_DAY = "NEXT DAY";

    /*
     * The type of relationship[ that these buttons have with the controller class is aggregation.
     * These buttons are first instantiated in the Render class, which they are then passes to this class
     * for manipulation.
     */
    private JButton[][] landMatrixBtns;
    private JButton[] seedBtns, toolBtns;
    private JButton nextDayBtn, upgradeBtn, harvestBtn;
    private JLabel dayLabel, farmerTypeLabel, playerLevelLabel, coinLabel;

    // Might not be used. Delete at final checking.
    ToolAttributes[] toolsInfo = ToolAttributes.values();
    SeedAttributes[] seedsInfo = SeedAttributes.values();

    // These values will be used for iterating through every tools or seeds in the game
    private final int TOOLS_TOTAL = ToolAttributes.values().length;
    private final int SEEDS_TOTAL = SeedAttributes.values().length;

    /*
     * These entities have an aggregation type of relationship with the controller.
     * They are aggregated when the Controller class is instantiated
     */
    public Controller(Player player, Land[][] landMatrix, GameEnvironment game, String GAME_NAME)
    {
        this.player = player;
        this.landMatrix = landMatrix;
        this.game = game;

        // creates the main frame
        this.render = new Render(this, GAME_NAME);
        render.mainFrame.setVisible(true);
    }

    /*
     * This is the prominent method of this class. Every button click in the main game is executed
     * It is divided by if statements.
     * Each clause have their specific function based on the operation type
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        // Getting the button that was pressed
        JButton currButton = (JButton) event.getSource();

        /*
         * Each button has their own message attached to it
         * OPERATION_TYPE,TOOL/SEED/COORDINATES
         * The line immedietly below this comment gets the operation type the button is
         *  - Whether they clicked the tool, seed, land, harvest, or next day
         */
        StringTokenizer eventInfo = new StringTokenizer(event.getActionCommand(), ",");
        String opType = eventInfo.nextToken();

        // Every button press checks if the game is already over.
        if (chckGameOver()){
            render.mainFrame.dispose(); // closes Main Window
            new GameOverController(); // opens Game Over Window
        }
        else
        {
            /*
             * There could only one type of operation from the choices:
             * Planting, using tool, or harvesting
             */
            if (opType.equals(CODE_SEED) || opType.equals(CODE_TOOL) || opType.equals(CODE_HARVEST)) {
                // This section highlights which operation the user is currently doing, either a specific tool,
                // specific seed, or harvesting
                for (int currTool = 0; currTool < TOOLS_TOTAL; currTool++) {
                    if (toolBtns[currTool].getActionCommand().equals(currButton.getActionCommand()))
                        currButton.setBackground(Color.PINK);
                    else
                        toolBtns[currTool].setBackground(Color.WHITE);
                }
                for (int currSeed = 0; currSeed < SEEDS_TOTAL; currSeed++) {
                    if (seedBtns[currSeed].getActionCommand().equals(currButton.getActionCommand()))
                        currButton.setBackground(Color.PINK);
                    else
                        seedBtns[currSeed].setBackground(Color.WHITE);
                }
                if (harvestBtn.getActionCommand().equals(currButton.getActionCommand()))
                    currButton.setBackground(Color.PINK);
                else
                    harvestBtn.setBackground(Color.WHITE);

                // This part now executes the back-end codes for the specific type of operation. It signals to the
                // player class what the operation type the player is in.
                if (opType.equals(CODE_TOOL)) {
                    String toolType = eventInfo.nextToken();
                    player.changeTool(ToolAttributes.valueOf(toolType));
                    player.setOperationType(player.USE_TOOL);
                } else if (opType.equals(CODE_SEED)) {
                    String seedType = eventInfo.nextToken();
                    player.grabSeed(SeedAttributes.valueOf(seedType));
                    player.setOperationType(player.PLANT);
                } else if (opType.equals(CODE_HARVEST)) {
                    player.setOperationType(player.HARVEST);
                }
            }

            // The land is what the tools, seeds, and harvest function will be interacting to
            // If a land button is clicked, a code will be executed based on the player class' operation type
            // (which was set using the above code)
            else if (opType.equals(CODE_LAND)) {
                // Getting the coordinate of which land button is clicked
                player.setYPointer(Integer.parseInt(eventInfo.nextToken()));
                player.setXPointer(Integer.parseInt(eventInfo.nextToken()));

                /*
                 * the methods useTool, plantSeed and harvestCrop are the methods that execute
                 * the necessary operations for the back-end. They return false when they were not able to
                 * execute their operation properly (ex. when one of the condition for planting is not met)
                 */
                if (player.getOperationType() == player.USE_TOOL) {
                    if (!player.useTool(landMatrix)) {
                        // Displays message box for alerting user in using tool.
                        JOptionPane.showMessageDialog(null,
                                "You can't use " + player.getTool().getToolName()
                                + " on this this land\nReason:\n" + player.getReason());
                    }
                } else if (player.getOperationType() == player.PLANT) {
                    if (!player.plantSeed(landMatrix, game)) {
                        // Displays message box for alerting user in planting crop.
                        JOptionPane.showMessageDialog(null,
                                "You can't plant " + player.getSeed().getSeedName()
                                + " on this this land\nReason:\n" + player.getReason());
                    }
                } else if (player.getOperationType() == player.HARVEST) {
                    if (!player.harvestCrop(landMatrix)) {
                        // Displays message box for alerting user in harvesting crop.
                        JOptionPane.showMessageDialog(null, player.getReason());
                    }
                }

                // Updates the buttons and the labels GUI due to changes from the operations
                updateLandButton(currButton, player.getYPointer(), player.getXPointer());
                updateLabels();
            }

            // If the player clicks the Next day function
            else if (opType.equals(CODE_NEXT_DAY)) {
                // This method from the game class updates all the land tiles and their seed harvest time.
                // This function also sets the tile if it is withered or not
                game.advanceTime(landMatrix);

                // Updates all land buttons and labels
                updateAllLandButton();
                updateLabels();
            }

            // If the player clicks the upgrade farmer button
            else if (opType.equals(CODE_UPGRADE)) {
                // This method upgrades the farmer's status. It returns false when proper conditions are not met.
                if (!player.upgradeFarmerType()) {
                    // Displays message box for alerting user in UPGRADING PLAYER
                    JOptionPane.showMessageDialog(null, player.getReason());
                }

                // Updates the labels, specifically the display which displays the status of the farmer
                updateLabels();
            }
        }
    }

    /*
     * The relationship of this controller class with the buttons and labels is aggregation. The buttons
     * in the Render class are passed into this class for manipulation
     */
    public void setButtons(JButton[][] landMatrixBtns, JButton[] seedBtns, JButton[] toolBtns,
                            JButton nextDayBtn, JButton upgradeBtn, JButton harvestBtn)
    {
        this.landMatrixBtns = landMatrixBtns;
        this.seedBtns = seedBtns;
        this.toolBtns = toolBtns;

        this.nextDayBtn = nextDayBtn;
        this.upgradeBtn = upgradeBtn;
        this.harvestBtn = harvestBtn;
    }

    public void setLabels(JLabel dayLabel, JLabel farmerTypeLabel, JLabel playerLevelLabel, JLabel coinLabel)
    {
        this.dayLabel = dayLabel;
        this.farmerTypeLabel = farmerTypeLabel;
        this.playerLevelLabel = playerLevelLabel;
        this.coinLabel = coinLabel;

        this.dayLabel.setText("Day " + game.getCurrentDay());
        this.farmerTypeLabel.setText(player.getFarmerType().getNameType());
        this.playerLevelLabel.setText("Level: " + player.getPlayerLvl());
        this.coinLabel.setText("Coins: " + player.getObjCoin());
    }

    // Updates the text of the labels situated at the top of the GUI
    public void updateLabels()
    {
        this.dayLabel.setText("Day " + game.getCurrentDay());
        this.farmerTypeLabel.setText(player.getFarmerType().getNameType());
        this.playerLevelLabel.setText("Level: " + player.getPlayerLvl());

        DecimalFormat df = new DecimalFormat("#.##");
        this.coinLabel.setText("Coins: " + df.format(player.getObjCoin()));
    }

    /*
     * Updates the current land button clicked / current button iteration in updateAllLandButton
     * This method sets the text in the button whether they have a rock, is planted, withered, etc...
     */
    public void updateLandButton(JButton currButton, int yPointer, int xPointer)
    {
        Land currLand = landMatrix[yPointer][xPointer];
        if (currLand.isWithered())
            currButton.setText("W");
        else if (currLand.hasRocks())
            currButton.setText("R");
        else if (currLand.hasSeed())
            currButton.setText(String.valueOf(currLand.getCurrentSeed().getSeedName().charAt(0)));
        else if (currLand.isPlowed())
            currButton.setText("P");
        else
            currButton.setText("L");
    }

    // Iterates through all land buttons and updates their texts
    public void updateAllLandButton()
    {
        int landWidth = game.getXSize();
        int landHeight = game.getYSize();

        for (int currYBtn = 0; currYBtn < landHeight; currYBtn++)
        {
            for (int currXBtn = 0; currXBtn < landWidth; currXBtn++)
            {
                updateLandButton(landMatrixBtns[currYBtn][currXBtn], currYBtn, currXBtn);
            }
        }
    }

    // Function that returns true if all land tiles have active plant
    public boolean checkFarmHasPlant(){
        int z=0;
        for (int y = 0; y < game.getYSize(); y++)
            for (int x = 0; x < game.getXSize(); x++){
                if(landMatrix[y][x].hasSeed() && !landMatrix[y][x].isWithered()){
                    z++;
                }
            }
        if(z!=0)
           return true;
        else
            return false;
    }

    // returns true if the game is over because of the conditions
    public boolean chckGameOver(){
        if(!checkFarmHasPlant() && player.getObjCoin() < 5)
            return true;

        //else if () {
         else return false;
    }

    // The methods below are get methods for private fields.

    public Land[][] getLandMatrix(){return this.landMatrix;}
    public int getWidthLand()
    {
        return game.getXSize();
    }

    public int getHeightLand()
    {
        return game.getYSize();
    }

    public static String getCodeLand()
    {
        return CODE_LAND;
    }
    public static String getCodeTool()
    {
        return CODE_TOOL;
    }
    public static String getCodeSeed()
    {
        return CODE_SEED;
    }
    public static String getCodeHarvest()
    {
        return CODE_HARVEST;
    }
    public static String getCodeUpgrade()
    {
        return CODE_UPGRADE;
    }
    public static String getCodeNextDay()
    {
        return CODE_NEXT_DAY;
    }
}
