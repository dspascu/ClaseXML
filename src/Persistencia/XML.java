package Persistencia;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class XML {
    private String ruta;

    public XML(String ruta) {
        this.ruta = ruta;
    }

    public XML() {
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public String toString() {
        return "XML{" +
                "ruta='" + ruta + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XML xml = (XML) o;
        return Objects.equals(ruta, xml.ruta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ruta);
    }

    public Document leer(){
        File file = null;
        DocumentBuilderFactory factory = null;
        DocumentBuilder builder = null;
        Document doc = null;

        try{
            file = new File(ruta);
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            doc = builder.parse(file);

        } catch (ParserConfigurationException e) {
            System.err.println("Error con la configuración del DocumentBuilderFactory o al crear el DocumentBuilder " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error con el fichero " + e.getMessage());
        } catch (SAXException e) {
            System.err.println("Error con el contenido del xml " + e.getMessage());
        }
        return doc;
    }

    public void escribir(Document doc){
        //File file = new File(ruta);
        TransformerFactory transformerFactory = null;
        Transformer transformer = null;
        DOMSource source = null;
        StreamResult result = null;

        try{

            transformerFactory = TransformerFactory.newInstance();
            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes"); // Activar sangría
            //transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); // Espacios de sangría
            source = new DOMSource(doc);
            result = new StreamResult(new File(ruta));
            transformer.transform(source,result);

        } catch (TransformerConfigurationException e) {
            System.err.println("Error con la configuracion del Transformer " + e.getMessage());
        } catch (TransformerException e) {
            System.err.println("Error con la fuente DOMSource o con el destino StreamResult " + e.getMessage());
        }
    }
}
