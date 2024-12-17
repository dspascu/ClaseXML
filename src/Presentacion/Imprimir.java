package Presentacion;

import Logica.Entrenamiento;
import Logica.ListaEntrenamientos;

import java.util.ArrayList;

public class Imprimir {
    public static void imprimir(String imprime){
        System.out.println(imprime);
    }
    public static void imprimir(ArrayList<Entrenamiento> listaEntrenamientos){
        System.out.println(listaEntrenamientos);
    }
    public static void imprimir(ListaEntrenamientos listaEntrenamientos){
        System.out.println(listaEntrenamientos);
    }
    public static void imprimir(String imprime, int numero){
        System.out.println(imprime + " " + numero);
    }
    public static void imprimirEstadisticas(int nodos, int niveles, int nodos1, int nodos2, int nodos3){
        System.out.println("Total nodos: " + nodos +
                            "\nNiveles: " + niveles +
                            "\nNodos x Nivel 1: " + nodos1 +
                            "\nNodos x Nivel 2: " + nodos2 +
                            "\nNodos x Nivel 3: " + nodos3);
    }
}
