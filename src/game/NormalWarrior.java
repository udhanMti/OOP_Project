package game;
import java.util.*;
public class NormalWarrior extends Warrior{
    public NormalWarrior(String name,int x,int y){
      super(name,x,y);
    }
    @Override
    public void sleep(){System.out.println("NormalWarrior "+this.getCharacterName()+" sleeps");}
    @Override
    public void eat(){System.out.println("NormalWarrior "+this.getCharacterName()+" eats");}
    @Override
    public void swim(){
        int previousXcoordinate=getXCoordinate();
        int previousYcoordinate=getYCoordinate();
        Random ran=new Random();
        boolean foundNewCoordinate=false;
        int[] newCoordinates=new int[2];
        while(!foundNewCoordinate){                            //Loop will run until a new appropiate set of coordinates is found 
          int randomInt=ran.nextInt(2);                        //choose the ccoordinate that is going to be changed(either x or y)
          int[] possibleMoves={-1,1};
          int expectedNewX;
          int expectedNewY;
          if(randomInt==0){                                    //change only the x coordinate
             int nextChange=possibleMoves[ran.nextInt(2)];     //choose whether warrior is going to his left or right
             expectedNewX=getXCoordinate()+nextChange;
             expectedNewY=getYCoordinate();
          }else{                                               //change only the y coordinate
             int nextChange=possibleMoves[ran.nextInt(2)];     //choose whether warrior is going up or down
             expectedNewY=getYCoordinate()+nextChange;
             expectedNewX=getXCoordinate();
          }
          if((expectedNewX>=0)&&(expectedNewX<11)&&(expectedNewY>=0)&&(expectedNewY<11)){
                GridLocation expectedLocation=Game.gamecontroller.getTheLocation(expectedNewX,expectedNewY);
                if(expectedLocation.checkWarrior()==null){      //check whether the new location is already occupied by an other warrior or not
                  this.setXCoordinate(expectedNewX);           //set the new coordinates if new location is vacant
                  this.setYCoordinate(expectedNewY);
                  newCoordinates[0]=expectedNewX;
                  newCoordinates[1]=expectedNewY;
                  foundNewCoordinate=true;
                }
          }
         }
         Game.gamecontroller.tranferTheWarrior(this, previousXcoordinate, previousYcoordinate, newCoordinates[0], newCoordinates[1]); //transfer warrior from old location to new location
         Game.gamecontroller.checkTheNewLocation(this);  //check the new location
    }
}