package Homework1;

public abstract class Character {
    private int id, hp, value1, value2;
    private String name;
    protected boolean isAlive;
    protected String type;
    private static int num = 0;

    public Character(int id, String name, int hp, boolean isAlive) {
        setId(id);
        setName(name);
        setHp(hp);
        setAlive(isAlive);
        type = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getStaminaMana(){
        return value1;
    }
    public int getStrengthIntelligence(){
        return value2;
    }
}
