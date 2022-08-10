package Homework1;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class main {

    public static void main(String[] args) throws FileNotFoundException {


        System.out.println("** Bienvenido al juego de Array Azul ** \nElige como quieres jugar al juego\n" +
                "1    Cargar un CSV                  Pulsa 1\n" +
                "2    Jugar una partida random       Pulsa 2\n" +
                "3    Jugar una partida custom       Pulsa 3\n" +
                "4    Salir del juego                Pulsa 4\n" );
        Scanner scan = new Scanner(System.in);
        String election = scan.nextLine();
        Game1 game1 = new Game1(election);

    }
}
