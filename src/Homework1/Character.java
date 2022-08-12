package Homework1;

public abstract class Character implements Attacker {
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
        if (hp<1){
            setAlive(false);
            this.hp=0;
        } else {
            this.hp = hp;
        }
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
        if (isAlive = alive) {
            String vivo = "vivo";
        } else {
            String vivo = "muerto";
        }
    }

    public int getStaminaMana(){
        return value1;
    }
    public int getStrengthIntelligence(){
        return value2;
    }

    public void pause(){
        try {
            //Ponemos a "Dormir" el programa durante los ms que queremos
            Thread.sleep(500);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void attack(Character character1, Character character2){
        String type1 = character1.type.toLowerCase();
        String type2 = character2.type.toLowerCase();

        if (type1.equals("warrior")) {
            Warrior warrior1 = (Warrior) character1;
            warrior1.attack(warrior1, character2);
            {
                if (type2.equals("warrior")) {
                    Warrior warrior2 = (Warrior) character2;
                    warrior2.attack(warrior2, character1);
                } else if (type2.equals("wizard")) {
                    Wizard wizard2 = (Wizard) character2;
                    wizard2.attack(wizard2, character1);
                }
            }
        } if (type1.equals("wizard")) {
            Wizard wizard1 = (Wizard) character1;
            wizard1.attack(wizard1, character2);
            {
                if (type2.equals("warrior")) {
                    Warrior warrior2 = (Warrior) character2;
                    warrior2.attack(warrior2, character1);
                } else if (type2.equals("wizard")) {
                    Wizard wizard2 = (Wizard) character2;
                    wizard2.attack(wizard2, character1);
                }
            }
        }
    }
}
