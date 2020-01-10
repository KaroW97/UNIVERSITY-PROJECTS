package Pikapika;

public class Pokemons extends Entity {

    private final int INITIAL_X = 400;

    public Pokemons(int x, int y) {
        super(x, y);

        initPokemon();
    }

    private void initPokemon() {

        loadImage("res/textures/pokemon.png");
        getImageDimensions();
    }

    public void move() {
        //jesli pozycja pokemona < 0  ustaw go na 400
        if (x < 0) {
            x = INITIAL_X;
        }
        //ruszaj sie w lewo
        x-=2;


    }
}
