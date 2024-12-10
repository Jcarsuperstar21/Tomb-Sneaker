import java.util.Scanner;

public class Board {
    //initializes a 10x30 square grid that will become the map
    private String[][] board = new String[10][30];
    private Scanner scan = new Scanner(System.in);
    Maps maps = new Maps();

    //constructor that makes the entire board 2d array spaces by iterating through the entire thing with a nested for loop
    public Board() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = " ";
            }
        }
    }


    //returns a string of the entire board as a nicely formatted string by iterating through the entire thing with a nested for loop
    public String toString() {
        String x = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                x += (board[i][j]);
            }
            x += ("\n");
        }
        return x;
    }

    //move method where all other methods are called depending on what is input. 
    public void move(Counter total, Player jerry, String cl) throws InterruptedException {
        Clear clear = new Clear();

        //first, it will ask the player what direction they wish to move.
        String input = "";
        System.out.println("Enter w, a, s, or d to move.");
        input = scan.nextLine();
        clear.clear();
        int startingv = 5;
        int startingh = 5;

        //then, it will search the board with a nested for loop for "P", the player, and set the starting verticle and horizontal coordinates to the coordinates of the player
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals("P")) {
                    startingv = i;
                    startingh = j;
                }
            }
        }

        //then, making the ending coordinates the same as the starting ones.
        int endingv = startingv;
        int endingh = startingh;

        //then, depending on what is input, it will alter the ending coordinates to whatever is input. For example, if D is input, it will increase the horizontal value by 1.
        if (input.equals("s")) {
            endingv = startingv + 1;
        }
        else if (input.equals("w")) {
            endingv = startingv - 1;
        }
        else if (input.equals("a")) {
            endingh = startingh - 1;
        }
        else if (input.equals("d")) {
            endingh = startingh + 1;
        }

        //Lastly, it will check what the tile you wish to move to (the ending coordinates) are, and do different things depending on what they are. this is where almost every other method is called from.

        //if the ending coordinates are a blank square, it will make the starting coordinate tile blank, and then set the ending coordinates to a "P", or the player.
        if (board[endingv][endingh].equals(" ")) {
        board[startingv][startingh] = " ";
        board[endingv][endingh] = "P";

        //If the ending coords are a aski square, or a wall, it will simply do nothing.
        } else if (board[endingv][endingh].equals("■")) {

            //if the ending coords are a D, or a door, it will run the newRoom method to generate a new room.
        } else if (board[endingv][endingh].equals("D")) {
            newRoom(total);

            //if the ending coors are a w, or a grass, it will roll a random number 1 through 5. if it is 5, start the combat method corosponding to your class. Then, do the same thing as you would a blank square.
        } else if (board[endingv][endingh].equals("w")) {
            int seed = (int) (Math.random() * 5) + 1;
            if (seed == 5) {
                if (cl.equals("Knight")) {
                    knightCombat((Knight)jerry, total);
                } else if(cl.equals("Mage")) {
                    mageCombat((Mage) jerry, total);
                } else if(cl.equals("Rogue")) {
                    rogueCombat((Rogue) jerry, total);
                }
            }
            board[startingv][startingh] = " ";
            board[endingv][endingh] = "P";

            //If the ending coords are a C, or a chest, create a new equipment object, list out its stats, and add it to the player. Then, do what you do for a blank tile.
        } else if (board[endingv][endingh].equals("C")) {
            Equipment johnson = new Equipment(total.difficulty);
            jerry.addItem(johnson);
            System.out.println("Name:" +johnson.getName());
            System.out.println("Damage Mod: " +johnson.getStrMod());
            System.out.println("Defense Mod: " +johnson.getDefMod());
            System.out.println("Hp Recovery Mod: " +johnson.getHpRecMod());
            System.out.println("Max Hp Mod: " +johnson.getMaxHpMod());
            Thread.sleep(3000);
            board[startingv][startingh] = " ";
            board[endingv][endingh] = "P";

            //if the ending coords are an H, or a heal, call the boardHeal method to heal the player based on the difficulty parameter. then, do the same thing as a blank tile
        } else if (board[endingv][endingh].equals("H")) {
            jerry.boardHeal(total.difficulty);
            Thread.sleep(3000);
            board[startingv][startingh] = " ";
            board[endingv][endingh] = "P";
        }
        
    }

    //method to change the room to a new one when you enter a door.
    public void newRoom(Counter total) {

        //makes a random number 1-5 for use in a moment.
        int seed = (int) (Math.random() * 5) + 1;
        //makes a temp map the same size of a regular one. This will also be used in just a moment.
        String[][] tempMap = new String[10][30];

        //increments the rooms amount from the counter class, and if the rooms value is even (every other call of the method), increment the difficulty from the counter class.
        total.rooms++;
        if (total.rooms % 2 == 0) {
            total.difficulty++;
        }

        //depending on the random number from earlier, choose a map from the maps class to make the temp array equal to.
        if (seed == 1) {
            tempMap = maps.getMap1();
        } else if (seed == 2) {
            tempMap = maps.getMap2();
        } else if (seed == 3) {
            tempMap = maps.getMap3();
        } else if (seed == 4) {
            tempMap = maps.getMap4();
        } else if (seed == 5) {
            tempMap = maps.getMap5();
        }

        //next, iterate throught the board 2d array and set each value to the one of the same coordinate from the temp array.
        //This is done because if we simply set the value of the board to that of the map gotten from the maps class, they will share a memory reference.
        //this will then cause the original version of the room stay altered when you leave it, and if you come back to the same map it will be in the status you last left it in.
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = tempMap[i][j];
            }
        }
    }


    //method for combat when you are a knight.
    //NOTE PLEASE READ: both the mage and rogue combat methods are nearly the exact same as the knight method, and it is incredibly inefficient (we know). This will be most likely changed before PVSEF.
    //due to this, the other methods have almost no comments (because they would be the exact same)
    public void knightCombat(Knight player, Counter total) throws InterruptedException {

        //make a randomizer object for later use
        Randomize rand = new Randomize();

        //Assign the name variable to a random name depending on the random number rolled
        String name = "";
        int seed = rand.getRandomInclusive(1, 4);
        if (seed == 1) {
            name = "Skeleton";
        } else if (seed == 2) {
            name = "Spitter";
        } else if (seed == 3) {
            name = "Zombie";
        } else {
            name = "Golem";
        }
        System.out.println("You are suddenly stopped as a " + name + " blocks your path!");
        Thread.sleep(2000);

        //then, create a new entity with the parameter of the name created before. Also make a scanner object for future use.
        Entity enemy = new Entity(name, total.difficulty);
        Scanner scan = new Scanner(System.in);

        //boolean value to track if you are currently blocking. Called bulking instead of blocking for no particular reason.
        boolean bulking = false;

        //while loop that continues to have you and the enemy take turns untill the enemy health falls below 0.
         while (enemy.currentHp > 0) {

                //asks for input on what move you wish to do, and does that move depending on input. Explanations for how they work are mostly in their respective classes.
                System.out.println("input 1 for sword slash, 2 for block, 3 for charged slash (2 turns), or 4 for healing potion (2 turns)");
                String input = scan.nextLine();
                Thread.sleep(1000);

                //make the enemy take the amount of damage returned by the player sword slash method.
                if (input.equals("1")) {
                    enemy.takeDmg(player.swordSlash());

                    //sets blocking to true to block the next attack if you input to block
                } else if(input.equals("2")) {
                    System.out.println("You put up your sheild in an attempt to block the next attack!");
                    bulking = true;

                    //explanation for this present in knight class
                } else if (input.equals("3")) {
                    enemy.takeDmg(player.chargedSlash());

                    //same as charged slash (see knight class)
                } else if (input.equals("4")) {
                    player.chargedHeal();

                    //If you do not input 1, 2, 3, or 4, do a sword slash by default
                } else {
                    enemy.takeDmg(player.swordSlash());
                }
                Thread.sleep(1000);

                //break from the loop if the enemy dies
                if (enemy.currentHp < 0) {
                    break;
                }
                 
                //chooses what the enemy will do on their turn.
                enemy.chooseTurn();

                //if they are not blocking, (attacking)
                if (! enemy.isBlocking()) {

                    //if you are blocking yourself, take damage minus what you would block from blocking
                    if (bulking) {
                        System.out.println("The enemy tries to hit you with a basic attack!");
                        System.out.println("Due to you guarding previously, you will take less damage!");
                        player.takeDmg(enemy.attack(), player.block());

                    //if you arent blocking, take the amount of damage the enemy deals
                    } else {
                        System.out.println("The enemy tries to hit you with a basic attack!");
                        player.takeDmg(enemy.attack(), 0);
                    }
                }

                //checks if the player has died from the enemy's attack. If they have, tell them and force end the program.
                if (player.currentHealth <= 0) {
                    Thread.sleep(1000);
                    System.out.println("Your health has fallen to or below 0, and you have perished!");
                    Thread.sleep(5000);
                    System.exit(0);
                }
            }

            //end of method after enemy hp has fallen to 0 and the while loop has ended
            System.out.println("You have defeated the enemy standing in your path!");
            Thread.sleep(2000);
         }
    

         //see note above knight class. Effectively the same, but it calls different methods and has different flavor text.
         public void mageCombat(Mage player, Counter total) throws InterruptedException {
            Randomize rand = new Randomize();
            String name = "";
            int seed = rand.getRandomInclusive(1, 4);
            if (seed == 1) {
                name = "Skeleton";
            } else if (seed == 2) {
                name = "Spitter";
            } else if (seed == 3) {
                name = "Zombie";
            } else {
                name = "Golem";
            }
            System.out.println("You are suddenly stopped as a " + name + " blocks your path!");
            Thread.sleep(2000);
            Entity enemy = new Entity(name, total.difficulty);
            Scanner scan = new Scanner(System.in);
            boolean bulking = false;
             while (enemy.currentHp > 0) {
                    System.out.println("input 1 for fireball, 2 for sheild spell, 3 wild bullet, or 4 for healing spell (2 turns)");
                    String input = scan.nextLine();
                    Thread.sleep(1000);
                    if (input.equals("1")) {
                        enemy.takeDmg(player.fireBall());
                    } else if(input.equals("2")) {
                        System.out.println("You utter a protective incantation as warding magic engulfs you");
                        bulking = true;
                    } else if (input.equals("3")) {
                        enemy.takeDmg(player.wildBullet());
                    } else if (input.equals("4")) {
                        player.healingSpell();
                    } else {
                        enemy.takeDmg(player.fireBall());
                    }
                    Thread.sleep(1000);
                    if (enemy.currentHp < 0) {
                        break;
                    }
                     
                    enemy.chooseTurn();
                    if (! enemy.isBlocking()) {
                        if (bulking) {
                            System.out.println("The enemy tries to hit you with a basic attack!");
                            System.out.println("Due to you guarding previously, you will take less damage!");
                            player.takeDmg(enemy.attack(), player.shieldSpell());
                        } else {
                            System.out.println("The enemy tries to hit you with a basic attack!");
                            player.takeDmg(enemy.attack(), 0);
                        }
                    }
                    if (player.currentHealth <= 0) {
                        Thread.sleep(1000);
                        System.out.println("Your health has fallen to or below 0, and you have perished!");
                        Thread.sleep(5000);
                        System.exit(0);
                    }
                }
                System.out.println("You have defeated the enemy standing in your path!");
                Thread.sleep(2000);
             }


             //see note above knight class. Most the same with different methods and flavor text.
             public void rogueCombat(Rogue player, Counter total) throws InterruptedException {
                Randomize rand = new Randomize();
                String name = "";
                int seed = rand.getRandomInclusive(1, 4);
                if (seed == 1) {
                    name = "Skeleton";
                } else if (seed == 2) {
                    name = "Spitter";
                } else if (seed == 3) {
                    name = "Zombie";
                } else {
                    name = "Golem";
                }
                System.out.println("You are suddenly stopped as a " + name + " blocks your path!");
                Thread.sleep(2000);
                Entity enemy = new Entity(name, total.difficulty);
                Scanner scan = new Scanner(System.in);
                boolean bulking = false;
                 while (enemy.currentHp > 0) {
                        System.out.println("input 1 for dagger slash, 2 for block, 3 for parry and riposte, or 4 for precision strike");
                        String input = scan.nextLine();
                        Thread.sleep(1000);
                        if (input.equals("1")) {
                            enemy.takeDmg(player.daggerSlash());
                        } else if(input.equals("2")) {
                            System.out.println("You ready your dagger to block the next attack...");
                            bulking = true;
                        } else if (input.equals("3")) {

                            //since this method both blocks and attacks, but only blocks when you dealt more than 0 damage, it can check if the damage returned from the method is > 0
                            //if it is > 0, it will also set blocking to true, and deal the damage to the enemy from the attack.
                            double damage = player.parryRipose();
                            if (damage > 0) {
                                enemy.takeDmg(damage);
                                bulking = true;
                            }
                        } else if (input.equals("4")) {
                            enemy.takeDmg(player.precisionStrike());
                        } else {
                            enemy.takeDmg(player.daggerSlash());
                        }
                        Thread.sleep(1000);
                        if (enemy.currentHp < 0) {
                            break;
                        }
                         
                        enemy.chooseTurn();
                        if (! enemy.isBlocking()) {
                            if (bulking) {
                                System.out.println("The enemy tries to hit you with a basic attack!");
                                System.out.println("Due to you guarding previously, you will take less damage!");
                                player.takeDmg(enemy.attack(), player.block());
                            } else {
                                System.out.println("The enemy tries to hit you with a basic attack!");
                                player.takeDmg(enemy.attack(), 0);
                            }
                        }
                        if (player.currentHealth <= 0) {
                            Thread.sleep(1000);
                            System.out.println("Your health has fallen to or below 0, and you have perished!");
                            Thread.sleep(5000);
                            System.exit(0);
                        }
                    }
                    System.out.println("You have defeated the enemy standing in your path!");
                    Thread.sleep(2000);
                 }
    }



