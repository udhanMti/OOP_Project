package game;
public class Lotus{
    private int xCoordinate;
    private int yCoordinate;
    public Lotus(int x,int y){
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
}