package Game;

class Game {
    public static void main(String[] args) {
        Casino c = Casino.getInstance();      //Singleton Design Pattern
        Producer p1 = new Producer(c);
        Consumer c1 = new Consumer(c, 1);
        Consumer c2 = new Consumer(c, 2);            //Decoupling: Singleton Object is passed..
        Consumer c3 = new Consumer(c, 3);
        p1.start(); 
        c1.start();                              //Any number of players can be created
        c2.start();
        c3.start();
        try {
          p1.join();							//So that main thread finishes last
          c1.join();
          c2.join();
          c3.join();
      } catch (InterruptedException e) { }
        c1.Result();
        c2.Result();
        c3.Result();
        if(Casino.Gameover==false)
            System.out.println("No one wins");
        System.out.println("Game over");
     }    
}
