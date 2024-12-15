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
                "listaEntrenamientos=" + listaEntrenamientos +
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

    public int nodosSegundoNivel(){
        return listaEntrenamientos.size();
    }

    public int nodosTercerNivel(){
        int contador=0;

        try{
            for(Entrenamiento entrenamiento : listaEntrenamientos){
                if(entrenamiento.getNombre()!=null){
                    contador++;
                }
                if(entrenamiento.getNivel()!= null){
                    contador++;
                }
                if(entrenamiento.getDuracion() > 0){
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

    public int totalNodos(int nodosPrimerNivel,int nodosSegundoNivel,int nodosTercerNivel){
        return nodosPrimerNivel + nodosSegundoNivel + nodosTercerNivel;
    }
    public int niveles(){
        int contadorNiveles =1;
        int contador = 0;
        boolean encontrar = false;
        try{
            if(listaEntrenamientos!=null){
                contadorNiveles++;

                do{
                    if(listaEntrenamientos.get(contador)!= null){
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
