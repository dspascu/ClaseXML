package Presentacion;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Lector {
    public static int leerInt(String titulo) throws InputMismatchException {
        int pedido;

        Scanner scn = new Scanner(System.in);
        System.out.println(titulo);
        pedido = scn.nextInt();
        scn.nextLine();

        return pedido;

    }

    public static String leerString(String titulo){
        String pedido = "";
        try {

            Scanner scn = new Scanner(System.in);
            System.out.println(titulo);
            pedido = scn.nextLine();

        } catch (Exception e) {
            System.err.println("Error" + e.getMessage());

        }

        return pedido;

    }

}
