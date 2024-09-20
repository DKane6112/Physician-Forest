public class AnimalFour extends Animal{
    static int seen = 0;
    static int healed = 0;
    static boolean bonusAvailable = true;
    public AnimalFour(String[] settings){
        name = settings[6];
        health = Integer.parseInt(settings[7]);
    }
}
