import java.util.Scanner;

public class Minigame {
    
    //Plays a minigame involving remembering a sequence of random numbers and then inputing them. called by the rogue calss for 2 different attacks as of now.
    public boolean minigame(long diff) throws InterruptedException {

        //makes clear and scan objects for future use
        Clear clear = new Clear();
        Scanner scan = new Scanner(System.in);

        //makes a string variable and fills it with 5 random numbers with a for loop
        String str = "";
        for (int i = 0; i < 5; i++) {
            str += ((int) (Math.random() * 9) + 1);
        }

        //makes another string with the same value as str. Fills it with a for loop to not have the same memory reference.
        String temp = "";
        for (int i = 0; i < 5; i++) {
            temp += str.substring(i, i + 1);
        }


        System.out.println("Input the string shown after it disappears.");
        Thread.sleep(1000);
        clear.clear();

        //Next, it will print the string and keep it on screen for however long is specified in the difficulty parameter
        //it will then clear it from the screen and reprint it after it has removed the first character of the string.
        //this causes it to be printed as 12345, then 2345, 345, 45, and 5.
        for (int i = 0; i < 5; i++) {
            System.out.println(temp);
            temp = temp.substring(1);
            Thread.sleep(diff);
            clear.clear();
        }

        //Finally, it will ask you to input what the original string was. If you are correct, return true. Otherwise, return false.
        String input = "";
        input = scan.nextLine();
        if (input.equals(str)) {
            return true;
        } else {
            return false;
        }

    }
}
