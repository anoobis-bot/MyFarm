import javax.swing.*;
import java.awt.*;

public class IntroWindow {
    //IntroController introController = new IntroController();
    public JFrame frame = new JFrame();
    public JProgressBar bar = new JProgressBar();
    private static final int frameX = 420;//frame width;
    private static final int frameY = 480;//frame Height;

    public IntroWindow(String GAME_TITLE)
    {
        //Initializing main frame
        frame.setTitle(GAME_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setSize(frameX, frameY);
        frame.setLocationRelativeTo(null);

        //Creating ImageIcon to change Icon of the window
        ImageIcon image = new ImageIcon("src/Images/Icon1.png");
        frame.setIconImage(image.getImage());
        //modified image for displaying on IntroWindow's frame
        Image frameicon = image.getImage().getScaledInstance(frameX -200, frameY -250, Image.SCALE_SMOOTH);

        //Initializing subframes
        JPanel iconPanel = new JPanel();
        iconPanel.setLayout(new GridBagLayout());
        JPanel barPanel = new JPanel();
        barPanel.setLayout(new GridBagLayout());

        //icon
        JLabel iconlbl = new JLabel();
        iconlbl.setIcon(new ImageIcon(frameicon));
        iconlbl.setHorizontalAlignment(JLabel.CENTER);
        iconPanel.add(iconlbl);

        // Button properties configurations
        GridBagConstraints btnProperty = new GridBagConstraints();
        btnProperty.gridy = 0;
        btnProperty.weightx = 0.5;

        // Progress bar (Loading Bar)
        btnProperty.gridy = 2;
        bar.setValue(0);
        //bar.setSize(frameX,15);
        bar.setPreferredSize(new Dimension(frameX-14, 25));
        barPanel.add(bar);


        //add content panel to main frame
        frame.add(iconPanel, BorderLayout.CENTER);
        frame.add(barPanel, BorderLayout.SOUTH);

        //frame.setVisible(true);
    }
}
