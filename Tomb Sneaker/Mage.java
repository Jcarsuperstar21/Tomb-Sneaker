public class Mage extends Player{
    //stores a string representation of the previous turn (wildBullet, healingSpell only)
    String prevTurn;
    Randomize gamble = new Randomize();
    //creates a default mage object
    public Mage(){
        super(50,20,15,10);
        prevTurn = "";
    }
    //returns base damage and prints flavortext about the attack
    public double fireBall(){
        System.out.println("You hurl a sphere of arcane flame towards your foe!");
        return dmg;
    }
    //returns how much damage an incoming attack should be reduced by
    public double shieldSpell(){
        return def;
    }
    //returns a large damage value if this action was chosen last turn
    //if the last turn was a different action, it will return zero damage and record this action as the previous action
    public double wildBullet(){
        System.out.println("You utter an arcane command, forcing chaotic energy to engulf your enemy");
        if(gamble.getRandomInclusive(1, 2) == 1){
            System.out.println("It reels back in horror and unleashes a shriek of agony");
            prevTurn = "";
            return dmg * 3;
        }else{
            System.out.println("It seems unharmed by your spell, if anything it's skin looks a little clearer...");
            return 0;
        }
    }
    //if the last action was also healingSpell this calls the Player.boardHeal() method with 5.0 passed as the 'difficulty' parameter
    //if the last turn was a different action, it will print a string about your last action and record this action as the previous action
    public void healingSpell(){
        if(prevTurn.equals("healingSpell")){
            prevTurn = "";
            System.out.println("The symbols dissipate and you feel a renewed sense of vigor!");
            super.boardHeal(5.0);
        }else{
            System.out.println("You trace several arcane symbols into the air an begin to channnel healing energy into yourself...");
            prevTurn = "healingSpell";
        }
    }

}