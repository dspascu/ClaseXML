package Logica;

public class Principal {
    public static void iniciar(){

        int numero;
        do{
            numero = ProcesadorMenu.gestionarMenu();
        }while(numero != 7);

    }
}
