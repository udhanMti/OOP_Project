package game;
import java.util.*;
import java.io.*;
public class GameController {
    private GridLocation[][] grid;
    Random ran=new Random();
    public GameController(){
       grid=new GridLocation[11][11];                        //cretae the grid
       for(int i=0;i<11;i++){
         for(int j=0;j<11;j++){
           grid[i][j]=new GridLocation();                    //add GridLocation objects to each of the location of grid
         }
       }
    }
    public GridLocation getTheLocation(int xCoordinate,int yCoordinate){      //return the GridLocation object corresponding to a given list
        return grid[xCoordinate][yCoordinate];                              
    }
    public void tranferTheWarrior(Warrior w,int previouseX,int previouseY,int newX, int newY){ //transfer the warrior to new location
        GridLocation previouseLocation=this.getTheLocation(previouseX, previouseY);
        GridLocation newLocation=this.getTheLocation(newX, newY);
        newLocation.addTheWarrior(w);
        previouseLocation.removeTheWarrior();   
    }
    public void addCharacters(Object obj,int xCoordinate,int yCoordinate){   //insert character to its initial location 
        grid[xCoordinate][yCoordinate].initiallyFillTheLocation(obj);
    }
    public int[][] placeWarriors(){                                          //randomly create the initial coordinates of the warriors
      int[][] initialCoordinates=new int[4][2];
      ArrayList<int[]> possibleCoordinates=new ArrayList<>();
      for(int i=1;i<11;i++){
         int[] a={0,i};int[] b={i,0};
         possibleCoordinates.add(a);possibleCoordinates.add(b);
      }
      for(int j=0;j<4;j++){
         int temp=ran.nextInt(possibleCoordinates.size());
         int[] coordinates=possibleCoordinates.get(temp);
         possibleCoordinates.remove(temp);
         initialCoordinates[j][0]=coordinates[0];
         initialCoordinates[j][1]=coordinates[1];
      }
      return initialCoordinates;
    }
    public int[][][] placeFishAndLotus(){                    //randomly create the initial coordinates of the fish and lotus
      int[][] initialCoordinates1=new int[6][2];
      int[][] initialCoordinates2=new int[5][2];
      ArrayList<int[]> possibleCoordinates=new ArrayList<>();
      for(int i=1;i<11;i++){
         for(int j=1;j<11;j++){
            if(i==5 && j==5){continue;}
            int[] temp={i,j};
            possibleCoordinates.add(temp);
         }
      }
      for(int j=0;j<6;j++){
         int temp=ran.nextInt(possibleCoordinates.size());
         int[] coordinates=possibleCoordinates.get(temp);
         possibleCoordinates.remove(temp);
         initialCoordinates1[j][0]=coordinates[0];
         initialCoordinates1[j][1]=coordinates[1];
      }
      for(int j=0;j<5;j++){
         int temp=ran.nextInt(possibleCoordinates.size());
         int[] coordinates=possibleCoordinates.get(temp);
         possibleCoordinates.remove(temp);
         initialCoordinates2[j][0]=coordinates[0];
         initialCoordinates2[j][1]=coordinates[1];
      }
      int[][][] temp={initialCoordinates1,initialCoordinates2};
      return temp;
    }
    public void checkTheNewLocation(Warrior w){
        System.out.println(w.getCharacterName()+" moves to "+"("+w.getXCoordinate()+","+w.getYCoordinate()+")"); //print the new position on console
       
        GridLocation newGridLocation=getTheLocation(w.getXCoordinate(),w.getYCoordinate()); //get the new grid location of the warrior
        if(newGridLocation.checkForTreasure()){                                    //check whether he is win or not
            Game.tChest.anounceTheWinner(w);
            double finishingTime=System.currentTimeMillis()-w.getStartTime();
            printTheSummary(w.getCharacterName(),finishingTime);
       //   System.out.println("Congratz, "+w.getCharacterName()+" won the game !!!!!!!!!!!! -"+" (Finishing Time - "+finishingtime+" ms)");
        }
        newGridLocation.checkForFish();                       //check for any kind of fish            
        if(newGridLocation.checkForLotus()){
            if(!w.checkImmortle()){
                 w.setImmortal(true);                         //if there is a lotus then make player immortle
                 System.out.println(w.getCharacterName()+" become immortal !!!!!!!!!!!");
            }
        }
    }
    public void printTheSummary(String name,Double time){   //store the winner&time in a file
       System.out.println("Congratz, "+name+" won the game !!!!!!!!!!!! -"+" (Finishing Time - "+time+" ms)");
       try{
          FileWriter writer=new FileWriter("C:\\Users\\Udhan\\Desktop\\gameInfo.txt",true);
          BufferedWriter output=new BufferedWriter(writer);
          output.write("Winner - "+name+", Time - "+time+" ms");
          output.newLine();
          output.close();
       }catch(IOException e){
         System.out.println("Error");
       }
    }
}