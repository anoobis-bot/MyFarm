import javax.swing.*;
import java.awt.*;

public class ReplayWindow {
    private static final String GAME_TITLE = "My Farm";
    public JFrame frame = new JFrame();
    private static final int PADDING_ADVANCE_BOTTOM = 50,
            HEIGHT_ADVANCE = 45,
            WIDTH_ADVANCE = 150;

    public ReplayWindow()
    {
        //Initializing main frame
        frame.setTitle(GAME_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        //frame width;
        int frameX = 420;
        //frame height;
        int frameY = 350;
        frame.setSize(frameX, frameY);
        frame.setLocationRelativeTo(null);

        //Initializing subframes
        JPanel iconPanel = new JPanel();
        iconPanel.setLayout(new GridBagLayout());
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridBagLayout());

    }
}
