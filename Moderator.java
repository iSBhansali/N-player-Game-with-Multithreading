package Game_1;

import java.util.*;
import java.util.Random;

public class  Moderator extends Thread {
	List<Integer> MArray = new ArrayList<Integer>(); 
	
	AnnouncedNumber n;
	int an = 0;
	int totalAnum = 0;
	Moderator(AnnouncedNumber A) {
		n = A;
	}
	
	public void run() {
		synchronized(AnnouncedNumber.lock) {
			while(AnnouncedNumber.win==false&&totalAnum<10) {
				AnnouncedNumber.didAnnounce = false;
				AnnouncedNumber.playerChanceFlag[0] = false;
				AnnouncedNumber.playerChanceFlag[1] = false;
					Random rand = new Random(); 
					an = rand.nextInt(50)+1;
					MArray.add(an);
					totalAnum++;
					System.out.println("\nThe Number Announced by Moderator is " +an);
					try {
						sleep(50);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		
				AnnouncedNumber.Anum = an;

				AnnouncedNumber.didAnnounce = true;
			
				while(AnnouncedNumber.playerChanceFlag[0] && AnnouncedNumber.playerChanceFlag[1]) {
					try {
						AnnouncedNumber.lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				if(AnnouncedNumber.win){ 
					System.out.println("Winner");	
					AnnouncedNumber.p.printPlayer();
				}
				
				AnnouncedNumber.lock.notifyAll(); // If at all any player is waiting			
			}		
		}
	}
}
			
			
			
		

