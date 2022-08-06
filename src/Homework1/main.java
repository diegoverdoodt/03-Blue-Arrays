package Homework1;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        System.out.println("** Bienvenido al juego de Array Azul ** \nElige como quieres jugar al juego\nCargar un CSV (escribe cargar)\nJugar una partida random (escribe random)\nJugar una partida custom (escibe custom)");
        Scanner scan = new Scanner(System.in);
        String election = scan.nextLine();
        Game1 game1 = new Game1(election);

    }
}
