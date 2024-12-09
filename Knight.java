

public  class Knight extends Player{
    String prevTurn;
    public Knight(){
        super(90,15,15,15);
        prevTurn = "";
    }
//should return the damage dealt by the attack
//basic ahh attack
    public double swordSlash(){
        System.out.println("You attempt to slash the enemy with your sword!");
        return dmg;
    }
//block 
//returns a number that represents how much damage should be reuced
//be careful to avoid reducing damage below 0
//this should be called in the method for taking damage
//AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA see below
//LATER: add values to base stats of player instead of calculating every time you execute an attack of block
    public double block(){
        return def;
    }

    public double chargedSlash(){
        if(prevTurn.equals("chargedSlash")){
            System.out.println("You let out a mighty slash with your sword!");
            prevTurn = "";
            return dmg * 3;
        }else{
            System.out.println("You begin to charge up a mighty slash for next turn!");
            prevTurn = "chargedSlash";
            return 0;
        }
    }

    public void chargedHeal(){
        if(prevTurn.equals("chargedHeal")){
            prevTurn = "";
            super.boardHeal(4.0);
        }else{
            System.out.println("Your begin to drink from the crimson elixir of your healing potion!");
            prevTurn = "chargedHeal";
        }
    }
    
}


