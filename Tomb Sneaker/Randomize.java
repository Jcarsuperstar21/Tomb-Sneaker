public class Randomize {
//returns a random integer between min and max inclusive
    public int getRandomInclusive(int min, int max){
        int range = max - min + 1;
        int johnson = (int)(Math.random()*range) + min;
        return johnson;
    }
//returns a random double out to two decimal places between min and max inclusive
    public double getRandomInclusive(double min, double max){
        int minny = (int)(min * 100);
        double range1 = ((max * 100) - (min * 100) + 1);
        double rangeDec = range1 % 1;
        int rangeFinal = (int)(range1 - rangeDec);
        int glumpus = (int)(Math.random()*rangeFinal) + minny;
        double grust = glumpus / 100.0;
        return grust;
        
    }
}

