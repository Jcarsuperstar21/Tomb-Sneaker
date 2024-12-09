
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        Clear clear = new Clear();
        clear.clear();
        Counter total = new Counter();
        Board board = new Board();
        Knight jerry = new Knight();
        System.out.println("Hello, welcome to Tomb Sneaker!");
        Thread.sleep(2000);
        System.out.println("Please select your class. Input 1 for Knight, 2 for Mage, or 3 for rogue.");
        String cl = scan.nextLine();
        if (cl.equals("1")) {
            cl = "Knight";
        }
        Thread.sleep(1000);
        
        board.newRoom(total);
        System.out.println(board);
        while (true) {
        board.move(total, jerry, cl);
        System.out.print(board);
        }
        /* 
        Equipment johnson = new Equipment(total.difficulty);
        System.out.println(johnson.getName());
        System.out.println(johnson.getStrMod());
        System.out.println(johnson.getDefMod());
        System.out.println(johnson.getHpRecMod());
        System.out.println(johnson.getMaxHpMod());
         
        System.out.println("1");
        Thread.sleep(1000);
        System.out.println("2");
        Equipment johnson = new Equipment(total.difficulty);
        System.out.println(johnson.getName());
        System.out.println(johnson.getStrMod());
        System.out.println(johnson.getDefMod());
        System.out.println(johnson.getHpRecMod());
        System.out.println(johnson.getMaxHpMod());
        Equipment johnson2 = new Equipment(total.difficulty);
        System.out.println(johnson2.getName());
        System.out.println(johnson2.getStrMod());
        System.out.println(johnson2.getDefMod());
        System.out.println(johnson2.getHpRecMod());
        System.out.println(johnson2.getMaxHpMod());
        Equipment johnson3 = new Equipment(total.difficulty);
        System.out.println(johnson3.getName());
        System.out.println(johnson3.getStrMod());
        System.out.println(johnson3.getDefMod());
        System.out.println(johnson3.getHpRecMod());
        System.out.println(johnson3.getMaxHpMod());
        System.out.println(jerry);
        jerry.addItem(johnson);
        jerry.addItem(johnson2);
        jerry.addItem(johnson3);
        System.out.println(jerry.swordSlash());
        jerry.takeDmg(50, jerry.block());
        System.out.println(jerry);
        */
    }
}
