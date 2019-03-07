package game;
public abstract class InhabitantCharacter{
    private String characterName;
    private int currentXCoordinate;
    private int currentYCoordinate;
    public void setCharacterName(String name){
      characterName=name;
    }
    public String getCharacterName(){
      return characterName;
    }
    public int getXCoordinate(){
      return currentXCoordinate;
    }
    public int getYCoordinate(){
      return currentYCoordinate;
    }
    public void setXCoordinate(int x){
      currentXCoordinate=x;
    }
    public void setYCoordinate(int y){
      currentYCoordinate=y;
    }   
}