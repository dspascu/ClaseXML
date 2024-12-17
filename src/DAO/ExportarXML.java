package DAO;

import Persistencia.XML;
import org.w3c.dom.Document;

import java.util.Objects;

public class ExportarXML {
    private String inicio;
    private String destino;

    public ExportarXML(String inicio, String destino) {
        this.inicio = inicio;
        this.destino = destino;
    }

    public ExportarXML() {
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    @Override
    public String toString() {
        return "ExportarXML{" +
                "inicio='" + inicio + '\'' +
                ", destino='" + destino + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExportarXML that = (ExportarXML) o;
        return Objects.equals(inicio, that.inicio) && Objects.equals(destino, that.destino);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inicio, destino);
    }

    //metodo exportar encargado de leer el contenido del xml y escribirlo en otra ubicacion
    public void exportar(){
        inicio = "xml/entrenamientos.xml";
        destino = "C:/xampp/htdocs/dashboard/ajax/datos/entrenamientos.xml";
        XML xml = new XML(inicio);
        Document doc;
        try{
            doc = xml.leer();
            xml.escribirXML(doc,destino);
        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }

    }

}
