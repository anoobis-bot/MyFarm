

import javax.swing.*;
import java.awt.*;

public class IntroWindow {
    private static final String GAME_TITLE = "My Farm";
    public JFrame frame = new JFrame();
    private static final int PADDING_ADVANCE_BOTTOM = 50,
                            HEIGHT_ADVANCE = 45,
                            WIDTH_ADVANCE = 150;

    public IntroWindow() {}

    public IntroWindow(Controller controller)
    {
        //Initializing main frame
        frame.setTitle(GAME_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        //frame width;
        int frameX = 420;
        //frame height;
        int frameY = 480;
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
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridBagLayout());

        //icon
        JLabel iconlbl = new JLabel();
        iconlbl.setIcon(new ImageIcon(frameicon));
        iconlbl.setHorizontalAlignment(JLabel.CENTER);
        iconPanel.add(iconlbl);

        // Button properties configurations
        GridBagConstraints btnProperty = new GridBagConstraints();
        btnProperty.gridy = 0;
        btnProperty.weightx = 0.5;

        // play button
        JButton btn_Play = new JButton();
        btn_Play.setText("PLAY");
        btn_Play.addActionListener(controller);

        btn_Play.setPreferredSize(new Dimension(WIDTH_ADVANCE,HEIGHT_ADVANCE));
        btnProperty.gridx = 0;
        btnProperty.anchor = GridBagConstraints.CENTER;
        btnProperty.insets = new Insets(0, 0, PADDING_ADVANCE_BOTTOM, 0);
        btnPanel.add(btn_Play, btnProperty);

        // help button
        JButton btn_Help = new JButton();
        btn_Help.setText("HELP");
        btn_Help.addActionListener(controller);

        btn_Help.setPreferredSize(new Dimension(WIDTH_ADVANCE, HEIGHT_ADVANCE));
        btnProperty.gridx = 1;
        btnProperty.anchor = GridBagConstraints.CENTER;
        btnProperty.insets = new Insets(0, 0, PADDING_ADVANCE_BOTTOM, 0);
        btnPanel.add(btn_Help, btnProperty);

        //add content panel to main frame
        frame.add(iconPanel, BorderLayout.CENTER);
        frame.add(btnPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public String getGameTitle(){return GAME_TITLE;}
}
