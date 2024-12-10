
//NOTE: this class clears the console through the use of windows commands. If you are not on windows, it might work, im not sure.
//worse case scenario, it will throw an execption and start the catch block. If it does do this, it will instead print a large amount of new lines, which is the closest thing i can get to clearing the console without os commands.
//this will cause some parts that involve clearing the screen to look different than intended.

import java.io.IOException;


public class Clear {


    public void clear () {    
        try {

            //checks if the os name contains windows, meaning you are on a windows machine.
            if (System.getProperty("os.name").contains("Windows"))

                //if you are, pass console clear commands
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
            
            //if an exception is thrown, instead print a bunch of new lines.
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
        }
    }
    }


