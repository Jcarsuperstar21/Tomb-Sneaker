

import java.util.ArrayList;

public class Player {
    double maxHealth;
    double currentHealth;
    double dmg;
    double regen;
    double def;
    ArrayList<Equipment> items = new ArrayList<Equipment>();

    public Player(){
        this(60,10,10,10);
    }
    //need to add another parameter that accepts an arraylist of equipment
    //need to add a method that adds an equipment to the player
    public Player(double hp, double dmgo, double reg, double defo){
        maxHealth = hp;
        currentHealth = maxHealth;
        dmg = dmgo;
        regen = reg;
        def = defo;
    }

    public void takeDmg(double damage, double dmgReduced){
        if(dmgReduced >= damage){
            System.out.println("You blocked all the damage! New health is " + currentHealth);
        }else{
            double dmgTaken = damage - dmgReduced;
            currentHealth -= dmgTaken;
            System.out.println("You recieved " + dmgTaken + " damage! New health is " + currentHealth);
        }
    }

    public void boardHeal(double difficulty){
        double healAmount = maxHealth *(difficulty / 10);
        currentHealth += healAmount;
        if(currentHealth > maxHealth){
            currentHealth = maxHealth;
        }
        System.out.println("You healed " + healAmount + " HP! New health is " + currentHealth);
    }

    public void addItem(Equipment item){
        items.add(item);
        maxHealth += item.getMaxHpMod();
        dmg += item.getStrMod();
        def += item.getDefMod();
        regen += item.getHpRecMod();
        System.out.println("You collected " + item.getName());
    }
    
    public String toString(){
        String output = "Stats:\nMax Health: " + maxHealth + "\nCurrent Health: " + currentHealth + "\nDamage: " + dmg + "\nRegen: " + regen + "\nDefense: " + def + "\nEquipment:\n";
        for(int i = 0; i < items.size(); i++){
            output += items.get(i).getName() + "\n";
        }
        return output;
    }
    
}


