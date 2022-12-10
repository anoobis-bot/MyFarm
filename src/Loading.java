import Entities.GameEnvironment;
import Entities.Land;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Loading {
    public static String SIGNAL = "---";

    public static boolean loadGameSize(GameEnvironment game)
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("sav\\land_config.txt"));
            while (!(reader.readLine()).equals(SIGNAL)) {} // throw away instructions

            String currLine;
            currLine = reader.readLine();
            int ySize = 0;
            game.setXSize(currLine.length());

            while (currLine != null)
            {
                currLine = reader.readLine();
                ySize++;
            }

            game.setYSize(ySize);

            reader.close();

            if (game.getXSize() == 0 && game.getYSize() == 0)
                return false;

            return true;
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "No file found.");
            return false;
        }


    }

    public static void loadLand(GameEnvironment game, Land[][] landMatrix)
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("sav\\land_config.txt"));
            while (!(reader.readLine()).equals(SIGNAL)) {} // throw away instructions

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
            JOptionPane.showMessageDialog(null, "No file found.");
        }
    }
}
