package Pikapika;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyEvent;

public class Pikapika extends Entity {

    private int yMove;
    private List<Power> power;

    public Pikapika(int x, int y) {
        super(x, y);

        init();
    }
        //inicjalizacja zdjecia oraz power
    private void init() {

        power = new ArrayList<>();
        loadImage("res/textures/pikapika.png");
        getImageDimensions();
    }

    public void move() {

        //pozliwosc poruszania sie góra dół
        y += yMove;
        //blokada przed wyjsciem poza plansze
        if (y < 1) {
            y = 1;
        }
        if(y > 220)
        {
            y=220;
        }
    }

    //zwraca liste power
    public List<Power> getPower() {
        return power;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            fire();
        }

        if (key == KeyEvent.VK_W) {
            yMove = -1;
        }

        if (key == KeyEvent.VK_S) {
            yMove = 1;
        }
    }
    //tworzymy nowy obiekt Power i dodajemy do listy
    //ustawienie x i y mocy x + width zapobiega temu by power wchodziło na pikapika
    //y + heigh/2  powoduje ze power jest na srodku pikapika
    public void fire() {
        power.add(new Power(x + width, y + height/2 ));
    }

    //Jak przestaniemy naciskac klawisz postac ma sie zatrzymac
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();


        if (key == KeyEvent.VK_W) {
            yMove = 0;
        }

        if (key == KeyEvent.VK_S) {
            yMove = 0;
        }
    }

}
