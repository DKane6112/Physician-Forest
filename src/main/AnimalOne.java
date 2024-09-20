public class AnimalOne extends Animal{

    static int seen = 0;
    static int healed = 0;
    static boolean bonusAvailable = true;
    public AnimalOne(String[] settings){
        name = settings[0];
        health = Integer.parseInt(settings[1]);
    }

}
