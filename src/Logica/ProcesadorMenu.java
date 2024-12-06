package Logica;

import DAO.EntrenamientoDAO;
import Presentacion.Imprimir;
import Presentacion.Lector;
import Presentacion.Menu;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class ProcesadorMenu {
    public static int gestionarMenu(){
        int opcion = Menu.menuOpciones();
        int id;
        int total;
        ArrayList<Entrenamiento> lista = new ArrayList<Entrenamiento>();
        EntrenamientoDAO entrenamientoDAO = new EntrenamientoDAO();
        Entrenamiento entrenamiento = new Entrenamiento();

        try{
            switch(opcion){
                case 1:
                    //leer
                    lista = entrenamientoDAO.leerTodos();
                    Imprimir.imprimir(lista);
                    break;
                case 2:
                    //añadir
                    entrenamiento = entrenamiento.constructorEntrenamientos();
                    entrenamientoDAO.agregar(entrenamiento);
                    break;
                case 3:
                    //modificar
                    id = Lector.leerInt("Escribe el id a modificar");
                    Imprimir.imprimir("A continuación escribe el nuevo entrenamiento");
                    entrenamiento = entrenamiento.constructorEntrenamientos();
                    entrenamientoDAO.actualizar(id,entrenamiento);
                    break;
                case 4:
                    //eliminar
                    id = Lector.leerInt("Escribe el id a eliminar");
                    entrenamientoDAO.eliminar(id);
                    break;
                case 5:
                    //operar
                    lista = entrenamientoDAO.leerTodos();
                    total = Consultas.operacion(lista);
                    Imprimir.imprimir("El total es: " + total);
                    break;
                case 6:
                    //estadisticas
                    break;
            }
        }catch (InputMismatchException e){
            System.err.println("Error al introducir el tipo de dato: " + e.getMessage());
        }catch (IllegalArgumentException e) {
            System.err.println("Has introducido mal los datos " + e.getMessage());

        }catch (Exception e) {
            System.err.println("Error " + e.getMessage());

        }
        return opcion;
    }
}
