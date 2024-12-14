package Presentacion;

import java.util.InputMismatchException;

public class Menu {
    public static int menuOpciones() throws InputMismatchException, IllegalArgumentException{
        int opcion=0;
        boolean interruptor = false;
        try{
            do{
                opcion = Lector.leerInt("Introduce una opción: \n1.Leer fichero xml" +
                        "\n2.Añadir etiqueta xml" +
                        "\n3.Modificar etiqueta xml" +
                        "\n4.Eliminar etiqueta xml" +
                        "\n5.Calcular la duración total de los entrenamientos" +
                        "\n6.Estadísticas del xml" +
                        "\n7.Buscador" +
                        "\n8.Salir");


                switch(opcion){
                    case 1:
                        Imprimir.imprimir("Leyendo fichero xml...");
                        interruptor=true;
                        break;

                    case 2:
                        Imprimir.imprimir("Añadiendo etiqueta xml...");
                        interruptor=true;
                        break;

                    case 3:
                        Imprimir.imprimir("Modificando etiqueta xml...");
                        interruptor=true;
                        break;

                    case 4:
                        Imprimir.imprimir("Eliminando etiqueta xml...");
                        interruptor=true;
                        break;

                    case 5:
                        Imprimir.imprimir("Realizando la operación...");
                        interruptor=true;
                        break;
                    case 6:
                        Imprimir.imprimir("Estadísticas...");
                        interruptor = true;
                        break;
                    case 7:
                        Imprimir.imprimir("Buscador por subcadena...");
                        interruptor = true;
                        break;
                    case 8:
                        Imprimir.imprimir("Saliendo...");
                        interruptor = true;
                        break;
                }


            }while(!interruptor);
        }catch (InputMismatchException e){
            System.err.println("Error producido en Menu al introducir el tipo de dato: " + e.getMessage());
            throw e;
        }catch (IllegalArgumentException e) {
            System.err.println("Error producido en Menu ya que has introducido mal los datos " + e.getMessage());
            throw e;

        }catch (Exception e){
            System.err.println("Error producido en Menu " + e.getMessage());
            throw e;
        }
        return opcion;
    }
}
