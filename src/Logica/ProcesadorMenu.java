package Logica;

import DAO.EntrenamientoDAO;
import Presentacion.Imprimir;
import Presentacion.Lector;
import Presentacion.Menu;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class ProcesadorMenu {
    public static int gestionarMenu() throws IllegalArgumentException, InputMismatchException{
        int opcion;
        int id;
        int total;
        int totalNodos, niveles, nodos1, nodos2, nodos3;
        String subcadena;
        ArrayList<Entrenamiento> filtroEntrenamientos = new ArrayList<Entrenamiento>();
        EntrenamientoDAO entrenamientoDAO = new EntrenamientoDAO();
        Entrenamiento entrenamiento = new Entrenamiento();
        ListaEntrenamientos listaEntrenamientos = entrenamientoDAO.leerTodos();

        try{
            opcion = Menu.menuOpciones();

            switch(opcion){
                case 1:
                    //leer
                    Imprimir.imprimir(listaEntrenamientos.getListaEntrenamientos());
                    break;
                case 2:
                    //añadir
                    id = Lector.leerInt("Escribe el id a añadir");
                    if(listaEntrenamientos.comprobarID(id)){
                        Imprimir.imprimir("No se pudo añadir porque el ya existe el id: " + id);
                    }else{
                        entrenamiento = entrenamiento.constructorEntrenamientos(id);
                        entrenamientoDAO.agregar(entrenamiento);
                        Imprimir.imprimir("Entrenamiento añadido");
                    }

                    break;
                case 3:
                    //modificar
                    id = Lector.leerInt("Escribe el id a modificar");
                    if(listaEntrenamientos.comprobarID(id)){
                        Imprimir.imprimir("A continuación escribe el nuevo entrenamiento");
                        entrenamiento = entrenamiento.constructorEntrenamientos(id);
                        entrenamientoDAO.actualizar(id,entrenamiento);
                        Imprimir.imprimir("Entrenamiento modificado");
                    }else{
                        Imprimir.imprimir("El entrenamiento no se pudo modificar porque no existe el id: " + id);
                    }
                    break;
                case 4:
                    //eliminar
                    id = Lector.leerInt("Escribe el id a eliminar");
                    if(listaEntrenamientos.comprobarID(id)){
                        entrenamientoDAO.eliminar(id);
                        Imprimir.imprimir("Entrenamiento eliminado");
                    }else{
                        Imprimir.imprimir("El entrenamiento no se pudo eliminar porque no existe el id: " + id);
                    }
                    break;
                case 5:
                    //operar
                    total = listaEntrenamientos.operacion();
                    Imprimir.imprimir("El total es: " + total);
                    break;
                case 6:
                    //estadisticas
                    niveles = listaEntrenamientos.niveles();
                    nodos1 = 1; //porque solo hay un unico nodo raiz
                    nodos2 = listaEntrenamientos.nodosSegundoNivel();
                    nodos3 = listaEntrenamientos.nodosTercerNivel();
                    totalNodos = listaEntrenamientos.totalNodos(nodos1,nodos2,nodos3);

                    Imprimir.imprimirEstadisticas(totalNodos,niveles,nodos1,nodos2,nodos3);
                    break;
                case 7:
                    subcadena = Lector.leerString("Introduce la subcadena");
                    filtroEntrenamientos = listaEntrenamientos.buscador(subcadena);
                    Imprimir.imprimir(filtroEntrenamientos);
                    break;
                case 8:
                    entrenamientoDAO.formatear(listaEntrenamientos.getListaEntrenamientos());
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
