package Homework1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class Game1 {

    /* Variables */
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    private final String[] namesWarriors = {"Warrior 1","Warrior 2","Warrior 3","Warrior 4","Warrior 5","Warrior 6","Warrior 7","Warrior 8","Warrior 9","Warrior 10"};
    private final String[] namesWizards = {"Wizard 1","Wizard 2","Wizard 3","Wizard 4","Wizard 5","Wizard 6","Wizard 7","Wizard 8","Wizard 9","Wizard 10"};
    int maxPlayers = 20;
    private int idx = 0;
    String[] characterType = new String[]{"warrior", "wizard"};
    int[] hpWarrior = new int[]{100, 200};
    int[] hpWizard = new int[]{50, 100};
    int[] staminaMana = new int[]{10, 50};
    int[] strength = new int[]{1, 10};
    int[] intelligence = new int[]{1, 50};
    private int selectedId1;
    private int selectedId2;
    private int randId1;
    private int randId2;

    HashMap<Integer, Character> equipo1 = new HashMap<>();
    HashMap<Integer, Character> equipo2 = new HashMap<>();
    HashMap<Integer, Character> partida = new HashMap<>();
    HashMap<Integer, Character> cementerio1 = new HashMap<>();

    /* Constructor */

    public Game1(String election) throws FileNotFoundException {
        switch (election){
            case "1":
                System.out.println("Cargar el fichero CSV");
                createCSV1();

                playGame1(equipo1, equipo2);

                break;

            case "2":
                System.out.println("Generando la partida random");
                int[] randPlayers = new int[]{1,maxPlayers};
                int numChar = randomMethod(randPlayers);

                equipo1 = createRandom1(1, numChar);
                equipo2 = createRandom1(2, numChar);

                printList1(equipo1,1);
                printList1(equipo2,2);

                playGame1(equipo1, equipo2);

                break;

            case "3":
                System.out.println("Carga los valores que quieras usar");
                createCustom();

                playGame1(equipo1, equipo2);

                break;

            case "4":
                System.out.println("Saliendo del juego");
                System.exit(0);
        }
    }
    /* Metodos principales */
    // Crea partida a partir de un CSV

    public void createCSV1 () throws FileNotFoundException {
        String archCSV = "src/Homework1/partidaguardada.csv";
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
        printCementerio1(cementerio1);
    }


    // Crea partida con valores random
    public HashMap<Integer, Character> createRandom1 (int numEquipo, int numChar){
        //Se inicializa el numero de jugadores por equipo de manera random entre 0 al numero máximo deseado o por defecto
        HashMap<Integer, Character> equipo = new HashMap<>();
        for (int i = 0; i < numChar; i++){
            int id = setId(numEquipo);
            String type = randomType(characterType);
            if (type.equals("wizard")) {
                equipo.put(id, new Wizard(id, changeName(equipo, namesWizards[(int) (Math.random() * namesWizards.length)]), randomMethod(hpWizard), true, randomMethod(staminaMana), randomMethod(intelligence)));
            } if (type.equals("warrior")) {
                equipo.put(id, new Warrior(id, changeName(equipo, namesWarriors[(int)(Math.random() * namesWarriors.length)]), randomMethod(hpWarrior), true, randomMethod(staminaMana), randomMethod(strength)));
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
        System.out.println("Existen dos tipos de jugadores, Magos (M) y Guerreros (G)\n" +
                "Para elegir entre guerrero o mago, pon su inicial " +  ANSI_BLUE + "G" + ANSI_RESET + " o " + ANSI_BLUE + "M" + ANSI_RESET + "\n" +
                "Para los Magos introduce valores para las siguientes caracteristicas\n" +
                "   Nombre\n" +
                "   Vida (valores entre 50 y 100),\n" +
                "   Mana ( valores de 10 a 50),\n" +
                "   Inteligencia (valores de 1 a 50)\n\n" +
                "Para los Guerreros debes introducir los siguientes valores\n" +
                "   Nombre\n" +
                "   Vida (valores entre 100 y 200,\n" +
                "   Stamina (valores entre 10 y 50),\n" +
                "   Fuerza (valores entre 1 y 10)\n\n" +
                "Ejemplos:\n"+
                "   Crear un Mago:          "+  ANSI_BLUE +"M-Merlin-75-40-35" + ANSI_RESET + "\n" +
                "   Crear un Guerrero:      "+  ANSI_BLUE +"G-Atila-150-35-7" + ANSI_RESET + "\n");
        System.out.println("Empezamos creando el primer equipo");
        equipo1 = CreateTeamCustom(1, election);
        System.out.println("Vamos con el segundo equipo");
        equipo2 = CreateTeamCustom(2, election);
        printList1(equipo1,1);
        printList1(equipo2,2);
    }



    // Metodo para jugar la partida
    public void playGame1(HashMap equipo1, HashMap equipo2) {

        if (equipo1.size() > 0 && equipo2.size() > 0) {
            String name1 = null, name2 = null;
            HashMap<Integer, Character> jugador1 = new HashMap<>();
            HashMap<Integer, Character> jugador2 = new HashMap<>();
            System.out.println( "ID  Elige el jugador del Equipo 1 por su ID    Teclea el ID\n" +
                                "0   Elige un jugador de manera aleatoria       Teclea 0");
            Scanner scan1 = new Scanner(System.in);
            selectedId1 = scan1.nextInt();

            if(selectedId1 == 0){
                randId1 = randomPlayer(equipo1);

                jugador1 = getJugador(equipo1, randId1);
                name1 = jugador1.get(randId1).getName();
                selectedId1 = randId1;
            } else {
                //Revisar si el numero es correcto
                jugador1 = getJugador(equipo1, selectedId1);
                name1 = jugador1.get(selectedId1).getName();
            }
            System.out.println("ID  Elige el jugador del Equipo 2 por su ID    Teclea el ID\n" +
                    "0   Elige un jugador de manera aleatoria       Teclea 0");
            Scanner scan2 = new Scanner(System.in);
            selectedId2 = scan2.nextInt();

            if(selectedId2 == 0){

                randId2 = randomPlayer(equipo2);
                jugador2 = getJugador(equipo2, randId2);
                name2 = jugador2.get(randId2).getName();
                selectedId2 = randId2;
            } else {
                //Revisar si el numero es correcto

                //Metodo para escoger random al jugador entre los IDs que haya en el equipo
                jugador2 = getJugador(equipo2, selectedId2);
                name2 = jugador2.get(selectedId2).getName();
            }
            System.out.println("Comienza la lucha entre " + name1 + " y " + name2 + ".");
            printList1(jugador1, 1);
            printList1(jugador2, 2);

            //CODIGO REALIZACION DE LA PARTIDA

            batalla(partida);

            printList1(equipo1, 1);
            printList1(equipo2, 2);
            printCementerio1(cementerio1);

            playGame1(equipo1, equipo2);

        } else if (equipo1.size() > 0 && equipo2.size() <= 0){
            System.out.println(ANSI_BLUE+"El equipo 1 ha ganado el juego"+ANSI_RESET);
        } else {
            System.out.println(ANSI_BLUE+"El equipo 2 ha ganado el juego"+ANSI_RESET);
        }

    }

    // Metodo para jugar cada batalla segun los dos jugadores
    public void batalla(HashMap batalla) {
        Character jugador1 = (Character) batalla.get(selectedId1);
        Character jugador2 = (Character) batalla.get(selectedId2);
        round(jugador1, jugador2);

        System.out.println("Deseas guarda la partida? Escribe Y. Sino pulsa Enter");
        Scanner scan1 = new Scanner(System.in);
        String guardar = scan1.nextLine().toLowerCase();

        if (guardar.equals("y")) {
            //EscribirCSV
            try {
                escribirCSV();
                }
            catch (Exception e){
                System.err.println("Error");
            }
            System.out.println("guardando");
            System.out.println("Saliendo del juego");
            System.exit(0);
        }
    }

    //  Jugar cada round
    public void round(Character jugador1, Character jugador2){
        boolean hp1 = jugador1.isAlive();
        boolean hp2 = jugador2.isAlive();
        String type1 = jugador1.type.toLowerCase();
        String type2 = jugador2.type.toLowerCase();


        if (hp1){
            if(hp2) {
                jugador1.attack(jugador1, jugador2);
                System.out.println("\n");
                printRound1(jugador1, 1, type1);
                printRound1(jugador2, 2, type2);
                round(jugador1, jugador2);
            } if (!hp2){
                System.out.println(ANSI_BLUE + jugador1.getName() + " ha ganado al jugador " + jugador2.getName() + ". " + jugador1.getName() + " vuelve a su equipo y " + jugador2.getName() + " va al cementerio." + ANSI_RESET);
                equipo1.put(selectedId1, jugador1);
                cementerio1.put(selectedId2, jugador2);
            }
        } if (!hp1) {
            if (hp2) {
                System.out.println(ANSI_BLUE + jugador2.getName() + " ha ganado al jugador " + jugador1.getName() + ". " + jugador2.getName() + " vuelve a su equipo y " + jugador1.getName() + " va al cementerio." + ANSI_RESET);
                equipo2.put(selectedId2, jugador2);
                cementerio1.put(selectedId1, jugador1);
            }
            if (!hp2) {
                System.out.println(ANSI_RED + jugador1.getName() + " y " + jugador2.getName() + "han muerto a la vez y van al cementerio." + ANSI_RESET);
                cementerio1.put(selectedId1, jugador1);
                cementerio1.put(selectedId2, jugador2);
            }
        }
    }


    /* Metodos auxiliares */

    public void escribirCSV() throws IOException {
        FileWriter writer = new FileWriter("partidaguardada.csv");
        writer.write("Equipo,Type,Name,HP,Stamina / Mana,Strength / Intelligence\n");

            for (Character jugador : equipo1.values()){
                writer.write("1," + jugador.type + "," + jugador.getName()+","+ jugador.getHp()+","+jugador.getStaminaMana()+","+jugador.getStrengthIntelligence()+"\n");
            }

            for (Character jugador : equipo2.values()){
                writer.write("2," + jugador.type + "," + jugador.getName()+","+ jugador.getHp()+","+jugador.getStaminaMana()+","+jugador.getStrengthIntelligence()+"\n");
            }


            for (Character jugador : cementerio1.values()){
                writer.write("9," + jugador.type + "," + jugador.getName()+","+ jugador.getHp()+","+jugador.getStaminaMana()+","+jugador.getStrengthIntelligence()+"\n");
            }

        writer.close();
    }

    // Funcion para crear cada jugador custom
    public HashMap<Integer, Character> CreateTeamCustom(int numEquipo, int election) {
        HashMap<Integer, Character> equipo = new HashMap<>();
        for (int i = 0; i < election; i++) {
            int playerNum = i +1;
            System.out.println("Apunta los valores del jugador " + playerNum + " del equipo" + 1);
            Scanner scan2 = new Scanner(System.in);
            String[] createCustomPlayer = scan2.nextLine().split("-");
            //funcion para revisar el String de arriba
                //primera condicion de la funcion
                /*if (createCustomPlayer[0].toLowerCase() != "m" || createCustomPlayer[0].toLowerCase() != "g"){
                    System.err.println("No has elegido bien el tipo de jugador. Tiene que ser M o G");
                    Scanner scan3 = new Scanner(System.in);
                    createCustomPlayer = scan3.nextLine().split("-");
                }*/
                //segunda condicion de la funcion
                //if (createCustomPlayer[2] - valores de HP
                //if (createCustomPlayer[3] - valores de Mana / Stamina
                //if (createCustomPlayer[4] - valores de Strength / Intelligence

            String characterTypeCustom = createCustomPlayer[0];
            int id = setId(numEquipo);
            if (characterTypeCustom.toLowerCase().equals("m")) {
                equipo.put(id, new Wizard(id, createCustomPlayer[1], Integer.parseInt(createCustomPlayer[2]), true, Integer.parseInt(createCustomPlayer[3]), Integer.parseInt(createCustomPlayer[4])));
            } else if (characterTypeCustom.equalsIgnoreCase("g")) {
                equipo.put(id, new Warrior(id, createCustomPlayer[1], Integer.parseInt(createCustomPlayer[2]), true, Integer.parseInt(createCustomPlayer[3]), Integer.parseInt(createCustomPlayer[4])));
            }
        }

        return equipo;
    }

    public int randomPlayer(HashMap<Integer, Character> equipo){
        ArrayList idsRand = new ArrayList<>();
        for(Integer key : equipo.keySet()){
            idsRand.add(key);
        }
        int random = (int)(Math.random() * idsRand.size());


                return (int) idsRand.get(random);
    }
    // Nombre repetido

    public String changeName (HashMap<Integer, Character> equipo, String name){
        String chName = name;
        for (Character jugador : equipo.values()){
            if (name.equals(jugador.getName())){
                chName = name + " Jr.";
            }
        }
        return chName;
    }

    //Metodo que hace todos los calculos random
    public int randomMethod(int [] value) {
        int maxValue=value[1];
        int minValue=value[0];
        return (int)(Math.random()*(maxValue-minValue) + minValue);
    }

    //Metodo que printa los equipos
    public void printList1(HashMap equipo, int numTeam){


        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("----------------------------- EQUIPO " + numTeam +" -----------------------------------------------");
        System.out.printf("%5s %10s %15s %7s %15s %15s %5s", "ID", "TIPO", "NOMBRE", "HP", "STAMINA", "STRENGTH", "ESTA VIVO\n");
        System.out.printf("%5s %10s %15s %7s %15s %15s %5s", "", "", "", "", "MANA", "INTELLIGENCE", "\n");
        System.out.println("--------------------------------------------------------------------------------------");
        for (Object jugador: equipo.values()){
            System.out.printf("%5s %10s %15s %7s %15s %15s %10s", ((Character) jugador).getId(), ((Character) jugador).type, ((Character) jugador).getName(),((Character) jugador).getHp(), ((Character) jugador).getStaminaMana(), ((Character) jugador).getStrengthIntelligence(), "Vivito y coleando" + "\n");
        }
        System.out.println("--------------------------------------------------------------------------------------\n");


    }
    public void printCementerio1(HashMap equipo){

        System.out.println(ANSI_RED + "----------------------------------------------------------------------------------------");
        System.out.println("----------------------------- CEMENTERIO -----------------------------------------------");
        System.out.printf("%5s %10s %15s %7s %15s %15s %5s", "ID", "TIPO", "NOMBRE", "HP", "STAMINA", "STRENGTH", "ESTA VIVO\n");
        System.out.printf("%5s %10s %15s %7s %15s %15s %5s", "", "", "", "", "MANA", "INTELLIGENCE", "\n");
        System.out.println("-----------------------------------------------------------------------------------------");
        for (Object jugador: equipo.values()){
            System.out.printf("%5s %10s %15s %7s %15s %15s %10s", ((Character) jugador).getId(), ((Character) jugador).type, ((Character) jugador).getName(),((Character) jugador).getHp(), ((Character) jugador).getStaminaMana(), ((Character) jugador).getStrengthIntelligence(), "Criando malvas"+ "\n");
        }
        System.out.println("-----------------------------------------------------------------------------------------\n"+ ANSI_RESET);
    }

    public void printRound1(Character character, int numTeam, String type){
        if(character.isAlive) {
            if (type.equals("warrior")) {
                System.out.println("-------------------- " + character.getName() + " del equipo " + numTeam + " tiene estos valores ---------------------");
                System.out.println("--------------------------------------------------------------------------------------");
                System.out.printf(" %10s  %7s %15s %15s %10s", character.type.toString(), "HP: " + character.getHp(), "Stramina: " + character.getStaminaMana(), "Strength: " + character.getStrengthIntelligence(), "Sigue vivo" + "\n");
                System.out.println("--------------------------------------------------------------------------------------\n");
            } else if (type.equals("wizard")) {
                System.out.println("-------------------- " + character.getName() + " del equipo " + numTeam + " tiene estos valores ---------------------");
                System.out.println("--------------------------------------------------------------------------------------");
                System.out.printf(" %10s  %7s %15s %15s %10s", character.type.toString(), "HP: " + character.getHp(), "Stramina: " + character.getStaminaMana(), "Strength: " + character.getStrengthIntelligence(), "Sigue vivo" + "\n");
                System.out.println("--------------------------------------------------------------------------------------\n");
            }
        } else if (!character.isAlive) {
            if (type.equals("warrior")) {
                System.out.println("-------------------- " + character.getName() + " del equipo " + numTeam + " tiene estos valores ---------------------");
                System.out.println("--------------------------------------------------------------------------------------");
                System.out.printf(" %10s  %7s %15s %15s %10s", character.type.toString(), "HP: " + character.getHp(), "Stramina: " + character.getStaminaMana(), "Strength: " + character.getStrengthIntelligence(), ANSI_RED +"Ha muerto" +ANSI_RESET+ "\n");
                System.out.println("--------------------------------------------------------------------------------------\n");
            } else if (type.equals("wizard")) {
                System.out.println("-------------------- " + character.getName() + " del equipo " + numTeam + " tiene estos valores ---------------------");
                System.out.println("--------------------------------------------------------------------------------------");
                System.out.printf(" %10s  %7s %15s %15s %10s", character.type.toString(), "HP: " + character.getHp(), "Stramina: " + character.getStaminaMana(), "Strength: " + character.getStrengthIntelligence(), ANSI_RED +"Ha muerto" +ANSI_RESET+ "\n");
                System.out.println("--------------------------------------------------------------------------------------\n");
            }
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
