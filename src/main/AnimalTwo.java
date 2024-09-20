public class AnimalTwo extends Animal{
    static int seen = 0;
    static int healed = 0;
    static boolean bonusAvailable = true;
    public AnimalTwo(String[] settings){
        name = settings[2];
        health = Integer.parseInt(settings[3]);
    }
}
