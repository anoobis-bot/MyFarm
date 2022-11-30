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


    private JButton[][] landMatrixBtns;
    private JButton[] seedBtns, toolBtns;
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

        if (opType.equals(CODE_SEED) || opType.equals(CODE_TOOL))
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
        }
        else if (opType.equals(CODE_LAND))
        {
            player.setYPointer(Integer.parseInt(eventInfo.nextToken()));
            player.setXPointer(Integer.parseInt(eventInfo.nextToken()));
            if (player.getOperationTypeType() == player.USE_TOOL)
            {
                if (player.useTool(landMatrix) == false)
                {
                    System.out.println("You cant use " + player.getTool().getToolName() + "!");
                }
            }
        }
    }

    public void setButtons(JButton[][] landMatrixBtns, JButton[] seedBtns, JButton[] toolBtns)
    {
        this.landMatrixBtns = landMatrixBtns;
        this.seedBtns = seedBtns;
        this.toolBtns = toolBtns;

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


    public int getWidthLand()
    {
        return game.getXSize();
    }

    public int getHeightLand()
    {
        return game.getYSize();
    }

    public String getCodeLand()
    {
        return CODE_LAND;
    }
    public String getCodeTool()
    {
        return CODE_TOOL;
    }
    public String getCodeSeed()
    {
        return CODE_SEED;
    }
}
