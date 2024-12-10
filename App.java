


//SEE NOTE IN CLEAR CLASS



import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        //clears screen, creates objects/variables for use later 
        Scanner scan = new Scanner(System.in);
        Clear clear = new Clear();
        clear.clear();
        Counter total = new Counter();
        Board board = new Board();
        System.out.println("Hello, welcome to Tomb Sneaker!");
        Thread.sleep(2000);

        //asks if you want a tutorial, prints if you input y, and doesnt if you input anything else
        System.out.println("Would you like a tutorial? y/n");
        String tut = scan.nextLine();
        if(tut.equals("y")) {
            Thread.sleep(1000);
            System.out.println("Upon beginning the game, you will be placed onto a map.");
            Thread.sleep(1000);
            System.out.println("The ■ tiles are walls. you will not be able to walk through them.");
            Thread.sleep(1000);
            System.out.println("The W tiles are grass. Every time you walk into a grass tile, there is a chance you will start a fight with a random enemy.");
            Thread.sleep(1000);
            System.out.println("The C tiles are chests. When you walk over one, you will gain an item that will give a small stat boost.");
            Thread.sleep(1000);
            System.out.println("The H tiles are healing squares. If you walk over one, you will be healed a small amount.");
            Thread.sleep(1000);
            System.out.println("The D tiles are doors. They are you objective on overy map, and they will take you to the next room.");
            Thread.sleep(1000);
            System.out.println("When you enter a fight, you will have 4 options every turn for what you want to do. The fight ends when either you or the enemy falls to 0hp.");
            Thread.sleep(1000);
            System.out.println("You are now ready for the game. Press enter to continue.");
            tut = scan.nextLine();
        }

        //asks for what class you want to play, makes you a knight by default if your input was not 1 2 or 3
        System.out.println("Please select your class. Input 1 for Knight[easy], 2 for Mage[medium], or 3 for rogue[hard].");
        String cl = scan.nextLine();
        Player jerry;
        if (cl.equals("1")) {
            cl = "Knight";
            jerry = new Knight();
        } else if (cl.equals("2")) {
            cl = "Mage";
            jerry = new Mage();
        } else if (cl.equals("3")) {
            cl = "Rogue";
            jerry = new Rogue();
        } else {
            cl = "Knight";
            jerry = new Knight();
        }
        Thread.sleep(1000);
        

        //initializes the game board with the newRoom method, prints the board for the first time, and begins the movement loop
        board.newRoom(total);
        System.out.println(board);
        //loop that simply prints the board and does the move method. All methods and gameplay stem from the move method.
        while (true) {
        board.move(total, jerry, cl);
        System.out.print(board);
        }
    }
}
