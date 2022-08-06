package Homework1;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;



public class Game1 {

    /* Variables */

    private String[] namesWarriors = {"Warrior1","Warrior2","Warrior3","Warrior4","Warrior5","Warrior6","Warrior7","Warrior8","Warrior9","Warrior10"};
    private String[] namesWizards = {"Wizards1","Wizards2","Wizards3","Wizards4","Wizards5","Wizards6","Wizards7","Wizards8","Wizards9","Wizards10"};
    int maxPlayers = 6;
    private int idx = 0;
    String[] characterType = new String[]{"warrior", "wizard"};
    int[] hpWarrior = new int[]{100, 200};
    int[] hpWizard = new int[]{50, 100};
    int[] staminaMana = new int[]{10, 50};
    int[] strength = new int[]{1, 10};
    int[] intelligence = new int[]{1, 50};
    private int selectedId1;
    private int selectedId2;

    HashMap<Integer, Character> equipo1 = new HashMap<>();
    HashMap<Integer, Character> equipo2 = new HashMap<>();
    HashMap<Integer, Character> partida = new HashMap<>();
    HashMap<Integer, Character> cementerio1 = new HashMap<>();

    /* Constructor */

    public Game1(String election) throws FileNotFoundException {
        election.toLowerCase();
        switch (election){
            case "cargar":
                System.out.println("Cargar el fichero CSV");
                createCSV1();
                playGame1(equipo1, equipo2);

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
    // Abrir CSV
    public void createCSV1 () throws FileNotFoundException {
        String archCSV = "src/Homework1/prueba1.csv";
        Scanner fichero = new Scanner(new File(archCSV));
        System.out.println(fichero.nextLine());

        while(fichero.hasNext()){
            String [] elemento = fichero.nextLine().split(",");
            if (Integer.parseInt(elemento[3]) >0) {
                if (Integer.parseInt(elemento[0]) == 1) {
                    int id = setId(1);
                    if (elemento[1].toLowerCase().equals("warrior")) {
                        equipo1.put(id, new Warrior(id, elemento[2], Integer.parseInt(elemento[3]), true, Integer.parseInt(elemento[4]), Integer.parseInt(elemento[5])));
                    }
                    if (elemento[1].toLowerCase().equals("wizard")) {
                        equipo1.put(id, new Wizard(id, elemento[2], Integer.parseInt(elemento[3]), true, Integer.parseInt(elemento[4]), Integer.parseInt(elemento[5])));
                    }
                }
                if (Integer.parseInt(elemento[0]) == 2) {
                    int id = setId(2);
                    if (elemento[1].toLowerCase().equals("warrior")) {
                        equipo2.put(id, new Warrior(id, elemento[2], Integer.parseInt(elemento[3]), true, Integer.parseInt(elemento[4]), Integer.parseInt(elemento[5])));
                    }
                    if (elemento[1].toLowerCase().equals("wizard")) {
                        equipo2.put(id, new Wizard(id, elemento[2], Integer.parseInt(elemento[3]), true, Integer.parseInt(elemento[4]), Integer.parseInt(elemento[5])));
                    }
                }
            } else if (Integer.parseInt(elemento[3]) <= 0){
                int id = setId(9);
                if (elemento[1].toLowerCase().equals("warrior")) {
                    cementerio1.put(id, new Warrior(id, elemento[2], Integer.parseInt(elemento[3]), false, Integer.parseInt(elemento[4]), Integer.parseInt(elemento[5])));
                }
                if (elemento[1].toLowerCase().equals("wizard")) {
                    cementerio1.put(id, new Wizard(id, elemento[2], Integer.parseInt(elemento[3]), false, Integer.parseInt(elemento[4]), Integer.parseInt(elemento[5])));
                }
            }

        }

        printList1(equipo1,1);
        printList1(equipo2,2);
        printList1(cementerio1,9);


        // recoger valores de CSV con el orden que toca
        // diferenciar entre Warrior y Wizard
        // saber orden de valores de CSV para introducir en los constructores
        // Equipo (1 o 2) | Warrior o Wizard | Nombre | HP | Stamina y Mana | Strength y Intelligence
        // Si HP es 0, enviar a cementerio directamente
        // creas los characters - Warriors o Wizards
        // juegas
        // guardar csv

    }


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



    // Metodo para jugar la partida
    public HashMap<Integer, Character> playGame1(HashMap equipo1, HashMap equipo2) {

        if (equipo1.size() > 0 && equipo2.size() > 0) {
            System.out.println("Elige el jugador del Equipo 1 por su ID");
            Scanner scan1 = new Scanner(System.in);
            selectedId1 = scan1.nextInt();
            HashMap<Integer, Character> jugador1 = getJugador(equipo1, selectedId1);
            String name1 = jugador1.get(selectedId1).getName();
            System.out.println(name1);

            System.out.println("Elige el jugador del Equipo 2 por su ID");
            Scanner scan2 = new Scanner(System.in);
            selectedId2 = scan2.nextInt();
            HashMap<Integer, Character> jugador2 = getJugador(equipo2,selectedId2);
            String name2 = jugador2.get(selectedId2).getName();
            System.out.println(name2);

            System.out.println("Comienza la lucha entre " + name1 + " y " + name2 + ".");
            printList1(jugador1, 1);
            printList1(jugador2, 2);

            //CODIGO REALIZACION DE LA PARTIDA

            batalla(partida);


            //batalla(player1, player2, p1Type, p2Type);

            printList1(jugador1, 1);
            printList1(jugador2, 2);

            printList1(equipo1, 1);
            printList1(equipo2, 2);
            printList1(cementerio1, 9);

            playGame1(equipo1, equipo2);
        /*} else if (team1.size() > 0 && team2.size() <= 0){
            System.out.println("El equipo 1 ha ganado la partida");
            System.exit(0);

        } else if (team1.size() <=0 && team2.size() >0){
            System.out.println("El equipo 2 ha ganado la partida");
            System.exit(0);
        }*/

        } else if (equipo1.size() > 0 && equipo2.size() <= 0){
            System.out.println("El equipo 1 ha ganado el juego");
        } else {
            System.out.println("El equipo 2 ha ganado el juego");
        }
        return null;
    }

    // Metodo para jugar cada batalla segun los dos jugadores
    public void batalla(HashMap batalla) throws IOException {
        Character jugador1 = (Character) batalla.get(selectedId1);
        Character jugador2 = (Character) batalla.get(selectedId2);
        round(jugador1, jugador2);

        System.out.println("Deseas guarda la partida? Escribe Y. Sino pulsa Enter");
        Scanner scan1 = new Scanner(System.in);
        String guardar = scan1.nextLine().toLowerCase();

        if (guardar.equals("y")) {
            //EscribirCSV
            File file = new File("src/Homework1/partidaguardada.csv");
            FileWriter fileWriter = new FileWriter(file);



            File createCSV = file;
            System.out.println("guardando");
            System.out.println("Saliendo del juego");
            System.exit(0);
        }

    }

    //  Jugar cada round
    public void round(Character jugador1, Character jugador2){
        boolean hp1 = jugador1.isAlive();
        boolean hp2 = jugador2.isAlive();

        if (hp1){
            if(hp2) {
                jugador1.attack(jugador1, jugador2);
                printRound1(jugador1, 1);
                printRound1(jugador2, 2);
                round(jugador1, jugador2);
            } if (hp2 == false){
                System.out.println(jugador1.getName() + " ha ganado al jugador " + jugador2.getName() + ". " + jugador1.getName() + " vuelve a su equipo y " + jugador2.getName() + "va al cementerio.");
                equipo1.put(selectedId1, jugador1);
                cementerio1.put(selectedId2, jugador2);
            }
        } if (hp1 == false) {
            if (hp2) {
                equipo2.put(selectedId2, jugador2);
                cementerio1.put(selectedId1, jugador1);
            }
            if (hp2 == false) {
                cementerio1.put(selectedId1, jugador1);
                cementerio1.put(selectedId2, jugador2);
            }
        }
    }


    /* Metodos auxiliares */


    // Nombre repetido

    /*public String changeName (HashMap equipo, String name){

        for (String jugador : equipo.keySet()){

        }

        for (int i = 0; i < equipo.size(); i++){
            equipo.get
        }

        return null;
    }*/

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

    public void printRound1(Character character, int numTeam){
        if (character.isAlive){

        }
        if (character.type.equals("warrior")) {
            System.out.println("----------------------------- JUGADOR: " + character.getName() + " tiene estos valores--------------------------------------------------");
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.printf(" %10s  %7s %15s %15s %10s", character.type.toString(), "HP: " + character.getHp(), "Stramina: " + character.getStaminaMana(), "Strength: " + character.getStrengthIntelligence(), character.isAlive + "\n");
            System.out.println("-----------------------------------------------------------------------------------------\n");
        } else if(character.type.equals("wizard")) {
            System.out.println("----------------------------- JUGADOR: " + character.getName() + " tiene estos valores--------------------------------------------------");
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.printf(" %10s  %7s %15s %15s %10s", character.type.toString(), "HP: " + character.getHp(), "Stramina: " + character.getStaminaMana(), "Strength: " + character.getStrengthIntelligence(), character.isAlive + "\n");
            System.out.println("-----------------------------------------------------------------------------------------\n");
        }

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
