package Logica;

import DAO.EntrenamientoDAO;
import Presentacion.Imprimir;
import Presentacion.Lector;
import Presentacion.Menu;

import java.util.InputMismatchException;

public class ProcesadorMenu {
    public static int gestionarMenu() throws IllegalArgumentException, InputMismatchException{
        int opcion =0;
        int id;
        int total;
        boolean encontrar;
        EntrenamientoDAO entrenamientoDAO = new EntrenamientoDAO();
        ListaEntrenamientos listaEntrenamientos = new ListaEntrenamientos();
        Entrenamiento entrenamiento = new Entrenamiento();

        try{
            opcion = Menu.menuOpciones();
            switch(opcion){
                case 1:
                    //leer
                    listaEntrenamientos = new ListaEntrenamientos(entrenamientoDAO.leerTodos());
                    Imprimir.imprimir(listaEntrenamientos.getListaEntrenamientos());
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
                    entrenamiento = entrenamiento.constructorEntrenamientos(id);
                    encontrar = entrenamientoDAO.actualizar(id,entrenamiento);

                    if(!encontrar){
                        Imprimir.imprimir("El entrenamiento no se pudo modificar porque no existe el id: " + id);
                    } else{
                        Imprimir.imprimir("Entrenamiento modificado");
                    }
                    break;
                case 4:
                    //eliminar
                    id = Lector.leerInt("Escribe el id a eliminar");
                    encontrar = entrenamientoDAO.eliminar(id);

                    if(!encontrar){
                        Imprimir.imprimir("El entrenamiento no se pudo eliminar porque no existe el id: " + id);
                    } else{
                        Imprimir.imprimir("Entrenamiento eliminado");
                    }
                    break;
                case 5:
                    //operar
                    listaEntrenamientos = new ListaEntrenamientos(entrenamientoDAO.leerTodos());
                    total = listaEntrenamientos.operacion();
                    Imprimir.imprimir("El total es: " + total);
                    break;
                case 6:
                    //estadisticas
                    break;
            }
        }catch (InputMismatchException e){
            System.err.println("Error producido en ProcesadorMenu al introducir el tipo de dato: " + e.getMessage());
            throw e;
        }catch (IllegalArgumentException e) {
            System.err.println("Error producido en ProcesadorMenu ya que has introducido mal los datos " + e.getMessage());
            throw e;

        }catch (Exception e) {
            System.err.println("Error producido en ProcesadorMenu " + e.getMessage());
            throw e;

        }
        return opcion;
    }
}
