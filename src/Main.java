import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //construir
        File file = null;
        DocumentBuilderFactory factory = null;
        DocumentBuilder builder = null;
        Document doc = null;
        //elementos xml
        Element entrenamientos = null; // es el elemento raiz
        Element entrenamiento = null;
        Element nombre = null;
        Element duracion = null;
        Element nivel = null;
        //guardar cambios en el archivo xml
        TransformerFactory transformerFactory = null;
        Transformer transformer = null;
        DOMSource source = null;
        StreamResult result = null;
        //entrenamiento
        Entrenamiento entrenamiento1 = new Entrenamiento(1,"correr",60,"dificil");
        Entrenamiento entrenamiento2 = new Entrenamiento(2,"andar",80,"facil");
        Entrenamiento entrenamiento3 = new Entrenamiento(3,"esquiar",100,"dificil");
        ArrayList<Entrenamiento> listaEntrenamientos = new ArrayList<Entrenamiento>();
        listaEntrenamientos.add(entrenamiento1);
        listaEntrenamientos.add(entrenamiento2);
        listaEntrenamientos.add(entrenamiento3);
        ListaEntrenamiento listaEntrenamiento = new ListaEntrenamiento(listaEntrenamientos);

        try{
            file = new File("xml/entrenamientos.xml");
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            doc = builder.newDocument();

            entrenamientos = doc.createElement("entrenamientos");
            doc.appendChild(entrenamientos);

            for(Entrenamiento e : listaEntrenamiento.getListaEntrenamientos()){
                entrenamiento = doc.createElement("entrenamiento");
                entrenamiento.setAttribute("id",String.valueOf(e.getId()));
                nombre = doc.createElement("nombre");
                nombre.setTextContent(e.getNombre());
                duracion = doc.createElement("duracion");
                duracion.setTextContent(String.valueOf(e.getDuracion()));
                nivel = doc.createElement("nivel");
                nivel.setTextContent(e.getNivel());

                entrenamiento.appendChild(nombre);
                entrenamiento.appendChild(duracion);
                entrenamiento.appendChild(nivel);

                entrenamientos.appendChild(entrenamiento);

            }

            transformerFactory = TransformerFactory.newInstance();
            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes"); // Activar sangría
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); // Espacios de sangría
            source = new DOMSource(doc);
            result = new StreamResult(file);
            transformer.transform(source,result);

            System.out.println("Xml generado");

        } catch (Exception e){
            System.err.println("Error " + e.getMessage());
        }
    }
}
