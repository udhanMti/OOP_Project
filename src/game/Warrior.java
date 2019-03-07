package game;
import java.util.*;
public abstract class Warrior extends InhabitantCharacter implements Runnable,Observer{
    private static int noOfWarriors;
    private boolean isMobile;
    private boolean isImmortal;
    private boolean isAlive;
    private boolean iswon;
    private double startTime;
    public Warrior(String name,int x,int y){
      this.setCharacterName(name);
      this.setXCoordinate(x);
      this.setYCoordinate(y);
      isMobile=true;
      isImmortal=false;
      isAlive=true;
      iswon=false;
      noOfWarriors++;
    }
    @Override
    public void run(){
       notifyTreasureChest();                      //notify treasurechest that he start the game
       startTime=System.currentTimeMillis();
       while(isMobile && isAlive){                 //swim if he is mobile and alive
        swim();
       }
    }
    @Override
    public void update(Observable o, Object arg) {
        setIsMobile(false);                         //mobility will get false when another warrior wins
    }
    public abstract void swim();
    public abstract void sleep();
    public abstract void eat();
    public double getStartTime(){return startTime;}
    public void setIsMobile(boolean value){
       isMobile=value;
    }
    public void setIsAlive(boolean value){
       isAlive=value;
    }
    public void setImmortal(boolean value){
       isImmortal=value;
    } 
    public boolean checkMobility(){
       return isMobile;
    }
    public boolean checkIsAlive(){
       return isAlive;
    }
    public boolean checkImmortle(){
       return isImmortal;
    }
    public boolean checkIswon(){
       return iswon;
    }
    public void looseFins(){
      this.setIsMobile(false);
    }
    public static int getNoOfWarriors(){
      return noOfWarriors;
    }
    public void notifyTreasureChest(){                 
      Game.tChest.getNotified(this);       //notify treasurechest that he start the game
    }
}