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

    //metodo que se encarga de leer el contenido del xml y guarda cada elemento en una lista
    public ListaEntrenamientos leerTodos(){
        ListaEntrenamientos listaEntrenamientos = new ListaEntrenamientos();
        XML xml = new XML("xml/entrenamientos.xml");
        Document doc;
        NodeList nodeList;
        Node node;
        Element element;
        try{
            doc = xml.leer(); //lectura
            nodeList = doc.getElementsByTagName("entrenamiento"); // se guardan todos los entrenamientos

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

    //metodo que recibe una lista y se encarga de crear un xml con formato
    public void formatear(ArrayList<Entrenamiento> listaEntrenamientos){
        XML xml = new XML("xml/entrenamientos.xml");
        Document doc;
        Element raiz;
        Element nuevoEntrenamiento;
        Element nombre,duracion,nivel;

        try{
            doc = xml.archivo(); // objeto Document vacio
            raiz = doc.createElement("entrenamientos"); // creacion de etiqueta raiz
            doc.appendChild(raiz);

            //creacion de etiqueta entrenamiento y sus etiquetas hijas y union a la raiz
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
            xml.escribir(doc,"yes"); // escritura donde se activa la indentacion

        }catch (Exception e){
            System.err.println("Error " + e.getMessage());
        }

    }

    //metodo para agregar entrenamiento donde se lee el xml y despues se escribe el nuevo entrenamiento
    public void agregar(Entrenamiento entrenamiento){
        XML xml = new XML("xml/entrenamientos.xml");
        Document doc;
        Element raiz;
        Element nuevoEntrenamiento;
        Element nombre,duracion,nivel;

        try{
            doc = xml.leer(); //lectura
            raiz = doc.getDocumentElement();

            //creacion de etiqueta entrenamiento y sus etiquetas hijas y union a la raiz
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

            xml.escribir(doc,"no"); //escritura con indentacion desactivada
        }catch (Exception e){
            System.err.println("Error " + e.getMessage());
        }
    }

    //metodo para actualizar entrenamiento donde se localiza por el id el entrenamiento a modificar
    public void actualizar(int id, Entrenamiento entrenamiento){
        int contador = 0;
        boolean encontrar = false;
        XML xml = new XML("xml/entrenamientos.xml");
        Document doc;
        NodeList nodeList;
        Node node;
        Element element;

        try{
            doc = xml.leer(); //lectura
            nodeList = doc.getElementsByTagName("entrenamiento"); // se guardan todos los entrenamientos

            do{
                //se recorre cada entrenamiento hasta llegar a la coincidencia
                node = nodeList.item(contador);
                element = (Element) node;

                //modificacion por el nuevo entrenamiento
                if(Integer.parseInt(element.getAttribute("id")) == id){
                    element.getElementsByTagName("nombre").item(0).setTextContent(entrenamiento.getNombre());
                    element.getElementsByTagName("duracion").item(0).setTextContent(String.valueOf(entrenamiento.getDuracion()));
                    element.getElementsByTagName("nivel").item(0).setTextContent(entrenamiento.getNivel());
                    encontrar = true;
                }

                contador++;
            }while(contador < nodeList.getLength() && !encontrar);

            if(encontrar){
                xml.escribir(doc,"no"); //escritura con indentacion desactivada
            }

        }catch (Exception e){
            System.err.println("Error " + e.getMessage());
        }
    }

    //metodo para eliminar entrenamiento donde se localiza por el id el entrenamiento a eliminar
    public void eliminar(int id){
        int contador = 0;
        boolean encontrar = false;
        XML xml = new XML("xml/entrenamientos.xml");
        Document doc;
        NodeList nodeList;
        Node node;
        Element element;

        try{
            doc = xml.leer(); //lectura
            nodeList = doc.getElementsByTagName("entrenamiento"); // se guardan todos los entrenamientos

            do{
                //se recorre cada entrenamiento hasta llegar a la coincidencia
                node = nodeList.item(contador);
                element = (Element) node;

                //eliminacion del entrenamiento
                if(Integer.parseInt(element.getAttribute("id")) == id){
                    element.getParentNode().removeChild(element);
                    encontrar = true;

                }
                contador++;
            }while(contador < nodeList.getLength() && !encontrar);

            if(encontrar){
                xml.escribir(doc,"no"); //escritura con indentacion desactivada
            }
        }catch (Exception e){
            System.err.println("Error " + e.getMessage());
        }
    }
}
