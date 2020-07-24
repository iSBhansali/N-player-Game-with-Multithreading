package Game_1;

import java.util.Arrays;
import java.util.Random;

class Player implements Runnable{
	int id;
	private int count;
	private int[] numArray = new int[10];
	private AnnouncedNumber n;
	Player(AnnouncedNumber A,int s) {
		n=A;
		id = s;
		count = 0;
		for(int i = 0;i<10;i++) {
			Random rand = new Random(); 
			numArray[i] = rand.nextInt(50)+1; 
		}
		printPlayer();
	}
	
	void printPlayer() {
		System.out.println("\nCard of Player "+id+":-");
		System.out.print(Arrays.toString(numArray));
		System.out.println("  With total Strick: "+ count);
	}

	@Override
	public void run() {
		
		synchronized (AnnouncedNumber.lock) {
			while(AnnouncedNumber.win==false){
				 
				while(!AnnouncedNumber.didAnnounce||AnnouncedNumber.playerChanceFlag[id]) {
					try {
						AnnouncedNumber.lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if(AnnouncedNumber.win==false) {					
					for(int i = 0; i<numArray.length ;i++) {
						if(AnnouncedNumber.Anum == numArray[i]) {
							numArray[i] = -1;
							count++;
							AnnouncedNumber.win = true;
							AnnouncedNumber.p = this;
							break;
						}
					
					}
					this.printPlayer();
					if(count == 3){
						AnnouncedNumber.win = true;
					}
										
					AnnouncedNumber.playerChanceFlag[id] = true;
					AnnouncedNumber.lock.notifyAll();
				}
			}
		}
		
	}
		
}
	
	
	
	

