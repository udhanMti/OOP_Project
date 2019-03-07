package game;
public class GridLocation {
    private Object[] charactersOccupiedTheLocation;                  //store the inhabitants that occupied this 
    public GridLocation(){                                                    //of the grid at the moment
      charactersOccupiedTheLocation=new Object[4];
    }
    public Warrior checkWarrior(){                                   //check whether there is a warrior in this location
        Object obj=charactersOccupiedTheLocation[0];
        if(obj!=null){
           return (Warrior)obj;
        }
        return null;
    }
    public void checkForFish(){                                   //check whether there is ant kind of fish in this location
         Object obj=charactersOccupiedTheLocation[1];
         if(obj!=null){
            Thread t=new Thread((Fish)obj);
            t.start();                                            //start the thread of the fish
            try {
                 t.join();                                        //join warrior's thread to the end of the fish thread
            } catch (InterruptedException ex) {}
         }
    }
    public boolean checkForLotus(){                                             //check whether there is a lotus in this location
      Object obj=charactersOccupiedTheLocation[2];
      if(obj!=null){return true;}
      return false;
    }
    public boolean checkForTreasure(){                                          //check whether there is the treasure in this location
      Object obj=charactersOccupiedTheLocation[3];
      if(obj!=null){return true;}
      return false;   
    }
    public void initiallyFillTheLocation(Object obj){                           //add objects to grid location at the start of game
      if(obj instanceof Warrior){
         charactersOccupiedTheLocation[0]=obj;
      }else if(obj instanceof Fish){
         charactersOccupiedTheLocation[1]=obj;
      }else if(obj instanceof Lotus){
         charactersOccupiedTheLocation[2]=obj;
      }else{
         charactersOccupiedTheLocation[3]=obj;
      }
    }
    public synchronized void addTheWarrior(Warrior w){                          //add the incoming warrior to the location
      Warrior obj=(Warrior)charactersOccupiedTheLocation[0];
      while(obj!=null && w.checkMobility() && obj.checkMobility() && obj.checkIsAlive()){
          try {
              wait(1000);  //1000
          } catch (InterruptedException ex) {}
          obj=(Warrior)charactersOccupiedTheLocation[0];
      }
      charactersOccupiedTheLocation[0]=w;
    }
    public synchronized void removeTheWarrior(){                                //remove outgoing warrior from the location
      charactersOccupiedTheLocation[0]=null;
      notifyAll();
    }
}