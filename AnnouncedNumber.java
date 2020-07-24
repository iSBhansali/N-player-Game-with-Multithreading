package Game_1;

public class AnnouncedNumber {
	public static Player p = null;
	public static int Anum = 0;
	public static boolean win = false;
	public static boolean didAnnounce = false;
	public static Object lock = new Object();
	public static boolean[] playerChanceFlag = new boolean[3];
}
