

import java.util.Scanner;
//package 
public class Board {
    private String[][] board = new String[10][30];
    private Scanner scan = new Scanner(System.in);
     Maps maps = new Maps();


    public Board() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = " ";
            }
        }
        board[5][5] = "P";
        board[9][10] = "D";
        board[6][7] = "■";
    }

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

    public void move(Counter total, Player jerry, String cl) throws InterruptedException {
        Clear clear = new Clear();
        String input = "";
        System.out.println("Enter w, a, s, or d to move.");
        input = scan.nextLine();
        clear.clear();
        int startingv = 5;
        int startingh = 5;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals("P")) {
                    startingv = i;
                    startingh = j;
                }
            }
        }
        int endingv = startingv;
        int endingh = startingh;
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
        if (board[endingv][endingh].equals(" ")) {
        board[startingv][startingh] = " ";
        board[endingv][endingh] = "P";
        } else if (board[endingv][endingh].equals("■")) {

        } else if (board[endingv][endingh].equals("D")) {
            newRoom(total);
        } else if (board[endingv][endingh].equals("w")) {
            int seed = (int) (Math.random() * 5) + 1;
            if (seed == 5) {
                if (cl.equals("Knight")) {
                    knightCombat((Knight)jerry, total);
                }
            }
            board[startingv][startingh] = " ";
            board[endingv][endingh] = "P";
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
        } else if (board[endingv][endingh].equals("H")) {
            jerry.boardHeal(total.difficulty);
            Thread.sleep(3000);
            board[startingv][startingh] = " ";
            board[endingv][endingh] = "P";
        }
        
    }


    public void newRoom(Counter total) {
        int seed = (int) (Math.random() * 5) + 1;
        String[][] tempMap = new String[10][30];
        total.rooms++;

        if (total.rooms % 3 == 0) {
            total.difficulty++;
        }

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

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = tempMap[i][j];
            }
        }
    }


    public void knightCombat(Knight player, Counter total) throws InterruptedException {
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
                System.out.println("input 1 for sword slash, 2 for block, 3 for charged slash (2 turns), or 4 for healing potion (2 turns)");
                String input = scan.nextLine();
                Thread.sleep(1000);
                if (input.equals("1")) {
                    enemy.takeDmg(player.swordSlash());
                } else if(input.equals("2")) {
                    System.out.println("You put up your sheild in an attempt to block the next attack!");
                    bulking = true;
                } else if (input.equals("3")) {
                    enemy.takeDmg(player.chargedSlash());
                } else if (input.equals("4")) {
                    player.chargedHeal();;
                } else {
                    enemy.takeDmg(player.swordSlash());
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



