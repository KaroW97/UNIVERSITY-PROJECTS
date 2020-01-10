package Pikapika;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Board extends JPanel implements ActionListener {
    private Timer timer;
    private Pikapika pikapika;
    private List<Pokemons> pokemo;

    private boolean ingame;
    private int LIVE =3;
    private int POKEMONS = 0;

    private final int B_WIDTH = 400;
    private final int B_HEIGHT = 300;
    private final int DELAY =15;
    //positions
    private final int[][] pos = {

            {500,135},{250,160}, {700,120},
            {850,50},{550,200}, {600,90},
            {950,125},{750,60}, {1000,150},
            {1100,20},{980,60}, {1200,180},
            {1150,200},{1200,125}, {1350,220},
            {1450,20},{1500,130}, {1550,180},
            {1600,50},{1650,90}, {1750,142},
            {1850,22},{1950,60}, {2000,150},
            {2150,75},{2200,135}, {2250,190},
            {2350,180},{2550,100}, {2650,180},
            {2750,25},{2850,75}, {2900,120},
            {2950,10},{3100,120}, {3150,90},
            {3250,10},{3350,60}, {3450,190},
            {3550,160},{3650,160}, {3750,90},
            {3850,220},{3950,30}, {4100,160},
            {4250,45},{4350,200}, {4450,80},
            {4550,20},{4650,135}, {4750,120},

    };
    //initialization of the board
    public Board() {

        initBoard();
    }

    private void initBoard() {
        addKeyListener(new Tadapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        ingame = true;
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        //ustawienie pikapika a pozycji x,y
        pikapika = new Pikapika(0, 100);
        initPokemon();
        //opoznienie/spowolnienie ruchu pokemonow
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void initPokemon() {
        //lista
        pokemo = new ArrayList<>();

        for (int[] p : pos) {
            //ustawienie na pozyjach zaczytanych z pos
            pokemo.add(new Pokemons(p[0], p[1]));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //jesli ingame jest true to uruchom metode drawIbjects jak nie to koniec gry
        if (ingame) {

            drawObjects(g);

        } else {

            drawGameOver(g);
        }


    }

    private void drawObjects(Graphics g) {


        if (pikapika.isVisible() || LIVE >= 0) {
            g.drawImage(pikapika.getImage(), pikapika.getX(), pikapika.getY(),
                    this);

        }


        List<Power> powers = pikapika.getPower();

        for (Power power : powers) {
            if (power.isVisible()) {
                g.drawImage(power.getImage(), power.getX(),
                        power.getY(), this);
            }
        }
        //rysowanie jesli pokemon jest vis(domyslnie) zmienia sie na niewidoczny dopiero jesli x < 0
        for (Pokemons pokemon : pokemo) {
            if (pokemon.isVisible()) {
                g.drawImage(pokemon.getImage(), pokemon.getX(), pokemon.getY(), this);
            }
        }
        //kolor tekstu
        g.setColor(Color.WHITE);
        //wielkosc tablicy z pokemonami, odleglosc od krawędzi  , wysokosc
        g.drawString("Pokemons left: " + pokemo.size(), 5, 15);
        g.drawString("Live left: "+ LIVE,5,30);
    }

    private void drawGameOver(Graphics g) {

        String message1 = "Game Over";
        String message2 = "You have killed: "+ POKEMONS;
        String messageEncourage="";

        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.setFont(small);
        g.drawString(message1, (B_WIDTH - fm.stringWidth(message1)) / 2,
                B_HEIGHT / 2);
        g.drawString(message2, ((B_WIDTH - fm.stringWidth(message1))/3) +30 ,
                B_HEIGHT /3);
        if(POKEMONS <=4){
            messageEncourage = " It was terrible!! TRY HARDER ";
        }
        if(POKEMONS >= 5 && POKEMONS <= 14 ){
            messageEncourage = " Its not so bad, but try harder";
        }
        if(POKEMONS >= 15 && POKEMONS <= 30 ){
            messageEncourage = " You are almost there, keep going! ";
        }
        if(POKEMONS >= 15 && POKEMONS <= 30 ){
            messageEncourage = " You are almost there, keep going! ";
        }
        if(POKEMONS >= 31 && POKEMONS <= 50 ){
            messageEncourage = " So close its just one left! ";
        }
        if(POKEMONS == 51){
            messageEncourage = " You are a true hero!  ";
        }
        g.drawString(messageEncourage, ((B_WIDTH - fm.stringWidth(message1))/3 -10) ,
                B_HEIGHT/5 );
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        inGame();
        updatePikapika();
        updatePower();
        updatePokemon();
        checkCollisions();
        repaint();
    }

    private void inGame() {
        //zatrzymaj spowalnianie w momecie giety ingame jest false
        if (!ingame) {
            timer.stop();
        }
    }

    private void updatePikapika() {
        //jesli pikapika jest widoczne to ma sie poruszać
        if (pikapika.isVisible()) {

            pikapika.move();
        }
    }

    private void updatePower() {

        List<Power> powers = pikapika.getPower();

        for (int i = 0; i < powers.size(); i++) {

            Power power = powers.get(i);
            //jesli power jest nadal widoczne to poruszaj
            if (power.isVisible()) {
                power.move();
            } else {
                //jesli power wyjdzie po za określona pozycje usuń
                powers.remove(i);
            }
        }
    }

    private void updatePokemon() {
        //sprawdzamy czy talibca pokemonow jest pusta jesli tak koniec gry
        if (pokemo.isEmpty()) {

            ingame = false;
            return;
        }
            //jesli nie jet pusta przechodzimy przez liste i wlaczamy
            //metode move(), jelsli pokemon zostanie zniszczony usun z listy
            for (int i = 0; i < pokemo.size(); i++) {

                Pokemons pokemons = pokemo.get(i);

                if (pokemons.isVisible()) {
                    pokemons.move();
                } else {
                    pokemo.remove(i);

                    }
                }
            }


    public void checkCollisions() {
        //tworzymy obiekt colision box
        Rectangle pikachu = pikapika.getBounds();
        //przechodzimy po lisicie pokemonow
        for (Pokemons pokemon : pokemo) {
            //tworzymy ccolision box
            Rectangle pokemons = pokemon.getBounds();
            //jesli dojdzie do kolizji,ustaw pokemon.setVis na false i zmniejsz zycie
            if (pikachu.intersects(pokemons)) {
                pokemon.setVisible(false);
                LIVE--;
                    //gdy LIVE =0 zakoncz gre
                if(LIVE==0){

                    ingame = false;
                }
            }
        }
        //sprawdzamy czy doszło do kolizji między POWER a POKEMONEM  jesli tak inkrementujemy zabite POKEMONY

        List<Power> power = pikapika.getPower();

        for (Power pow : power) {

            Rectangle powers = pow.getBounds();

            for (Pokemons poke : pokemo) {

                Rectangle powerss = poke.getBounds();

                if (powerss.intersects(powers)) {
                    //Jesli doszlo do zdezenia power z pokemon update pokemons i
                    //wyswietl pod koniec gry
                    POKEMONS ++;

                    pow.setVisible(false);
                    poke.setVisible(false);
                }
            }
        }
    }


    private  class Tadapter extends KeyAdapter {
        //wywolanie metody z pikapika odpowiadajacej za zachowanie klwiszy
        //w zaleznisci od tego czy sa wcisniete czy tez nie
        @Override
        public void keyReleased(KeyEvent e){
            pikapika.keyReleased(e);
        }
        @Override
        public void keyPressed(KeyEvent e) {

            pikapika.keyPressed(e);
        }

    }
}
