public class Animal {
    String name;
    int injuryLevel;
    String injuryDescription;
    int health;

    public void setInjuryLevel(int level){
        this.injuryLevel = level;
    }

    public void setHealth(int level){
        this.health -= level;
    }

    public void setInjuryDescription(int level){
        if (level == 1)
        {
            this.injuryDescription = "near death!!!";
        }
        else if (level == 2)
        {
            this.injuryDescription = "bleeding heavily.";
        }
        else
        {
            this.injuryDescription = "limping heavily.";
        }
    }

    public String getName() {
        return name;
    }

    public String getInjuryDescription() {
        return injuryDescription;
    }

    public int getHealth() {
        return health;
    }

    public int getInjuryLevel() {
        return injuryLevel;
    }
}
