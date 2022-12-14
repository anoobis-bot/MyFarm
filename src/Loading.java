import Entities.GameEnvironment;
import Entities.Land;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class contains the static methods for loading a file and importing its contents
 */
public class Loading {
    /**
     * Signal to stop the while loop readLine() to know when the instructions for the text file is over
     */
    public static String SIGNAL = "---";

    /**
     * Getting the size (dimension) of the farm land
     * @param game game instance
     * @return true if it was able to open the file
     */
    public static boolean loadGameSize(GameEnvironment game)
    {
        int numRocks = 0;
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("sav\\land_config.txt"));
            while (!(reader.readLine()).equals(SIGNAL)) {} // throw away instructions from text file

            String currLine;
            currLine = reader.readLine();
            // If the input field is empty
            if (currLine == null)
            {
                JOptionPane.showMessageDialog(null, "Please do at least a 1x1 input size");
                return false;
            }

            int ySize = 0;
            game.setXSize(currLine.length());
            while (currLine != null)
            {
                int currLet = 0;
                while (currLet < game.getXSize())
                {
                    if (currLine.charAt(currLet) == '1')
                        numRocks++;

                    currLet++;
                }

                currLine = reader.readLine();
                ySize++;

            }

            if (numRocks < 10 || numRocks > 30)
            {
                JOptionPane.showMessageDialog(null, "Rocks must be more than 10 " +
                        "and less than 30");
                return false;
            }

            game.setYSize(ySize);
            reader.close();
            return true;

        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "No file found. " +
                    "\nPlease create a land_config.txt file at sav folder.");
            return false;
        }


    }

    /**
     * Instantiates land object inside the landMatrix[][] array. It also sets if the land should have rocks on them
     * based on the text configuration file
     * @param game game instance
     * @param landMatrix landMatrix instance
     */
    public static void loadLand(GameEnvironment game, Land[][] landMatrix)
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("sav\\land_config.txt"));
            while (!(reader.readLine()).equals(SIGNAL)) {} // throw away instructions from text file

            String currLine;
            currLine = reader.readLine();

            for (int currY = 0; currY < game.getYSize(); currY++)
            {
                for (int currX = 0; currX < game.getXSize(); currX++)
                {
                    landMatrix[currY][currX] = new Land();
                    if (currLine.charAt(currX) == '1')
                        landMatrix[currY][currX].setHasRocks(true);
                }

                currLine = reader.readLine();
            }

            reader.close();

        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "No file found. Please create a land_config.txt file at sav folder");
        }
    }
}
