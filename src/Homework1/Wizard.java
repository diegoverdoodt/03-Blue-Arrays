package Homework1;

public class Wizard extends Character implements Attacker{

    private int mana, intelligence;
    public static final int FIREBALLLESSMANA = 5;
    public static final int STAFFHITMOREMANA = 2;

    public Wizard(int id, String name, int hp, boolean isAlive, int mana, int intelligence) {
        super(id, name, hp, isAlive);
        setMana(mana);
        setIntelligence(intelligence);
        type = "Wizard";
    }


    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    @Override
    public int getStaminaMana(){
        return mana;
    }

    @Override
    public int getStrengthIntelligence(){
        return intelligence;
    }

    /*public void fireball(Wizard wizard,Character character){
        character.setHp(getHp()-wizard.getIntelligence());
        wizard.setMana(getMana()-FIREBALLLESSMANA);
    }
    public void staffHit(Wizard wizard,Character character){
        character.setHp(getHp()- 2);
        wizard.setMana(getMana()+STAFFHITMOREMANA);
    }*/




    public void attack(Wizard wizard, Character character) {
        if (wizard.getMana() >= 5) {
            character.setHp(character.getHp()-wizard.getIntelligence());
            wizard.setMana(wizard.getMana()-FIREBALLLESSMANA);
        } if (wizard.getMana() < 5) {
            character.setHp(character.getHp()- 2);
            wizard.setMana(wizard.getMana()+STAFFHITMOREMANA);
        }
    }


    /*@Override
    public ArrayList attack(Character player1, Character player2, String P2Type) {
        ArrayList result = new ArrayList<>(2);
        Wizard w1 = (Wizard) player1;
        String type = P2Type.toLowerCase();
        if (type.equals("wizard")) {
            Wizard w2 = (Wizard) player2;
            result = fightWizWiz(w1, w2);

        }if (type.equals("warrior")){
            Warrior w2 = (Warrior) player2;
            result = fightWizWar(w1, w2);
        }
        return result;
    }*/

    /*public ArrayList fightWizWiz(Wizard w1, Wizard w2) {
        ArrayList result = new ArrayList<>(2);
        if (w1.getHp() > 0 & w2.getHp() > 0) {
            if (w1.getMana() < 5) {
                if (w2.getMana() < 5) {
                    w1.setMana(w1.getMana() + 1);
                    w2.setMana(w2.getMana() + 1);
                    w1.setHp(w1.getHp() - 2);
                    w2.setHp(w2.getHp() - 2);
                } else if (w2.getMana() >= 5) {
                    w1.setMana(w1.getMana() + 1);
                    w2.setMana(w2.getMana() - 5);
                    w1.setHp(w1.getHp() - w2.getIntelligence());
                    w2.setHp(w2.getHp() - 2);
                }
            } else if (w1.getMana() >= 5) {
                if (w2.getMana() < 5) {
                    w1.setMana(w1.getMana() - 5);
                    w2.setMana(w2.getMana() + 1);
                    w1.setHp(w1.getHp() - 2);
                    w2.setHp(w2.getHp() - w1.getIntelligence());
                } else if (w2.getMana() >= 5) {
                    w1.setMana(w1.getMana() - 5);
                    w2.setMana(w2.getMana() - 5);
                    w1.setHp(w1.getHp() - w2.getIntelligence());
                    w2.setHp(w2.getHp() - w1.getIntelligence());
                }
            }
        }
        result.add(w1);
        result.add(w2);
        return result;
    }*/

    /*
    public ArrayList fightWizWar(Wizard w1, Warrior w2) {
        ArrayList result = new ArrayList<>(2);
        if (w1.getHp() > 0 & w2.getHp() > 0) {
            if (w1.getMana() < 5) {
                if (w2.getStamina() < 5) {
                    w1.setMana(w1.getMana() + 1);
                    w2.setStamina(w2.getStamina() + 1);
                    w1.setHp(w1.getHp() - w2.getStrength() / 2);
                    w2.setHp(w2.getHp() - 2);
                } else if (w2.getStamina() >= 5) {
                    w1.setMana(w1.getMana() + 1);
                    w2.setStamina(w2.getStamina() - 5);
                    w1.setHp(w1.getHp() - w2.getStrength());
                    w2.setHp(w2.getHp() - 2);
                }
            } else if (w1.getMana() >= 5) {
                if (w2.getStamina() < 5) {
                    w1.setMana(w1.getMana() - 5);
                    w2.setStamina(w2.getStamina() + 1);
                    w1.setHp(w1.getHp() - w2.getStrength() / 2);
                    w2.setHp(w2.getHp() - w1.getIntelligence());
                } else if (w2.getStamina() >= 5) {
                    w1.setMana(w1.getMana() - 5);
                    w2.setStamina(w2.getStamina() - 5);
                    w1.setHp(w1.getHp() - w2.getStrength());
                    w2.setHp(w2.getHp() - w1.getIntelligence());
                }
            }
        }
        result.add(w1);
        result.add(w2);
        return result;
    }*/
}

