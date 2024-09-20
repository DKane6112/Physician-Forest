public class AnimalFive extends Animal{
    static int seen = 0;
    static int healed = 0;
    public AnimalFive(String[] settings){
        name = settings[8];
        health = Integer.parseInt(settings[9]);
    }
}
