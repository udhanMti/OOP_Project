package game;
public class RubberFish extends Fish{
    public RubberFish(String name,int x,int y){
       super(name,x,y);
    }
    @Override
    public void run(){
        GridLocation gl=Game.gamecontroller.getTheLocation(this.getXCoordinate(),this.getYCoordinate());
        Warrior w=gl.checkWarrior();    //get the wrrior there
        eatFins(w);                     //execute eatfins method
    }
    @Override
    public void eat(){}
    public void eatFins(Warrior w){
      if(w.checkIsAlive()){
       System.out.println("RubberFish "+this.getCharacterName()+" eats "+w.getCharacterName()+"'s fins");
       w.setIsMobile(false);             //eat fins
      }
    }
}