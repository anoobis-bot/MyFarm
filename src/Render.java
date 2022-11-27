import Constants.SeedAttributes;
import Constants.ToolAttributes;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/*
    This class contains the graphics code for setting up the GUI of the game. A separate class will be created
    for event handling.
 */
public class Render {
    /*
        The GUI has a mainFrame that has a BorderLayout.
        The CENTER of this layout consists of the land plot that the player interacts with
        The EAST and WEST contains the buttons for the tools and seeds
        While the NORTH and SOUTH contains player information and advancing to the next day.
        Each section of the Layout is a nested GridBagLayout
     */
    private JFrame mainFrame;
    private JPanel landPlot;
    private JPanel toolPlot;
    private JPanel seedPlot;


    // Constant values relating to the GUI
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 700;
    private static final String GAME_TITLE = "My Farm";


    // Constants needed for creating the GUI (e.g. how many land tiles)
    private final int LAND_WIDTH;
    private final int LAND_HEIGHT;
    private final int LAND_AREA;

    private static final int TOOLS_TOTAL = ToolAttributes.values().length;
    private static final int SEEDS_TOTAL = SeedAttributes.values().length;

    // Button Property Constants
    private static final int SIZE_LAND = 45;
    private static final int SIZE_TOOL = 45;
    private static final int SIZE_SEED = 45;
    private static final int PADDING_LAND = 10;
    private static final int PADDING_TOOL_LEFT = 50;
    private static final int PADDING_SEED_RIGHT = 45;


    // Static values that will be used by the Controller class
    private static final String CODE_LAND = "LAND";
    private static final String CODE_TOOL = "TOOL";
    private static final String CODE_SEED = "SEED";

