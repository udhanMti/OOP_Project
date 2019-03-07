package game;
public abstract class Fish extends InhabitantCharacter implements Runnable{
    public Fish(String name,int x,int y){
      this.setCharacterName(name);
      this.setXCoordinate(x);
      this.setYCoordinate(y);
    }
    public void swim(){}
    public abstract void eat();
}