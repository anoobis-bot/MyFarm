import Constants.SeedAttributes;
import Constants.ToolAttributes;
import Entities.GameEnvironment;
import Entities.Player;
import Entities.Land;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;

/*
    This class controls all the back end of the GUI
    It handles the communication between the entities and the GUI
 */
public class Controller implements ActionListener {
    private final Player player;
    private final GameEnvironment game;
    private final Land[][] landMatrix;

    private static final String CODE_LAND = "LAND";
    private static final String CODE_TOOL = "TOOL";
    private static final String CODE_SEED = "SEED";
    private static final String CODE_HARVEST = "HARVEST";
    private static final String CODE_UPGRADE = "UPGRADE";
    private static final String CODE_NEXT_DAY = "NEXT DAY";


    private JButton[][] landMatrixBtns;
    private JButton[] seedBtns, toolBtns;
    private JButton nextDayBtn, upgradeBtn, harvestBtn;
    private JLabel dayLabel, farmerTypeLabel, coinLabel;

    ToolAttributes[] toolsInfo = ToolAttributes.values();
    SeedAttributes[] seedsInfo = SeedAttributes.values();
    private final int TOOLS_TOTAL = ToolAttributes.values().length;
    private final int SEEDS_TOTAL = SeedAttributes.values().length;

    public Controller(Player player, Land[][] landMatrix,GameEnvironment game)
    {
        this.player = player;
        this.landMatrix = landMatrix;
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        JButton currButton = (JButton) event.getSource();

        StringTokenizer eventInfo = new StringTokenizer(event.getActionCommand(), ",");
        String opType = eventInfo.nextToken();

        if (opType.equals(CODE_SEED) || opType.equals(CODE_TOOL) || opType.equals(CODE_HARVEST))
        {
            for (int currTool = 0; currTool < TOOLS_TOTAL; currTool++)
            {
                if (toolBtns[currTool].getActionCommand().equals(currButton.getActionCommand()))
                    currButton.setBackground(Color.PINK);
                else
                    toolBtns[currTool].setBackground(Color.WHITE);
            }
            for (int currSeed = 0; currSeed < SEEDS_TOTAL; currSeed++)
            {
                if (seedBtns[currSeed].getActionCommand().equals(currButton.getActionCommand()))
                    currButton.setBackground(Color.PINK);
                else
                    seedBtns[currSeed].setBackground(Color.WHITE);
            }
            if (harvestBtn.getActionCommand().equals(currButton.getActionCommand()))
                currButton.setBackground(Color.PINK);
            else
                harvestBtn.setBackground(Color.WHITE);

            if (opType.equals(CODE_TOOL))
            {
                String toolType = eventInfo.nextToken();
                player.changeTool(ToolAttributes.valueOf(toolType));
                player.setOperationType(player.USE_TOOL);
            }
            else if (opType.equals(CODE_SEED))
            {
                String seedType = eventInfo.nextToken();
                player.grabSeed(SeedAttributes.valueOf(seedType));
                player.setOperationType(player.PLANT);
            }
            else if (opType.equals(CODE_HARVEST))
            {
                player.setOperationType(player.HARVEST);
            }
        }

        else if (opType.equals(CODE_LAND))
        {
            player.setYPointer(Integer.parseInt(eventInfo.nextToken()));
            player.setXPointer(Integer.parseInt(eventInfo.nextToken()));
            if (player.getOperationType() == player.USE_TOOL)
            {
                if (player.useTool(landMatrix) == false)
                {
                    System.out.println("You cant use " + player.getTool().getToolName() + "!");
                }
            }
            else if (player.getOperationType() == player.PLANT)
            {
                if (player.plantSeed(landMatrix, game) == false)
                {
                    System.out.println("You cant plant " + player.getSeed().getSeedName() + "!");
                }
            }
            else if (player.getOperationType() == player.HARVEST)
            {
                if (player.harvestCrop(landMatrix) == false)
                {
                    System.out.println("You can't harvest!");
                }
            }

            updateLandButton(currButton, player.getYPointer(), player.getXPointer());
            updateLabels();
        }

        else if (opType.equals(CODE_NEXT_DAY))
        {
            game.advanceTime(landMatrix);

            updateAllLandButton();
            updateLabels();
        }

        else if (opType.equals(CODE_UPGRADE))
        {
            if (player.upgradeFarmerType() == false)
            {
                System.out.println("You can't upgrade!");
            }

            updateLabels();
        }
    }

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

    public void setLabels(JLabel dayLabel, JLabel farmerTypeLabel, JLabel coinLabel)
    {
        this.dayLabel = dayLabel;
        this.farmerTypeLabel = farmerTypeLabel;
        this.coinLabel = coinLabel;

        this.dayLabel.setText("Day " + game.getCurrentDay());
        this.farmerTypeLabel.setText(player.getFarmerType().getNameType());
        this.coinLabel.setText("Coins: " + player.getObjCoin());
    }

    public void updateLabels()
    {
        this.dayLabel.setText("Day " + game.getCurrentDay());
        this.farmerTypeLabel.setText(player.getFarmerType().getNameType());
        this.coinLabel.setText("Coins: " + player.getObjCoin());
    }

    public void updateLandButton(JButton currButton, int yPointer, int xPointer)
    {
        Land currLand = landMatrix[yPointer][xPointer];
        if (currLand.isWithered())
            currButton.setText("W");
        else if (currLand.hasSeed())
            currButton.setText(String.valueOf(currLand.getCurrentSeed().getSeedName().charAt(0)));
        else if (currLand.isPlowed())
            currButton.setText("P");
        else if (currLand.hasRocks())
            currButton.setText("R");
        else
            currButton.setText("L");
    }

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