    public Render(Controller controller)
    {
        // Setting up the number of column and rows of the land
        this.LAND_WIDTH = controller.getWidthLand();
        this.LAND_HEIGHT = controller.getHeightLand();
        this.LAND_AREA = LAND_WIDTH * LAND_HEIGHT;

        // Initializing main frame
        mainFrame = new JFrame(GAME_TITLE);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        mainFrame.setLocationRelativeTo(null);
        // Initializing subframes
        landPlot = new JPanel();
        landPlot.setLayout(new GridBagLayout());
        toolPlot = new JPanel();
        toolPlot.setLayout(new GridBagLayout());
        seedPlot = new JPanel();
        seedPlot.setLayout(new GridBagLayout());

        // Instantiating all the components that will be used in the game
        // LAND PLOT
        JButton[] landMatrixBtns = new JButton[LAND_AREA];
        for (int i = 0; i < LAND_AREA; i++)
        {
            // Initializing all land. Labels will be put on button property.
            landMatrixBtns[i] = new JButton();
        }

        // TOOLS
        ToolAttributes[] toolsInfo = ToolAttributes.values();
        JButton[] toolBtns = new JButton[TOOLS_TOTAL];
        for (int i = 0; i < TOOLS_TOTAL; i++)
        {
            // creates buttons having a text of the first letter of the tool
            toolBtns[i] = new JButton(String.valueOf(toolsInfo[i].firstLetter()));
            toolBtns[i].setActionCommand("TOOL" + "," + toolsInfo[i].name());
        }

        // SEEDS
        SeedAttributes[] seedsInfo = SeedAttributes.values();
        JButton[] seedBtns = new JButton[SEEDS_TOTAL];
        for (int i = 0; i < SEEDS_TOTAL; i++)
        {
            // creates buttons having a text of the first letter of the tool
            seedBtns[i] = new JButton(String.valueOf(seedsInfo[i].firstLetter()));
            seedBtns[i].setActionCommand("SEED" + "," + seedsInfo[i].name());
        }

        // The code below contains all that is necessary to design and set up the back end of the code
        // This class contains the GUI code and passes all the computation to the Controller class

        // Configuring the properties of each button
        GridBagConstraints landBtnProperty = new GridBagConstraints();
        GridBagConstraints toolBtnProperty = new GridBagConstraints();
        GridBagConstraints seedBtnProperty = new GridBagConstraints();

        // LAND
        landBtnProperty.weightx = 0;    // So that the buttons clump together
        landBtnProperty.weighty = 0;
        // External padding
        landBtnProperty.insets = new Insets(PADDING_LAND, PADDING_LAND, PADDING_LAND, PADDING_LAND);
        for (int currHeight = 0; currHeight < LAND_HEIGHT; currHeight++)
        {
            for (int currWidth = 0; currWidth < LAND_WIDTH; currWidth++)
            {
                // Set up information about the button to be used by the controller object
                landMatrixBtns[(currHeight * LAND_WIDTH) + currWidth].addActionListener(controller);
                landMatrixBtns[(currHeight * LAND_WIDTH) + currWidth].setActionCommand(CODE_LAND + "," +
                        String.valueOf(currHeight) + ","  +
                        String.valueOf(currWidth));

                // Initializing all land to be normal, L
                landMatrixBtns[(currHeight * LAND_WIDTH) + currWidth].setText(String.valueOf('L'));

                // Setting the appropriate sizes of the buttons
                landMatrixBtns[(currHeight * LAND_WIDTH) + currWidth].
                        setPreferredSize(new Dimension(SIZE_LAND, SIZE_LAND));

                // Positioning the buttons
                landBtnProperty.gridx = currWidth;
                landBtnProperty.gridy = currHeight;
                // Adding it to the panel
                landPlot.add(landMatrixBtns[(currHeight * LAND_WIDTH) + currWidth], landBtnProperty);
            }
        }

        // TOOLS
        toolBtnProperty.gridx = 0;
        toolBtnProperty.weighty = 0.5;
        toolBtnProperty.insets = new Insets(0, PADDING_TOOL_LEFT, 0, 0);
        for (int currTool = 0; currTool < TOOLS_TOTAL; currTool++)
        {
            // Set up information about the button to be used by the controller object
            toolBtns[currTool].addActionListener(controller);
            toolBtns[currTool].setActionCommand(CODE_TOOL + "," + toolsInfo[currTool].name());

            // Setting text as the first letter of the tool
            toolBtns[currTool].setText(String.valueOf(toolsInfo[currTool].firstLetter()));

            // Setting the appropriate sizes of the buttons
            toolBtns[currTool].setPreferredSize(new Dimension(SIZE_TOOL, SIZE_TOOL));

            // Positioning the buttons
            toolBtnProperty.gridy = currTool;
            // Adding it to the panel
            toolPlot.add(toolBtns[currTool], toolBtnProperty);
        }

        // SEEDS
        seedBtnProperty.gridx = 0;
        seedBtnProperty.weighty = 0.5;
        seedBtnProperty.insets = new Insets(0, 0, 0, PADDING_SEED_RIGHT);
        for (int currSeed = 0; currSeed < SEEDS_TOTAL; currSeed++)
        {
            // Set up information about the button to be used by the controller object
            seedBtns[currSeed].addActionListener(controller);
            seedBtns[currSeed].setActionCommand(CODE_SEED + "," + seedsInfo[currSeed].name());

            // Setting text as the first letter of the tool
            seedBtns[currSeed].setText(String.valueOf(seedsInfo[currSeed].firstLetter()));

            // Setting the appropriate sizes of the buttons
            seedBtns[currSeed].setPreferredSize(new Dimension(SIZE_SEED, SIZE_SEED));

            // Positioning the buttons
            seedBtnProperty.gridy = currSeed;
            // Adding it to the panel
            seedPlot.add(seedBtns[currSeed], seedBtnProperty);
        }


        // Adding all the sub frames to the mainFrame
        mainFrame.add(landPlot, BorderLayout.CENTER);
        mainFrame.add(toolPlot, BorderLayout.WEST);
        mainFrame.add(seedPlot, BorderLayout.EAST);


        mainFrame.setVisible(true);
    }
}
