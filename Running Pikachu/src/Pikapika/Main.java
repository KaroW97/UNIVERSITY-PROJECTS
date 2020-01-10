package Pikapika;

import javax.swing.*;
import java.awt.*;
//klasa glowna
public class Main extends JFrame {

    private int FRAME_WIDTH = 400, FRAME_HEIGHT =300;

    public Main(){
        init();
    }
    public void init(){
        add(new Board());
        setTitle("Running Pikachu");
        setSize(FRAME_WIDTH,FRAME_HEIGHT);

        setLocationRelativeTo(null);
        //nie chcemy zmieniac wielkosci okienka
        setResizable(false);
        //zamykanie na X
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
                    Main main = new Main();
                    main.setVisible(true);
    }
}
