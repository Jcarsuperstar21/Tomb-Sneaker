

public  class Knight extends Player{
    //holds a string representation of the last turn taken by the player (chargedSlash, chargedHeal only)
    String prevTurn;
    public Knight(){
        super(90,15,15,15);
        prevTurn = "";
    }
//returns the damage dealt by the attack
//basic ahh attack
    public double swordSlash(){
        System.out.println("You attempt to slash the enemy with your sword!");
        return dmg;
    }
//block 
//returns a number that represents how much damage should be reuced
    public double block(){
        return def;
    }
//returns a large damage value if this action was chosen last turn
//if the last turn was a different action, it will return zero damage and record this action as the previous action
    public double chargedSlash(){
        if(prevTurn.equals("chargedSlash")){
            System.out.println("You unleash a mighty slash with your sword!");
            prevTurn = "";
            return dmg * 3;
        }else{
            System.out.println("You begin to charge up a mighty slash for next turn...");
            prevTurn = "chargedSlash";
            return 0;
        }
    }
//if the last action was also chargedHeal this calls the Player.boardHeal() method with 4.0 passed as the 'difficulty' parameter
//if the last turn was a different action, it will print a string about your last action and record this action as the previous action
    public void chargedHeal(){
        if(prevTurn.equals("chargedHeal")){
            prevTurn = "";
            System.out.println("You finish your elixer and crush it on your helmet to intimidate your foe!");
            super.boardHeal(4.0);
        }else{
            System.out.println("You begin to drink from the crimson elixir of your healing potion...");
            prevTurn = "chargedHeal";
        }
    }
    
}


