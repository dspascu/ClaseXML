package DAO;

import Logica.Entrenamiento;
import Persistencia.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class EntrenamientoDAO {

    public ArrayList<Entrenamiento> leerTodos(){
        ArrayList<Entrenamiento> listaEntrenamientos = new ArrayList<Entrenamiento>();
        XML xml = new XML("xml/entrenamientos.xml");
        Document doc = null;
        NodeList nodeList = null;
        Node node = null;
        Element element = null;
        try{
            doc = xml.leer();
            nodeList = doc.getElementsByTagName("entrenamiento");

            for(int i = 0; i < nodeList.getLength(); i++){
                node = nodeList.item(i);
                element = (Element) node;
                listaEntrenamientos.add(new Entrenamiento(Integer.parseInt(element.getAttribute("id")),
                        element.getElementsByTagName("nombre").item(0).getTextContent(),
                        Integer.parseInt(element.getElementsByTagName("duracion").item(0).getTextContent()),
                        element.getElementsByTagName("nivel").item(0).getTextContent()));
            }

        }catch (NullPointerException e){
            System.err.println("El elemento no tiene el contenido que se esperaba");
        }catch (Exception e){
            System.err.println("Error " + e.getMessage());
        }
        return listaEntrenamientos;
    }

    public void agregar(Entrenamiento entrenamiento){
        XML xml = new XML("xml/entrenamientos.xml");
        Document doc = null;
        Element raiz = null;
        Element nuevoEntrenamiento = null;
        Element nombre,duracion,nivel;

        try{
            doc = xml.leer();
            raiz = doc.getDocumentElement();

            nuevoEntrenamiento = doc.createElement("entrenamiento");
            nuevoEntrenamiento.setAttribute("id",String.valueOf(entrenamiento.getId()));

            nombre = doc.createElement("nombre");
            nombre.setTextContent(entrenamiento.getNombre());

            duracion = doc.createElement("duracion");
            duracion.setTextContent(String.valueOf(entrenamiento.getDuracion()));

            nivel = doc.createElement("nivel");
            nivel.setTextContent(entrenamiento.getNivel());

            nuevoEntrenamiento.appendChild(nombre);
            nuevoEntrenamiento.appendChild(duracion);
            nuevoEntrenamiento.appendChild(nivel);

            raiz.appendChild(nuevoEntrenamiento);

            xml.escribir(doc);
        }catch (Exception e){
            System.err.println("Error " + e.getMessage());
        }
    }
    public void actualizar(int id, Entrenamiento entrenamiento){
        int contador = 0;
        boolean encontrar = false;
        XML xml = new XML("xml/entrenamientos.xml");
        Document doc = null;
        NodeList nodeList = null;
        Node node = null;
        Element element = null;

        try{
            doc = xml.leer();
            nodeList = doc.getElementsByTagName("entrenamiento");

            do{
                node = nodeList.item(contador);
                element = (Element) node;

                if(Integer.parseInt(element.getAttribute("id")) == id){
                    element.getElementsByTagName("nombre").item(0).setTextContent(entrenamiento.getNombre());
                    element.getElementsByTagName("duracion").item(0).setTextContent(String.valueOf(entrenamiento.getDuracion()));
                    element.getElementsByTagName("nivel").item(0).setTextContent(entrenamiento.getNivel());
                    encontrar = true;
                }

                contador++;
            }while(contador < nodeList.getLength() && !encontrar);

            xml.escribir(doc);
        }catch (Exception e){
            System.err.println("Error " + e.getMessage());
        }
    }
    public void eliminar(int id){
        int contador = 0;
        boolean encontrar = false;
        XML xml = new XML("xml/entrenamientos.xml");
        Document doc = null;
        NodeList nodeList = null;
        Node node = null;
        Element element = null;

        try{
            doc = xml.leer();
            nodeList = doc.getElementsByTagName("entrenamiento");

            do{
                node = nodeList.item(contador);
                element = (Element) node;

                if(Integer.parseInt(element.getAttribute("id")) == id){
                    element.getParentNode().removeChild(element);
                    encontrar = true;

                }
                contador++;
            }while(contador < nodeList.getLength() && !encontrar);
            xml.escribir(doc);
        }catch (Exception e){
            System.err.println("Error " + e.getMessage());
        }
    }
}
