package game;
import java.util.*;
public class Treasure extends Observable{
    private int xCoordinate;
    private int yCoordinate;
    public Treasure(int x,int y){
      xCoordinate=x;
      yCoordinate=y;
    }
    public int getXCoordinate(){
      return xCoordinate;
    }
    public int getYCoordinate(){
      return yCoordinate;
    }
    public void setXCoordinate(int x){
      xCoordinate=x;
    }
    public void setYCoordinate(int y){
      yCoordinate=y;
    }
    public void getNotified(Warrior w){
      this.addObserver(w);
    }
    public void anounceTheWinner(Warrior w){     //notify other warriors that there is a winner
      setChanged();
      notifyObservers(w);
    }
}