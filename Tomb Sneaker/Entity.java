
public class Entity {
//initializes member variables for the entity
private String name;
private double baseDmg;
private double maxHp;
public double currentHp;
private double dmgReduce;
private boolean blocking;
private double difficulty;
//creates a randomizer for simpler number generation
private Randomize rando = new Randomize();
//creates an object of entity with default values
public Entity(){
    this.name = "Entity";
    this.baseDmg = 3;
    this.maxHp = 15;
    this.currentHp = this.maxHp;
    this.dmgReduce = 2;
    this.difficulty = 1;
}
//creates an enntity with the given parameters
//ONLY ACCEPTED 'name' INPUTS:
/*
Skeleton
Spitter
Zombie
Golem
*/
public Entity(String name, double diff){
    this();
    this.name = name;
    this.difficulty = diff;
    if(name.equals("Skeleton")){
        baseDmg *= (5 + difficulty);
        dmgReduce *= (1 + difficulty);
        maxHp *= (.5 + difficulty);
    } else if(name.equals("Spitter")){
        baseDmg *= (2 + difficulty);
        maxHp *= (1 + difficulty);
        currentHp = maxHp;
        dmgReduce *= (0.5 + difficulty); 
    }else if(name.equals("Zombie")){
        baseDmg *= (1 + difficulty);
        maxHp *= (3 + difficulty);
        currentHp = maxHp;
        dmgReduce *= (1 + difficulty);  
    }else if(name.equals("Golem")){
        baseDmg *= (1 + difficulty);
        maxHp *= (6 + difficulty);
        currentHp = maxHp;
        dmgReduce *= (3.5 + difficulty); 
    }
}

//randomly chooses if the entity should block or attack
//blocking will be set to 'false' if the entity decides to attack 
//blocking will be set to 'true' if the entity decides to block
public void chooseTurn(){
    if(rando.getRandomInclusive(0,3) == 1){
        System.out.println("The " + name + " gets ready to block the next attack!");
        blocking = true; // blocking
    }else{
        blocking = false; // attacking
    }
}
//returns true if the entity is blocking
//returns false if the entity is attacking
public boolean isBlocking(){
    return blocking;
}
//returns base damage dealt by an attack
public double attack(){
    return baseDmg;
}
//returns reduced damage after a block turn (could be private)
public double reduceDmg(double damageRecieved){
    if(damageRecieved - this.dmgReduce < 0) {
        return 0;
    }
    return damageRecieved - this.dmgReduce;
}
//mutates currentHp after damage is recieved
public void takeDmg(double damageRecieved){
    if(blocking == true){
        System.out.println("The enemy successfully blocked a portion of the attack!");
        System.out.println("The " + name + " took " + this.reduceDmg(damageRecieved) + " dmg!");
        currentHp -= this.reduceDmg(damageRecieved);
        System.out.println("The " + name + " now has " + currentHp + " hp!");
    }else{
        System.out.println("The " + name + " took " + damageRecieved + " dmg!");
        currentHp -= damageRecieved;
        System.out.println("The " + name + " now has " + currentHp + " hp!");
    }
}


}
