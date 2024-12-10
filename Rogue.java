public class Rogue extends Player {

    //create a minigame object for use later, as well as use a super constructor from the player class to fill in basic stats.
    Minigame mini = new Minigame();
    public Rogue(){
        super(60,17,15,20);
    }

    //both daggerSlash and block are effectively the same thing as the sword slash and block methods from the knight class, with altered values.
    public double daggerSlash(){
        System.out.println("You slash your opponent with your silver dagger!");
        return dmg;
    }

    public double block(){
        return def;
    }

    //returns a double of how much damage the attack dealt. In gameplay, if you succeed the minigame attached to the method, you both black and attack at the same time.
    public double parryRipose() throws InterruptedException {
        System.out.println("You get ready to parry the enemy's next attack...");
        Thread.sleep(2000);

        //calls the minigame method. If it returns true, meaning you won the minigame, do the proper attack and return full damage.
        if (mini.minigame(1500)) {
            System.out.println("You succesfully parried the enemy's attack and will take less damage next turn!");
            System.out.println("In addition, you counter attacked the enemy!");
            return dmg;

        //if you did not succeed the minigame, return 0 damage.
        } else {
            System.out.println("You failed to parry the enemies attack!");
            return 0;
        }
    }


    //Similar to the parryRipose method. If you win the minigame attached, you deal a large amount of damage. returns the amount of damge dealt. returns 0 if you fail the minigame.
    public double precisionStrike() throws InterruptedException {
        System.out.println("You prepare to let out a mighty stab at the enemy...");
        if (mini.minigame(2000)) {
            System.out.println("You hit your attack for major damage!");
            return dmg * 3;
        } else {
            System.out.println("You missed your attack!");
            return 0;
        }
    }


}
