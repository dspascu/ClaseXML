package Logica;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ListaEntrenamientos {
    private ArrayList<Entrenamiento> listaEntrenamientos;

    public ListaEntrenamientos() {
        this.listaEntrenamientos = new ArrayList<Entrenamiento>();
    }
    public ListaEntrenamientos(ArrayList<Entrenamiento> listaEntrenamientos) {
        this.listaEntrenamientos = listaEntrenamientos;
    }

    public ArrayList<Entrenamiento> getListaEntrenamientos() {
        return listaEntrenamientos;
    }

    public void setListaEntrenamientos(ArrayList<Entrenamiento> listaEntrenamientos) {
        this.listaEntrenamientos = listaEntrenamientos;
    }

    @Override
    public String toString() {
        return "ListaEntrenamientos{" +
                "\n" + listaEntrenamientos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListaEntrenamientos that = (ListaEntrenamientos) o;
        return Objects.equals(listaEntrenamientos, that.listaEntrenamientos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listaEntrenamientos);
    }

    //metodo para calcular la suma de la duracion total de los entrenamientos
    public int operacion(){
        int sumatorio = 0;
        try{

            for(int i = 0; i < listaEntrenamientos.size(); i++){
                sumatorio += listaEntrenamientos.get(i).getDuracion();
            }

        }catch(NullPointerException e){
            System.err.println("Hay valores nulos en la lista " + e.getMessage());
        }catch(Exception e){
            System.err.println("Error " + e.getMessage());
        }

        return sumatorio;
    }

    //metodo para comprobar id a la hora de añadir, modificar y eliminar
    public boolean comprobarID(int id){
        boolean encontrar = false;
        int contador =0;
        try{
            do{
                if(listaEntrenamientos.get(contador).getId() == id){
                    encontrar = true;
                }
                contador++;
            }while(contador < listaEntrenamientos.size() && !encontrar);

        }catch(NullPointerException e){
            System.err.println("Hay valores nulos en la lista " + e.getMessage());
        }catch(Exception e){
            System.err.println("Error " + e.getMessage());
        }
        return encontrar;
    }

    //metodo que filtra la lista en funcion de la subcadena y devuelve un ArrayList con las coincidencias
    public ArrayList<Entrenamiento> buscador(String subcadena){
        List<Entrenamiento> listaBuscador = new ArrayList<Entrenamiento>();
        try {
            listaBuscador = listaEntrenamientos.stream()
                    .filter(e -> e.getNombre().contains(subcadena) || e.getNivel().contains(subcadena))
                    .collect(Collectors.toList());
        } catch(NullPointerException e){
            System.err.println("Hay valores nulos en la lista " + e.getMessage());
        }catch (Exception e){
            System.err.println("Error " + e.getMessage());
        }

        return new ArrayList<>(listaBuscador);

    }

    //obtener los nodos del segundo nivel
    public int nodosSegundoNivel(){
        return listaEntrenamientos.size();
    }

    //obtener los nodos del tercer nivel
    public int nodosTercerNivel(){
        int contador=0;

        try{
            for(Entrenamiento entrenamiento : listaEntrenamientos){ // cuenta cada etiqueta duracion,nivel y nombre
                if(!entrenamiento.getNombre().isEmpty()){
                    contador++;
                }
                if(!entrenamiento.getNivel().isEmpty()){
                    contador++;
                }
                if(entrenamiento.getDuracion() >= 0){
                    contador++;
                }
            }
        } catch(NullPointerException e){
            System.err.println("Hay valores nulos en la lista " + e.getMessage());
        }catch (Exception e){
            System.err.println("Error " + e.getMessage());
        }
        return contador;
    }

    //suma total de nodos
    public int totalNodos(int nodosPrimerNivel,int nodosSegundoNivel,int nodosTercerNivel){
        return nodosPrimerNivel + nodosSegundoNivel + nodosTercerNivel;
    }

    //metodo para obtener los niveles
    public int niveles(){
        int contadorNiveles =1; // nivel raiz
        int contador = 0;
        boolean encontrar = false;
        try{
            if(!listaEntrenamientos.isEmpty()){ // segundo nivel
                contadorNiveles++;

                do{
                    if(listaEntrenamientos.get(contador)!= null){ //tercer nivel
                        contadorNiveles++;
                        encontrar = true;
                    }
                    contador++;

                }while(contador < listaEntrenamientos.size() && !encontrar);
            }
        } catch(NullPointerException e){
            System.err.println("Hay valores nulos en la lista " + e.getMessage());
        }catch (Exception e){
            System.err.println("Error " + e.getMessage());
        }

        return contadorNiveles;
    }

}
