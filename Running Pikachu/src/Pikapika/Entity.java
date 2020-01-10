package Pikapika;

import javax.swing.*;
import java.awt.*;
//klasa dzielona miedzy pikapika, pokemons, power
public class Entity {
    protected int x,y;
    protected int width,height;
    protected boolean visible;
    protected Image image;

    public Entity(int x, int y){
        this.x = x;
        this.y=y;
        //domyslne ustawienei widocznosci elementow na true
        visible = true;
    }
    protected void getImageDimensions(){
        //wysokosc szerokość zdjecia
        width = image.getWidth(null);
        height = image.getHeight(null);
    }
    protected void loadImage(String path){
        //ladowanie zdjecia
        ImageIcon imageIcon = new ImageIcon(path);
        image = imageIcon.getImage();
    }
    public Image getImage(){
        return image;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

  public boolean isVisible(){
        return visible;
  }
  public void setVisible(Boolean visible){
        this.visible = visible;
  }
  //Potrzebne do kolizji, powierzchnia styku
    public Rectangle getBounds(){
        return new Rectangle(x,y,width,height);
    }



}
