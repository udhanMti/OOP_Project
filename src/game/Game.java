package game;
import java.util.*;
public class Game {
    static GameController gamecontroller;
    static Treasure tChest;
    public static void main(String[] args) {
       ArrayList<InhabitantCharacter> Inhabitants=new ArrayList<>();         //store newly created warrior and fish objects
       Warrior[] listOfWarriors=new Warrior[4];                              //store newly created warrior objects
       gamecontroller=new GameController();                                  //make a new playground(lake)
       System.out.println("-------Let's start the game--------");
       int[][] warriorCoordinates=gamecontroller.placeWarriors();            //get random initial coordinates for warriors
       String[] warriorNames={"Chandimal","Mathews","Kusal","Malinga"};
       for(int i=0;i<4;i++){                                                 //create two normalWarriors & two superWarriors
         Warrior newWarrior;
         if(i%2==0){
           newWarrior=new SuperWarrior(warriorNames[i],warriorCoordinates[i][0],warriorCoordinates[i][1]);
         }else{
           newWarrior=new NormalWarrior(warriorNames[i],warriorCoordinates[i][0],warriorCoordinates[i][1]);
         }
         gamecontroller.addCharacters(newWarrior,newWarrior.getXCoordinate(),newWarrior.getYCoordinate());  //insert the newly created warrior into the grid
         listOfWarriors[i]=newWarrior;
         Inhabitants.add(newWarrior);
       }
       int[][][] fishAndlotusCoordinates=gamecontroller.placeFishAndLotus();
       int[][] fishCoordinates=fishAndlotusCoordinates[0];                                   //get random initial coordinates for fish
       String[] fishNames={"Kholi","Dhoni","Rohit","Jadeja","Pandya","Ashwin"};
       for(int i=0;i<6;i++){                                                                 //create fish objects
         Fish newfish;
         if(i<2){
           newfish=new KillerFish(fishNames[i],fishCoordinates[i][0],fishCoordinates[i][1]);
         }else if(i<4){
           newfish=new RubberFish(fishNames[i],fishCoordinates[i][0],fishCoordinates[i][1]);
         }else{
           newfish=new InnocentFish(fishNames[i],fishCoordinates[i][0],fishCoordinates[i][1]);
         }
         gamecontroller.addCharacters(newfish,newfish.getXCoordinate(),newfish.getYCoordinate());     //insert the newly created fish into the grid
         Inhabitants.add(newfish);
       }
       for(int i=0;i<Inhabitants.size();i++){                                                //show the names & initial coordinates of the inhabitants
         InhabitantCharacter inhabitant=Inhabitants.get(i);
         System.out.println(inhabitant.getClass().getSimpleName()+" "+inhabitant.getCharacterName()+" is at ("+inhabitant.getXCoordinate()+","+inhabitant.getYCoordinate()+")");
       }
       int[][] lotusCoordinates=fishAndlotusCoordinates[1];                                        //get random coordinates for lotus flowers
       for(int i=0;i<5;i++){
         Lotus newLotus=new Lotus(lotusCoordinates[i][0],lotusCoordinates[i][1]);
         gamecontroller.addCharacters(newLotus,newLotus.getXCoordinate(),newLotus.getYCoordinate());  //insert the newly created lotus into the grid
         System.out.println("Lotus is at ("+newLotus.getXCoordinate()+","+newLotus.getYCoordinate()+")");
       }    
       tChest=new Treasure(5,5);                                                    //create the teasure object
       gamecontroller.addCharacters(tChest,tChest.getXCoordinate(),tChest.getYCoordinate());        //insert the newly created treasure into the grid
       System.out.println("Treasure is at ("+tChest.getXCoordinate()+","+tChest.getYCoordinate()+")");
       Warrior warrior1=listOfWarriors[0];Warrior warrior2=listOfWarriors[1];Warrior warrior3=listOfWarriors[2];Warrior warrior4=listOfWarriors[3];

       Thread t1=new Thread(warrior1);                      //create warrior threads
       Thread t2=new Thread(warrior2);
       Thread t3=new Thread(warrior3);
       Thread t4=new Thread(warrior4);
       t1.start();t2.start();t3.start();t4.start();   //start warrior threads
   }
}