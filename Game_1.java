package Game_1;
import java.util.*;


public class Game_1 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		AnnouncedNumber n = new AnnouncedNumber();
		Moderator m = new Moderator(n);
		Player player1 = new Player(n, 1);
		Player player2 = new Player(n, 2);
		
		Thread Moderator  = new Thread(m);
		Thread P1Thread = new Thread(player1);
		Thread P2Thread = new Thread(player2);
		
		Moderator.start();
		P1Thread.start();
		P2Thread.start();		
		
		
	}

}
