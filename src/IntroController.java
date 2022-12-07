public class IntroController {

    private int loadValue = 0;

    public IntroController(String GAME_TITLE){

        // LOADING SCREEN
        IntroWindow introWindow = new IntroWindow(GAME_TITLE);
        introWindow.frame.setVisible(true);
        try{
            for (int i = 0; i <= 100; i++){
                //Thread.sleep(50);// loading speed
                Thread.sleep(10);// for testing
                introWindow.bar.setValue(i);
                loadValue = i;
            }

            if (loadValue == 100){
                introWindow.frame.dispose();
            }

        } catch (Exception e){
        }

    }
}
