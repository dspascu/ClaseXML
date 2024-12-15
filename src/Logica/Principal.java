package Logica;

import java.util.InputMismatchException;

public class Principal {
    public static void iniciar(){

        int numero;
        try{
            do{
                numero = ProcesadorMenu.gestionarMenu();
            }while(numero != 9);

        }catch (IllegalArgumentException e){
            System.err.println("Error en Principal debido a que has introducido mal los datos " + e.getMessage());
        }catch (InputMismatchException e){
            System.err.println("Error en Principal debido al tipo de dato: " + e.getMessage());
        }catch (Exception e){
            System.err.println("Error " + e.getMessage());
        }


    }
}
