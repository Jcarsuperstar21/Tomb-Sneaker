

import java.util.ArrayList;

public class Player {
    double maxHealth;
    double currentHealth;
    double dmg;
    double regen;
    double def;
    ArrayList<Equipment> items = new ArrayList<Equipment>();
//creates a player object with the indicated values
    public Player(){
        this(60,10,10,10);
    }
//creates a player object with the indicated parameters
    public Player(double hp, double dmgo, double reg, double defo){
        maxHealth = hp;
        currentHealth = maxHealth;
        dmg = dmgo;
        regen = reg;
        def = defo;
    }
//reduces the player's currentHp by 'damage' - 'dmgReduced'
//if 'dmgReduced' is greater than 'damage' currenthp will be reduced by 0 instead
    public void takeDmg(double damage, double dmgReduced){
        if(dmgReduced >= damage){
            System.out.println("You blocked all the damage! New health is " + currentHealth);
        }else{
            double dmgTaken = damage - dmgReduced;
            currentHealth -= dmgTaken;
            System.out.println("You recieved " + dmgTaken + " damage! New health is " + currentHealth);
        }
    }
//designed for healing spaces on the board.
//the current difficulty is typically passed as the 'difficulty' value
//the player will be healed by a percentage of their max hp equal to 'difficulty' times 10
    public void boardHeal(double difficulty){
        double healAmount = maxHealth *(difficulty / 10);
        currentHealth += healAmount;
        if(currentHealth > maxHealth){
            currentHealth = maxHealth;
        }
        System.out.println("You healed " + healAmount + " HP! New health is " + currentHealth);
    }
//adds an 'equipment' object to the player's list of obtained equipment (items)
    public void addItem(Equipment item){
        items.add(item);
        maxHealth += item.getMaxHpMod();
        dmg += item.getStrMod();
        def += item.getDefMod();
        regen += item.getHpRecMod();
        System.out.println("You collected " + item.getName());
    }
//Returns a string representation of the player in the following format:
/*
    Stats:
    Max Health: 'maxHealth'
    Current Health: 'currentHealth'
    Damage: 'dmg'
    Regen: 'regen'
    Defense: 'def'
    Equipment:
    (each item contained in this.items)
*/
    public String toString(){
        String output = "Stats:\nMax Health: " + maxHealth + "\nCurrent Health: " + currentHealth + "\nDamage: " + dmg + "\nRegen: " + regen + "\nDefense: " + def + "\nEquipment:\n";
        for(int i = 0; i < items.size(); i++){
            output += items.get(i).getName() + "\n";
        }
        return output;
    }
    
}


