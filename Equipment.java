
public class Equipment {
    //stores the name of the equipment in question
    public String name;
    //stores the member variables representing modifiers of player stats
    private double strMod;
    private double defMod;
    private double maxHpMod;
    private double hpRecMod;
    //these represent potential prefixes and postfixes for generating random eqiupment names
    static String[] preFixes = {"con","pre","rag","gul","sun","dry","gross","fort","fresh","phact","tace","kail"};
    static String[] postFixes = {"amulet", "ring", "sack", "crown","bracelet","necklace","neckpeice"};
    //creates a randomizer for generating nnumbers more easily (potentially could create static methods so that randomizer doesn't need to be initialized???)
    private Randomize rando = new Randomize();

    //creates an equipment object with default values of 1
    public Equipment(){
        this(1,1,1,1);
        
    }
    //creates a randomized equipment that is scaled based on the difficulty of the world 
    //NOTE: difficulty should increase by 1 for each boss stage, this will help with keeping items balanced
    public Equipment(double difficulty){
            this();
            int firstStat = rando.getRandomInclusive(1, 4);
            int secondStat = rando.getRandomInclusive(1, 4);
            //the conditional chain is for deciding which two stats should be modified
            if(secondStat == firstStat && secondStat != 4){
                secondStat += 1;
            }else if(secondStat == firstStat){
                secondStat = rando.getRandomInclusive(1, 3);
            }
            //this connditional chain determines how much each stat is modified by
            if(firstStat == 1 || secondStat == 1){
                this.strMod = rando.getRandomInclusive(1.1*difficulty, 2.1*difficulty);
            }if(firstStat == 2 || secondStat == 2){
                this.defMod = rando.getRandomInclusive(1.1*difficulty, 2.1*difficulty);
            }if(firstStat == 3 || secondStat == 3){
                this.maxHpMod = rando.getRandomInclusive(1.1*difficulty, 2.1*difficulty);
            }if(firstStat == 4 || secondStat == 4){
                this.hpRecMod = rando.getRandomInclusive(1.1*difficulty, 2.1*difficulty);
            }
    }    
    //creates an equipment object with the specified values
    public Equipment(double str, double def, double hp, double hpR){
        this.strMod = str;
        this.defMod = def;
        this.maxHpMod = hp;
        this.hpRecMod = hpR;
        name = preFixes[rando.getRandomInclusive(0,(preFixes.length - 1))] + preFixes[rando.getRandomInclusive(0,(preFixes.length - 1))] + " " + postFixes[rando.getRandomInclusive(0,(postFixes.length - 1))]; 
    }
    //getters are self explanitory
    public String getName(){
        return name;
    }
    public double getStrMod(){
        return strMod;
    }

    public double getDefMod(){
        return defMod;
    }

    public double getMaxHpMod(){
        return maxHpMod;
    }

    public double getHpRecMod(){
        return hpRecMod;
    }

    
}
