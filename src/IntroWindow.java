import javax.swing.*;
import java.awt.*;

public class IntroWindow {
    //set as public for backend controls by IntroController
    public JFrame introFrame;
    //set as public for backend controls by IntroController
    public JProgressBar bar;
    //set as public for backend controls by IntroController
    public JLabel lblLoad;
    private static final int frameX = 420;//frame width;
    private static final int frameY = 480;//frame Height;

    public IntroWindow(String GAME_TITLE)
    {
        introFrame = new JFrame();
        lblLoad = new JLabel();
        bar = new JProgressBar();

        //Initializing Intro Frame
        introFrame.setTitle(GAME_TITLE);
        introFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        introFrame.setLayout(new BorderLayout());
        introFrame.setResizable(false);
        introFrame.setSize(frameX, frameY);
        introFrame.setLocationRelativeTo(null);

        //Creating ImageIcon to change Icon of the window
        ImageIcon image = new ImageIcon("src/Images/Icon1.png");
        introFrame.setIconImage(image.getImage());

        //modified image for displaying on IntroWindow's frame
        Image frameicon = image.getImage().getScaledInstance(frameX -200, frameY -250, Image.SCALE_SMOOTH);

        //Initializing subframes
        JPanel iconPanel = new JPanel();
        iconPanel.setLayout(new GridBagLayout());
        JPanel barPanel = new JPanel();
        barPanel.setLayout(new GridBagLayout());

        // Main Icon at the center
        JLabel iconlbl = new JLabel();
        iconlbl.setIcon(new ImageIcon(frameicon));
        iconlbl.setHorizontalAlignment(JLabel.CENTER);
        iconPanel.add(iconlbl);

        // properties configurations
        GridBagConstraints btnProperty = new GridBagConstraints();
        btnProperty.weightx = 0.5;

        // Label of progress bar
        lblLoad.setText("Loading...");
        btnProperty.gridy = 0;
        barPanel.add(lblLoad,btnProperty);

        // Progress bar (Loading Bar)
        btnProperty.gridy = 1;
        bar.setValue(0);
        bar.setPreferredSize(new Dimension(frameX-14, 25));
        barPanel.add(bar,btnProperty);

        //add content panel to main frame
        introFrame.add(iconPanel, BorderLayout.CENTER);
        introFrame.add(barPanel, BorderLayout.SOUTH);

        //frame.setVisible(true); for testing SEPARATELY
    }
}
