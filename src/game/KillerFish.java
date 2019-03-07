package game;
public class KillerFish extends Fish{
    public KillerFish(String name,int x,int y){
       super(name,x,y);
    }
    @Override
    public void run(){
        GridLocation gl=Game.gamecontroller.getTheLocation(this.getXCoordinate(),this.getYCoordinate()); //get its location
        Warrior w=gl.checkWarrior();           //get the wrrior there
        kill(w);                               //execute kill method
    }
    public void kill(Warrior w){
      if(!w.checkImmortle()){
        w.setIsAlive(false);                   //kill the warrior
        System.out.println("KillerFish "+this.getCharacterName()+" kills "+w.getCharacterName());
      }
    }
    @Override
    public void eat(){}
}