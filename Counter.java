
class Counter{
    //initializes global variables 
    //new variables will need to be implemented for data saving (must import from txt file?)
    public static double difficulty;
    public static double strength;
    public static int kills;
    public static int bossKills;
    public static int stages;
    public static int rooms;

    public Counter(){
        this(1.0,1.0,0,0,0,0);
    }

    public Counter(double diff, double str, int kill, int bossKill, int stage, int room){
        difficulty = diff;
        strength = str;
        kills = kill;
        bossKills = bossKill;
        stages = stage;
        rooms = room;
    }


    
}
