package Logica;

import DAO.EntrenamientoDAO;
import Presentacion.Imprimir;
import Presentacion.Lector;
import Presentacion.Menu;

import java.util.InputMismatchException;

public class ProcesadorMenu {
    public static int gestionarMenu(){
        int opcion = Menu.menuOpciones();
        int id;
        int total;
        boolean encontrar;
        EntrenamientoDAO entrenamientoDAO = new EntrenamientoDAO();
        ListaEntrenamientos listaEntrenamientos = new ListaEntrenamientos();
        Entrenamiento entrenamiento = new Entrenamiento();

        try{
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
            System.err.println("Error al introducir el tipo de dato: " + e.getMessage());
        }catch (IllegalArgumentException e) {
            System.err.println("Has introducido mal los datos " + e.getMessage());

        }catch (Exception e) {
            System.err.println("Error " + e.getMessage());

        }
        return opcion;
    }
}
