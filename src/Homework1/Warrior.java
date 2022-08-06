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


    public ArrayList attack(Character player1, Character player2, String P2Type) {
        ArrayList result = new ArrayList<>(2);
        Warrior w1 = (Warrior) player1;
        String type = P2Type.toLowerCase();
        if (type.equals("wizard")) {
            Wizard w2 = (Wizard) player2;
            result = fightWarWiz(w1, w2);
        }if (type.equals("warrior")){
            Warrior w2 = (Warrior) player2;
            result = fightWarWar(w1, w2);
        }
        return result;
    }

    /*public void heavyAttack(Warrior warrior,Character character){
        character.setHp(getHp()-warrior.getStrength());
        warrior.setStamina(getStamina()-HEAVYATTACKLESSSTAMINA);
    }
    public void weakAttack(Warrior warrior,Character character){
        character.setHp(getHp()-(int)warrior.getStrength()/2);
        warrior.setStamina(getStamina()+WEAKATTACKMORESTAMINA);
    }*/

    public void attack(Warrior warrior, Character character) {
        if(warrior.getStamina() >= 5){
            character.setHp(character.getHp()-warrior.getStrength());
            warrior.setStamina(warrior.getStamina()-HEAVYATTACKLESSSTAMINA);
        } if (warrior.getStamina() < 5) {
            character.setHp(character.getHp()-(int)warrior.getStrength()/2);
            warrior.setStamina(warrior.getStamina()+WEAKATTACKMORESTAMINA);
        }
    }


    public ArrayList fightWarWiz(Warrior w1, Wizard w2) {
        ArrayList result = new ArrayList<>(2);
        if (w1.getHp() > 0 & w2.getHp() > 0) {
            if (w1.getStamina() < 5) {
                if (w2.getMana() < 5) {
                    w1.setStamina(w1.getStamina() + 1);
                    w2.setMana(w2.getMana() + 1);
                    w1.setHp(w1.getHp() - 2);
                    w2.setHp(w2.getHp() - w1.getStrength() / 2);
                } else if (w2.getMana() >= 5) {
                    w1.setStamina(w1.getStamina() + 1);
                    w2.setMana(w2.getMana() - 5);
                    w1.setHp(w1.getHp() - w2.getIntelligence());
                    w2.setHp(w2.getHp() - w1.getStrength() / 2);
                }
            } else if (w1.getStamina() >= 5) {
                if (w2.getMana() < 5) {
                    w1.setStamina(w1.getStamina() - 5);
                    w2.setMana(w2.getMana() + 1);
                    w1.setHp(w1.getHp() - 2);
                    w2.setHp(w2.getHp() - w1.getStrength());
                } else if (w2.getMana() >= 5) {
                    w1.setStamina(w1.getStamina() - 5);
                    w2.setMana(w2.getMana() - 5);
                    w1.setHp(w1.getHp() - w2.getIntelligence());
                    w2.setHp(w2.getHp() - w1.getStrength());
                }
            }
        }
        result.add(w1);
        result.add(w2);
        return result;
    }
    public ArrayList fightWarWar(Warrior w1, Warrior w2) {
        ArrayList result = new ArrayList<>(2);
        if (w1.getHp() > 0 & w2.getHp() > 0) {
            if (w1.getStamina() < 5) {
                if (w2.getStamina() < 5) {
                    w1.setStamina(w1.getStamina() + 1);
                    w2.setStamina(w2.getStamina() + 1);
                    w1.setHp(w1.getHp() - w2.getStrength() / 2);
                    w2.setHp(w2.getHp() - w1.getStrength() / 2);
                } else if (w2.getStamina() >= 5) {
                    w1.setStamina(w1.getStamina() + 1);
                    w2.setStamina(w2.getStamina() - 5);
                    w1.setHp(w1.getHp() - w2.getStrength());
                    w2.setHp(w2.getHp() - w1.getStrength() / 2);
                }
            } else if (w1.getStamina() >= 5) {
                if (w2.getStamina() < 5) {
                    w1.setStamina(w1.getStamina() - 5);
                    w2.setStamina(w2.getStamina() + 1);
                    w1.setHp(w1.getHp() - w2.getStrength() / 2);
                    w2.setHp(w2.getHp() - w1.getStrength());
                } else if (w2.getStamina() >= 5) {
                    w1.setStamina(w1.getStamina() - 5);
                    w2.setStamina(w2.getStamina() - 5);
                    w1.setHp(w1.getHp() - w2.getStrength());
                    w2.setHp(w2.getHp() - w1.getStrength());
                }
            }
        }
        result.add(w1);
        result.add(w2);
        return result;
    }


}

