package Game;
import java.util.ArrayList;

public class Consumer extends Thread {                       //Observer Class, Multi-threading applied
    private Casino casino;
    private int number;
    private int struck;
    private ArrayList<Integer> card; 
 
    public Consumer(Casino c, int number) {                 //Constructor
       casino = c;
       this.number = number;
       struck=0;
       card= new ArrayList<Integer>(10);
       for(int i=0;i<10;i++){
           card.add((int)(Math.random() * 51 ));
       }
       System.out.println("Player"+ this.number +" tokens are: " +card);
    }
    
    public void Result(){
        System.out.println("The no. of matches for Player" + this.number + " is: " + struck);
        if(struck==3)
            System.out.println("Player"+ this.number + " won!!");
    }
    
    public boolean search(int val){
        for(int i=0;i<card.size();i++){
            if(val==card.get(i)){
                struck++;
                card.remove(i);         //Remove struck number from ArrayList so that it can't be struck again
                return true;
            }
        }
        return false;
    }
    
    public void run() {
       int value = 0;
       for (int i = 0; i < 10; i++) {
           if(Casino.Gameover==true){
               return;
           }
          value = casino.get();        //Although ArrayList is shared resource, players can only access the last generated number directly 
          if(value==-1) return;
          
          System.out.println("Player"+this.number);
          
          if(search(value))
              System.out.println("Player" + this.number + " has: " + value);
          if(struck==3){
              Casino.Gameover=true;
              casino.end();
          }
       }
    }
 }
 