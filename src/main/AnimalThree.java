public class AnimalThree extends Animal{
    static int seen = 0;
    static int healed = 0;
    static boolean bonusAvailable = true;
    public AnimalThree(String[] settings){
        name = settings[4];
        health = Integer.parseInt(settings[5]);
    }
}
