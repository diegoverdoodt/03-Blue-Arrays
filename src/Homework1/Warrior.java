package Homework1;

import java.util.ArrayList;

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
            try {
                //Ponemos a "Dormir" el programa durante los ms que queremos
                Thread.sleep(2*1000);
            }
            catch (Exception e) {
                System.out.println(e);
            }
            System.out.println(warrior.getName() + " lanza un ataque terrible. Le quita a su oponente " + warrior.getStrength() + " puntos de vida");
            try {
                //Ponemos a "Dormir" el programa durante los ms que queremos
                Thread.sleep(1*1000);
            }
            catch (Exception e) {
                System.out.println(e);
            }
        } else if (warrior.getStamina() < 5) {
            character.setHp(character.getHp()-(int)warrior.getStrength()/2);
            warrior.setStamina(warrior.getStamina()+WEAKATTACKMORESTAMINA);
            try {
                //Ponemos a "Dormir" el programa durante los ms que queremos
                Thread.sleep(2*1000);
            }
            catch (Exception e) {
                System.out.println(e);
            }
            System.out.println(warrior.getName() + " lanza un ataque debil. Esta cansado. Le quita a su oponente " + (int)warrior.getStrength()/2 + " puntos de vida ");
            try {
                //Ponemos a "Dormir" el programa durante los ms que queremos
                Thread.sleep(1*1000);
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

