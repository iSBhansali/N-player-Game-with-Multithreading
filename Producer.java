package Game;
import java.util.ArrayList;
public class Producer extends Thread {
   private Casino casino;
   public Producer(Casino c) {
      casino = c;
      System.out.println("Moderator started");
   } 
   public void run() {
      for (int i = 0; i < 10; i++) {
    	 if(Casino.Gameover==true){
    		  return;
    	 }
    	 int val = (int)(Math.random() * 51 );
         casino.put(val);

         try {
            sleep(1000);
         } catch (InterruptedException e) { }
      } 
   }
} 
