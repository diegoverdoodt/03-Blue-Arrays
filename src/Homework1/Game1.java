package Homework1;

import java.util.*;

public class Game1 {

    /* Variables */

    private String[] namesWarriors = {"Warrior1","Warrior2","Warrior3","Warrior4","Warrior5","Warrior6","Warrior7","Warrior8","Warrior9","Warrior10"};
    private String[] namesWizards = {"Wizards1","Wizards2","Wizards3","Wizards4","Wizards5","Wizards6","Wizards7","Wizards8","Wizards9","Wizards10"};
    ArrayList team1 = new ArrayList<>();
    ArrayList team2 = new ArrayList<>();
    ArrayList cementerio = new ArrayList<>();
    int maxPlayers = 5;
    private int idx = 0;
    String[] characterType = new String[]{"warrior", "wizard"};
    int[] hpWarrior = new int[]{100, 200};
    int[] hpWizard = new int[]{50, 100};
    int[] staminaMana = new int[]{10, 50};
    int[] strength = new int[]{1, 10};
    int[] intelligence = new int[]{1, 50};

    HashMap<Integer, Character> equipo1 = new HashMap<>();
    HashMap<Integer, Character> equipo2 = new HashMap<>();
    HashMap<Integer, Character> partida = new HashMap<>();
    HashMap<Integer, Character> cementerio1 = new HashMap<>();

    /* Constructor */

    public Game1(String election) {
        election.toLowerCase();
        switch (election){
            case "cargar":
                System.out.println("Cargar el fichero CSV");
                //cargar fichero CSV
                //empezar juego
                break;
            case "random":
                System.out.println("Generando la partida random");
                //createRandom();
                int[] randPlayers = new int[]{1,maxPlayers};
                int numChar = randomMethod(randPlayers);

                equipo1 = createRandom1(1, numChar);
                equipo2 = createRandom1(2, numChar);
                playGame1(equipo1, equipo2);

                //cargar partida random
                //Empezar juego
                break;
            case "custom":
                System.out.println("Carga los valores que quieras usar");
                createCustom();
                //cargar partida custom
                //empezar juego
                break;
            case "close":
                System.exit(0);
        }
    }
    /* Metodos principales */
    // Crea partida a partir de un CSV



    // Crea partida con valores random
    public HashMap<Integer, Character> createRandom1 (int numEquipo, int numChar){
        //Se inicializa el numero de jugadores por equipo de manera random entre 0 al numero máximo deseado o por defecto
        HashMap<Integer, Character> equipo = new HashMap<>();
        for (int i = 0; i < numChar; i++){
            int id = setId(numEquipo);
            String type = randomType(characterType);
            if (type.equals("wizard")) {
                equipo.put(id, new Wizard(id, namesWizards[(int) (Math.random() * namesWizards.length)], randomMethod(hpWizard), true, randomMethod(staminaMana), randomMethod(intelligence)));
            } if (type.equals("warrior")) {
                equipo.put(id, new Warrior(id, namesWarriors[(int)(Math.random() * namesWarriors.length)], randomMethod(hpWarrior), true, randomMethod(staminaMana), randomMethod(strength)));
            }
        }
        //Printamos por pantalla los equipos.
        printList1(equipo,numEquipo);
        return equipo;
    }

    // Crea partida con valores custom ACABAR
    public void createCustom() {
        System.out.println("¿Cuantos jugadores quieres que haya en cada equipo?");
        Scanner scan = new Scanner(System.in);
        int election = scan.nextInt();
        ArrayList team1 = createTeams(election, 1);
    }

