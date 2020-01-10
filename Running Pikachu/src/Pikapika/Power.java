package Pikapika;

public class Power extends Entity {
    private final int BOARD_WIDTH = 400;
    private final int POWER_SPEED = 2;

    public Power(int x, int y) {
        super(x, y);

        init();
    }
//Ladowanie obrazka
    private void init() {

        loadImage("res/textures/power.png");
        getImageDimensions();
    }
    // Ustawienie predkosci mocy oraz ustawienie widocznosci na false jesli wyjdzie poza ramke
    public void move() {

        x += POWER_SPEED ;

        if (x > BOARD_WIDTH)
            visible = false;
    }
}
