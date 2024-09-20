import java.util.HashMap;
import java.util.Map;

public class Player {
    String name;
    int health = 20;
    int healSkill = 0;

    public Player(String name){
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public int getHealSkill() {
        return healSkill;
    }

    public String getName() {
        return name;
    }

    public void incHealth(){
        health++;
    }

    public void decHealth(){
        health-= 3;
    }

    public void incHealSkill(){
        healSkill++;
    }

}
