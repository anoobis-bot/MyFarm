import javax.swing.*;
import java.awt.*;

public class GameOverWindow {

    //set as public for backend controls by GOverController
    public JFrame gameOverFrame = new JFrame();

    private static final int frameX = 420;//frame width;
    private static final int frameY = 480;//frame Height;
    private static final int PADDING_BTN_BOTTOM = 15;
    private static final int PADDING_BTN_RIGHT = 25;

    public GameOverWindow(GameOverController gameOverController)
    {
        final String CODE_RESTART = GameOverController.getCodeRestart();
        final String CODE_QUIT = GameOverController.getCodeQuit();
        //Initializing Game Over Frame
        gameOverFrame.setTitle("Game Over");
        gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameOverFrame.setLayout(new BorderLayout());
        gameOverFrame.setResizable(false);
        gameOverFrame.setSize(frameX, frameY);
        gameOverFrame.setLocationRelativeTo(null);

        //Creating ImageIcon to change Icon of the window
        ImageIcon image = new ImageIcon("src/Images/Icon1.png");
        gameOverFrame.setIconImage(image.getImage());

        //Initializing sub-frames
        JPanel gOverPanel = new JPanel();
        gOverPanel.setLayout(new GridBagLayout());
        JPanel btnsPanel = new JPanel();
        btnsPanel.setLayout(new GridBagLayout());

        // properties configurations
        GridBagConstraints btnProperty = new GridBagConstraints();
        btnProperty.gridy = 0;
        btnProperty.weightx = 0.5;

        // Game Over Label Icon
        JLabel iconGameOver = new JLabel();
        ImageIcon image2 = new ImageIcon("src/Images/GameOverIcon.png");
        Image gOverImgIcon = image2.getImage().getScaledInstance(frameX -200, frameY -250, Image.SCALE_SMOOTH);
        iconGameOver.setIcon(new ImageIcon(gOverImgIcon));
        gOverPanel.add(iconGameOver);


        // Replay Button
        JButton btnReplay = new JButton();
        btnReplay.setText("RESTART");
        btnReplay.addActionListener(gameOverController);
        btnReplay.setActionCommand(CODE_RESTART);
        btnProperty.gridx = 0;
        btnProperty.insets = new Insets(0, 0, PADDING_BTN_BOTTOM, PADDING_BTN_RIGHT);
        // btnReplay.setPreferredSize(new Dimension(WIDTH_ADVANCE, HEIGHT_ADVANCE));
        btnsPanel.add(btnReplay, btnProperty);

        // QUIT Button
        JButton btnQuit = new JButton();
        btnQuit.setText("QUIT");
        btnQuit.addActionListener(gameOverController);
        btnQuit.setActionCommand(CODE_QUIT);
        btnProperty.gridx = 1;
        btnProperty.insets = new Insets(0, 0, PADDING_BTN_BOTTOM, PADDING_BTN_RIGHT);
        // btnReplay.setPreferredSize(new Dimension(WIDTH_ADVANCE, HEIGHT_ADVANCE));
        btnsPanel.add(btnQuit, btnProperty);

        // Add Panels to Game Over Frame
        gameOverFrame.add(gOverPanel, BorderLayout.CENTER);
        gameOverFrame.add(btnsPanel, BorderLayout.SOUTH);

        //gameOverFrame.setVisible(true);
    }
}
