
public class Equipment {
    public String name;
    private double strMod;
    private double defMod;
    private double maxHpMod;
    private double hpRecMod;
    private String[] preFixes = {"con","pre","rag","gul","sun","dry","gross","fort","fresh","phact","tace","kail"};
    private String[] postFixes = {"amulet", "ring", "sack", "crown","bracelet","necklace","neckpeice"};
    private Randomize rando = new Randomize();

    public Equipment(){
        this(1,1,1,1);
        
    }
    //difficulty should increase by 1 for each boss stage, this will help with keeping items balanced
    public Equipment(double difficulty){
            this();
            int firstStat = rando.getRandomInclusive(1, 4);
            int secondStat = rando.getRandomInclusive(1, 4);
            if(secondStat == firstStat && secondStat != 4){
                secondStat += 1;
            }else if(secondStat == firstStat){
                secondStat = rando.getRandomInclusive(1, 3);
            }
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

    public Equipment(double str, double def, double hp, double hpR){
        this.strMod = str;
        this.defMod = def;
        this.maxHpMod = hp;
        this.hpRecMod = hpR;
        name = preFixes[rando.getRandomInclusive(0,(preFixes.length - 1))] + preFixes[rando.getRandomInclusive(0,(preFixes.length - 1))] + " " + postFixes[rando.getRandomInclusive(0,(postFixes.length - 1))]; 
    }

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
