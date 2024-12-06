package Logica;

import java.util.ArrayList;

public class Consultas {
    public static int operacion(ArrayList<Entrenamiento> listaEntrenamiento){
        int sumatorio = 0;
        try{
            for(int i = 0; i < listaEntrenamiento.size(); i++){
                sumatorio += listaEntrenamiento.get(i).getDuracion();
            }
        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }

        return sumatorio;
    }
}
