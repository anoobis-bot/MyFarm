import Entities.GameEnvironment;
import Entities.Land;
import Entities.Player;

public class IntroController
{
    /**
     * This controls the intro of the game (the loading screen) it instantiates the IntroWindow
     * @param player player instance
     * @param landMatrix landMatrix instance
     * @param game game instance
     * @param GAME_TITLE Title of the game to be displayed
     */
    public IntroController(Player player, Land[][] landMatrix, GameEnvironment game, String GAME_TITLE)
    {
        int loadValue = 0;
        // LOADING SCREEN Window rendering
        IntroWindow introWindow = new IntroWindow(GAME_TITLE);
        introWindow.introFrame.setVisible(true);

        // JProgressbar update for loading view
        try{
            for (int i = 0; i <= 100; i++){
                Thread.sleep(70);// loading speed
                //Thread.sleep(10);// for speed testing

                // Label updates according to i'th value
                switch (i){
                    case 10 -> introWindow.lblLoad.setText("Fetching Database...");
                    case 30 -> introWindow.lblLoad.setText("Almost there...");
                    case 40 -> introWindow.lblLoad.setText("Program Created by...");
                    case 60 -> introWindow.lblLoad.setText("Francis De Leon and...");
                    case 80 -> introWindow.lblLoad.setText("Mark Abergos...");
                    case 98 -> introWindow.lblLoad.setText("Enjoy!");
                }
                introWindow.bar.setValue(i);
                loadValue = i;
            }
            if (loadValue == 100){
                introWindow.introFrame.dispose(); //closes Loading window
                // after loading successfully, the main game starts
                new Controller(player, landMatrix, game, GAME_TITLE);

            }
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }
}
