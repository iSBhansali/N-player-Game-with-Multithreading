package Game;
import java.util.ArrayList;

public class Casino {                                              // Subject Class   
    private volatile static Casino uniqInst;                //Singleton implementation
     
    private ArrayList<Integer> contents;                 //ArrayList containing generated numbers
    private int available = 0;					        //Flag/Sophomore Variable
    public static boolean Gameover;						
    
    private Casino(){								//Private Constructor
        contents= new ArrayList<Integer>(10);         //Shared Resource but only through Casino object
        Gameover=false;
    }
    
    public static Casino getInstance(){				//Create Singleton Object
        if(uniqInst==null){
            synchronized(Casino.class){
                if(uniqInst==null)
                    uniqInst= new Casino();
            }
        }
        return uniqInst;
    }
    
    public synchronized void end(){					//Ends the game
        notifyAll();
    }
    
    public synchronized int get() {                     //Gives the players the generated number
       while (available == 0 && Gameover==false) {
          try {
             wait();                                    //Intercommunication between threads
          } catch (InterruptedException e) {}
       }
       if(Gameover==true)return -1;
       available -- ;
       notifyAll();
       try {
         Thread.sleep(30);                        //So that same consumer thread doesn't retrieve the number again
     } catch (InterruptedException e) { }
       
     return contents.get(contents.size()-1);
    }
    
    private void Notify(){               //Notify the observer classes that number has been generated
        notifyAll();                   //Observer Design Pattern
    }
    
    public synchronized void put(int value) {				//Inputs the generated number from Moderator
       while (available != 0 && Gameover==false) {
          try {
             wait();
          } catch (InterruptedException e) { } 
       }
       if(Gameover==true){
           return;
       }
       contents.add(value);
       available = 3;
       System.out.println("Moderator generated: " + value);
       Notify();
    }
 }
 
