package Logica;

import java.util.ArrayList;
import java.util.Objects;

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

        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }

        return sumatorio;
    }
}
