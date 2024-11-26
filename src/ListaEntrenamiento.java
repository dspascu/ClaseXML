import java.util.ArrayList;
import java.util.Objects;

public class ListaEntrenamiento {
    private ArrayList<Entrenamiento> listaEntrenamientos;

    public ListaEntrenamiento() {
        this.listaEntrenamientos = new ArrayList<Entrenamiento>();
    }

    public ListaEntrenamiento(ArrayList<Entrenamiento> listaEntrenamientos) {
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
        return "ListaEntrenamiento{" +
                "listaEntrenamientos=" + listaEntrenamientos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListaEntrenamiento that = (ListaEntrenamiento) o;
        return Objects.equals(listaEntrenamientos, that.listaEntrenamientos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listaEntrenamientos);
    }
}