    // Metodo para jugar cada batalla segun los dos jugadores
    public HashMap<Integer, Character> batalla(HashMap batalla){
        Character jugador1 = (Character) batalla.get(0);
        Character jugador2 = (Character) batalla.get(1);
        HashMap<Integer,Character> resultadoBatalla = new HashMap<>();

        if (jugador1.getHp()>0 & jugador2.getHp()>0){

            String type1 = jugador1.type;
            String type2 = jugador2.type;





            if (jugador1.type == "warrior" & jugador2.type == "warrior") {
                Warrior warrior1 = (Warrior) jugador1;
                Warrior warrior2 = (Warrior) jugador2;
                if (warrior1.getStamina()>=5 & warrior2.getStamina()>=5){
                    warrior1.heavyAttack(warrior1, jugador2);
                    warrior2.heavyAttack(warrior2, jugador1);
                } if (warrior1.getStamina()<5 & warrior2.getStamina()>=5) {
                    warrior1.weakAttack(warrior1, jugador2);
                    warrior2.heavyAttack(warrior2, jugador1);
                } if (warrior1.getStamina()>=5 & warrior2.getStamina()<5) {
                    warrior1.heavyAttack(warrior1, jugador2);
                    warrior2.weakAttack(warrior2, jugador1);
                } if (warrior1.getStamina()<5 & warrior2.getStamina()<5) {
                    warrior1.weakAttack(warrior1, jugador2);
                    warrior2.weakAttack(warrior2, jugador1);
                }
            }
            if (jugador1.type == "warrior" & jugador2.type == "wizard"){
                Warrior warrior1 = (Warrior) jugador1;
                Wizard wizard2= (Wizard) jugador2;
                if (warrior1.getStamina()>=5 & wizard2.getMana()>=5){
                    warrior1.heavyAttack(warrior1, jugador2);
                    wizard2.fireball(wizard2, jugador1);
                } if (warrior1.getStamina()<5 & wizard2.getMana()>=5) {
                    warrior1.weakAttack(warrior1, jugador2);
                    wizard2.fireball(wizard2, jugador1);
                } if (warrior1.getStamina()>=5 & wizard2.getMana()<5) {
                    warrior1.heavyAttack(warrior1, jugador2);
                    wizard2.staffHit(wizard2, jugador1);
                } if (warrior1.getStamina()<5 & wizard2.getMana()<5) {
                    warrior1.weakAttack(warrior1, jugador2);
                    wizard2.staffHit(wizard2, jugador1);
                }
            }
            if (jugador1.type == "wizard" & jugador2.type == "wizard") {
                Wizard wizard1 = (Wizard) jugador1;
                Wizard wizard2 = (Wizard) jugador2;
                if (wizard1.getMana() >= 5 & wizard2.getMana() >= 5) {
                    wizard1.fireball(wizard1, jugador2);
                    wizard2.fireball(wizard2, jugador1);
                } if (wizard1.getMana() < 5 & wizard2.getMana() >= 5) {
                    wizard1.staffHit(wizard1, jugador2);
                    wizard2.fireball(wizard2, jugador1);
                } if (wizard1.getMana() >= 5 & wizard2.getMana() < 5) {
                    wizard1.fireball(wizard1, jugador2);
                    wizard2.staffHit(wizard2, jugador1);
                } if (wizard1.getMana() < 5 & wizard2.getMana() < 5) {
                    wizard1.staffHit(wizard1, jugador2);
                    wizard2.staffHit(wizard2, jugador1);
                }
            }
            if (jugador1.type == "wizard" & jugador2.type == "warrior") {
                Warrior warrior2 = (Warrior) jugador2;
                Wizard wizard1 = (Wizard) jugador1;
                if (warrior2.getStamina() >= 5 & wizard1.getMana() >= 5) {
                    warrior2.heavyAttack(warrior2, jugador1);
                    wizard1.fireball(wizard1, jugador2);
                } if (warrior2.getStamina() < 5 & wizard1.getMana() >= 5) {
                    warrior2.weakAttack(warrior2, jugador1);
                    wizard1.fireball(wizard1, jugador2);
                } if (warrior2.getStamina() >= 5 & wizard1.getMana() < 5) {
                    warrior2.heavyAttack(warrior2, jugador1);
                    wizard1.staffHit(wizard1, jugador2);
                } if (warrior2.getStamina() < 5 & wizard1.getMana() < 5) {
                    warrior2.weakAttack(warrior2, jugador1);
                    wizard1.staffHit(wizard1, jugador2);
                }
            }

        }

        return resultadoBatalla;
    }

    // Metodo para jugar la partida
    public HashMap<Integer, Character> playGame1(HashMap equipo1, HashMap equipo2) {

        if (equipo1.size() > 0 && equipo2.size() > 0) {
            System.out.println("Elige el jugador del Equipo 1 por su ID");
            Scanner scan1 = new Scanner(System.in);
            int selectedId1 = scan1.nextInt();
            HashMap<Integer, Character> jugador1 = getJugador(equipo1, selectedId1);
            String name1 = jugador1.get(selectedId1).getName();
            System.out.println("Elige el jugador del Equipo 2 por su ID");
            Scanner scan2 = new Scanner(System.in);
            int selectedId2 = scan2.nextInt();
            HashMap<Integer, Character> jugador2 = getJugador(equipo2,selectedId2);
            String name2 = jugador1.get(selectedId2).getName();

            System.out.println("Comienza la lucha entre " + name1 + " y " + name2 + ".");
            printList1(jugador1, 1);
            printList1(jugador2, 2);

            //CODIGO REALIZACION DE LA PARTIDA
            HashMap<Integer, Character> resultadoBatalla = new HashMap<>();
            resultadoBatalla = batalla(partida);


            //batalla(player1, player2, p1Type, p2Type);

            printList1(jugador1, 1);
            printList1(jugador2, 2);
            printList1(cementerio1, 0);


        /*    playGame();
        } else if (team1.size() > 0 && team2.size() <= 0){
            System.out.println("El equipo 1 ha ganado la partida");
            System.exit(0);

        } else if (team1.size() <=0 && team2.size() >0){
            System.out.println("El equipo 2 ha ganado la partida");
            System.exit(0);
        }*/

        }
        return null;
    }

