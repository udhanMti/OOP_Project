package game;
import java.util.Random;
public class SuperWarrior extends Warrior {
    public SuperWarrior(String name,int x,int y){
        super(name,x,y);
    }
    @Override
    public void sleep(){System.out.println("SuperWarrior "+this.getCharacterName()+" sleeps");}
    @Override
    public void eat(){System.out.println("SuperWarrior "+this.getCharacterName()+" eats");}
    @Override
    public void swim(){
       int previousXcoordinate=getXCoordinate();
       int previousYcoordinate=getYCoordinate();
       Random ran=new Random();
       int[] newCoordinates=new int[2];
       int expectedNewX=-1;
       int expectedNewY=-1;
       if(!this.checkImmortle()){
          int[] expectedNewCoordinates=lookForLotus(Game.gamecontroller);     //first check whether there are lotus flowers in adjacent locations
          if(expectedNewCoordinates!=null){                    //if lotus are there then try to move to that location
            expectedNewX=expectedNewCoordinates[0];
            expectedNewY=expectedNewCoordinates[1];
          }
       }                
       //if there are no lotus in adjacent locations then swim like a normal warrior
       do{
           if((expectedNewX>=0)&&(expectedNewX<11)&&(expectedNewY>=0)&&(expectedNewY<11)){
                GridLocation expectedLocation=Game.gamecontroller.getTheLocation(expectedNewX,expectedNewY);
                if(expectedLocation.checkWarrior()==null){      //check whether the new location is already occupied by an other warrior or not
                  setXCoordinate(expectedNewX);           //set the new coordinates if new location is vacant
                  setYCoordinate(expectedNewY);
                  newCoordinates[0]=expectedNewX;
                  newCoordinates[1]=expectedNewY;
                  break;
                }
            }
            int randomInt=ran.nextInt(2);                        //choose the ccoordinate that is going to be changed(either x or y)
            int[] possibleMoves={-1,1};
            if(randomInt==0){                                    //change only the x coordinate
              int nextChange=possibleMoves[ran.nextInt(2)];     //choose whether warrior is going to his left or right
              expectedNewX=this.getXCoordinate()+nextChange;
              expectedNewY=this.getYCoordinate();
            }else{                                               //change only the y coordinate
              int nextChange=possibleMoves[ran.nextInt(2)];     //choose whether warrior is going up or down
              expectedNewY=this.getYCoordinate()+nextChange;
              expectedNewX=this.getXCoordinate();
            }
       }while(true);
       
       Game.gamecontroller.tranferTheWarrior(this, previousXcoordinate, previousYcoordinate, newCoordinates[0], newCoordinates[1]); //transfer warrior from old location to new location
       Game.gamecontroller.checkTheNewLocation(this);   //check the new location
    }
    public int[] lookForLotus(GameController gc){                //find are there any lotus flowers in adjacent locations
        int currentX=this.getXCoordinate();
        int currentY=this.getYCoordinate();
        int[][] coordinateSet={{currentX-1,currentY},{currentX+1,currentY},{currentX,currentY-1},{currentX,currentY+1}};
        for(int i=0;i<4;i++){
          int[] coordinates=coordinateSet[i];
          if((coordinates[0]>=0)&&(coordinates[0]<11)&&(coordinates[1]>=0)&&(coordinates[1]<11)){
             if(gc.getTheLocation(coordinates[0],coordinates[1]).checkForLotus()){
                this.setImmortal(true);
                return coordinates;                      //if there exist lotus then return that coordinates
             }
          }
        }
        return null;
   }
}