package DAO;

import Logica.Entrenamiento;
import Logica.ListaEntrenamientos;
import Persistencia.XML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class EntrenamientoDAO {

    public ListaEntrenamientos leerTodos(){
        ListaEntrenamientos listaEntrenamientos = new ListaEntrenamientos();
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
                listaEntrenamientos.getListaEntrenamientos().add(new Entrenamiento(Integer.parseInt(element.getAttribute("id")),
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

    public void agregar(ArrayList<Entrenamiento> listaEntrenamientos){
        XML xml = new XML("xml/entrenamientos.xml");
        Document doc = null;
        Element raiz = null;
        Element nuevoEntrenamiento = null;
        Element nombre,duracion,nivel;

        try{
            doc = xml.archivo();
            raiz = doc.createElement("entrenamientos");
            doc.appendChild(raiz);

            for(Entrenamiento e: listaEntrenamientos){
                nuevoEntrenamiento = doc.createElement("entrenamiento");
                nuevoEntrenamiento.setAttribute("id",String.valueOf(e.getId()));
                nombre = doc.createElement("nombre");
                nombre.setTextContent(e.getNombre());
                duracion = doc.createElement("duracion");
                duracion.setTextContent(String.valueOf(e.getDuracion()));
                nivel = doc.createElement("nivel");
                nivel.setTextContent(e.getNivel());

                nuevoEntrenamiento.appendChild(nombre);
                nuevoEntrenamiento.appendChild(duracion);
                nuevoEntrenamiento.appendChild(nivel);

                raiz.appendChild(nuevoEntrenamiento);

            }
            xml.escribir(doc,"yes");

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

            xml.escribir(doc,"no");

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

            if(encontrar){
                xml.escribir(doc,"no");
            }
        }catch (Exception e){
            System.err.println("Error " + e.getMessage());
        }
    }
}
