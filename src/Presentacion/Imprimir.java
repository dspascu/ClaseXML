package Presentacion;

import Logica.Entrenamiento;

import java.util.ArrayList;

public class Imprimir {
    public static void imprimir(String imprime){
        System.out.println(imprime);
    }
    public static void imprimir(ArrayList<Entrenamiento> listaEntrenamientos){
        System.out.println(listaEntrenamientos);
    }
    public static void imprimir(String imprime, int numero){
        System.out.println(imprime + " " + numero);
    }
}
