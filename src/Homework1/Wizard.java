package Homework1;

public class Wizard extends Character implements Attacker{

    private int mana, intelligence;
    public static final int FIREBALLLESSMANA = 5;
    public static final int STAFFHITMOREMANA = 1;

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

    public void attack(Wizard wizard, Character character) {
        if (wizard.getMana() >= 5) {
            character.setHp(character.getHp()-wizard.getIntelligence());
            wizard.setMana(wizard.getMana()-FIREBALLLESSMANA);
            pause();
            System.out.println(wizard.getName() + " lanza una Bola de Fuego a su oponente. Le ha quitado " + wizard.getIntelligence() + " puntos de vida.");
            pause();
        }else if (wizard.getMana() < 5) {
            character.setHp(character.getHp()- 2);
            wizard.setMana(wizard.getMana()+STAFFHITMOREMANA);
            pause();
            System.out.println(wizard.getName() + " bastonea a su oponente. Le ha quitado " + 2 + " puntos de vida.");
            pause();
        }
    }
}

