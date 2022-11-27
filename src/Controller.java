import Entities.GameEnvironment;
import Entities.Player;
import Entities.Land;

import java.awt.*;
import java.awt.event.*;

public class Controller implements ActionListener {
    Player player;
    GameEnvironment game;

    public Controller(Player player, Land[][] landMatrix,GameEnvironment game)
    {
        this.player = player;
        this.game = game;
    }

    public int getWidthLand()
    {
        return game.getXSize();
    }

    public int getHeightLand()
    {
        return game.getYSize();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}
