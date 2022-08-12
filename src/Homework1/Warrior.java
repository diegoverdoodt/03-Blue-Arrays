package Homework1;

public class Warrior extends Character implements Attacker {

    private int stamina, strength;

    public static final int HEAVYATTACKLESSSTAMINA = 5;
    public static final int WEAKATTACKMORESTAMINA = 1;


    public Warrior(int id, String name, int hp, boolean isAlive, int stamina, int strength) {
        super(id, name, hp, isAlive);
        setStamina(stamina);
        setStrength(strength);
        type = "Warrior";
    }


    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getStrength() {
        return strength;
    }

    @Override
    public int getStaminaMana(){
        return stamina;
    }

    @Override
    public int getStrengthIntelligence(){
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;

    }
    public void attack(Warrior warrior, Character character) {
        if(warrior.getStamina() >= 5){
            character.setHp(character.getHp()-warrior.getStrength());
            warrior.setStamina(warrior.getStamina()-HEAVYATTACKLESSSTAMINA);
            pause();
            System.out.println(warrior.getName() + " lanza un ataque terrible. Le quita a su oponente " + warrior.getStrength() + " puntos de vida");
            pause();
        } else if (warrior.getStamina() < 5) {
            character.setHp(character.getHp()-warrior.getStrength()/2);
            warrior.setStamina(warrior.getStamina()+WEAKATTACKMORESTAMINA);
            pause();
            System.out.println(warrior.getName() + " lanza un ataque debil. Esta cansado. Le quita a su oponente " + warrior.getStrength()/2 + " puntos de vida ");
            pause();
        }
    }
}