    /* Metodos auxiliares */

    //Metodo que hace todos los calculos random
    public int randomMethod(int [] value) {
        int maxValue=value[1];
        int minValue=value[0];
        int result = (int)(Math.random()*(maxValue-minValue) + minValue);

        return result;
    }

    // Metodos para crear la partida Custom
    public ArrayList createTeams(int numChar, int numTeam){
        System.out.println("Vamos a elegir los " + numChar + " jugadores del equipo " + numTeam + ".\n\n");
        for (int i = 0; i < numChar; i++){
            System.out.println("Nombre del jugador");
            Scanner name = new Scanner(System.in);
            name.nextLine();
            System.out.println("\n¿Que tipo de jugador quieres que sea, Warrior o Wizard?");
            Scanner scan1 = new Scanner(System.in);
            String type = scan1.nextLine();
            if (type == "Warrior") {
                String msn = "\n¿Cuanto HP quieres que tenga? Valores entre 100-200.";
                Scanner scan2 = new Scanner(System.in);
                int hp = obtainInt(hpWarrior, scan2, msn);
            } if (type == "Wizard"){
                Scanner scan2 = new Scanner(System.in);
                String msn = "\n¿Cuanto HP quieres que tenga? Valores entre 50-100.";
                int hp =  obtainInt(hpWizard, scan2, msn);
            }

        }
        return null;

    }

    // Metodo para revisar el integer en la entrada de datos custom
    public int obtainInt (int [] value, Scanner scan, String mensaje) {
        int maxValue = value[1];
        int minValue = value[0];
        int idx = 0;
        int scanValue = 0;
        int defaultValue = (maxValue + minValue) / 2;
        while (true) {
            System.out.println(mensaje);
            if (scan.hasNextLine()) {
                scanValue = scan.nextInt();
                if (scanValue > minValue && scanValue < maxValue) {
                    return scanValue;
                } else if ((scanValue < minValue || scanValue > maxValue) && idx < 3) {
                    System.err.println("Valor incorrecto. El valor debe estar comprendido entre " + minValue + " y " + maxValue + ".");
                    obtainInt(value, scan, mensaje);
                    idx++;
                } else if (idx == 3) {
                    System.err.println("Has cometido demasiados errores. Se pone un valor por defecto " + defaultValue + ".");
                    return defaultValue;

                }
            } else {
                System.err.println("No has escrito un número entero. Vuelve a repetir");
                obtainInt(value, scan, mensaje);
                idx++;
            }
        }
    }

    //Metodo que printa los equipos
    public void printList1(HashMap equipo, int numTeam){


        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("----------------------------- EQUIPO " + numTeam +" --------------------------------------------------");
        System.out.printf("%5s %10s %10s %7s %15s %15s %5s", "ID", "TIPO", "NOMBRE", "HP", "STAMINA", "STRENGTH", "ESTA VIVO\n");
        System.out.printf("%5s %10s %10s %7s %15s %15s %5s", "", "", "", "", "MANA", "INTELLIGENCE", "\n");
        System.out.println("-----------------------------------------------------------------------------------------");
        for (Object jugador: equipo.values()){
            System.out.printf("%5s %10s %10s %7s %15s %15s %10s", ((Character) jugador).getId(), ((Character) jugador).type, ((Character) jugador).getName(),((Character) jugador).getHp(), ((Character) jugador).getStaminaMana(), ((Character) jugador).getStrengthIntelligence(), ((Character) jugador).isAlive+ "\n");
        }
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("\n\n");

    }


    // Metodo de eleccion del jugador mediante el ID
    public HashMap<Integer, Character> getJugador(HashMap equipo, int selectedId){
        HashMap<Integer, Character> jugador = new HashMap<>();
        jugador.put(selectedId,(Character) equipo.get(selectedId));
        partida.put(selectedId,(Character) equipo.get(selectedId));
        equipo.remove(selectedId);
        return jugador;
    }

    // Dar un ID único a cada caracter de la partida:
    public int setId (int numeroEquipo){
        idx++;
        return numeroEquipo*1000 + idx;
    }

    // Determina el tipo de caracter para la partida random
    public String randomType(String[] type){
        int[] ranType = new int[]{0, type.length};
        int numRan = randomMethod(ranType);
        return type[numRan];
    }

}
