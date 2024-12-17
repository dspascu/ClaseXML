package Persistencia;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
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

    //metodo que devuelve un objeto Document y que crea un xml vacio
    public Document archivo(){
        DocumentBuilderFactory factory; //proporciona una forma de crear instancias de DocumentBuilder
        DocumentBuilder builder; //construye el objeto Document
        Document doc = null;
        try{
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            doc = builder.newDocument();

        } catch (ParserConfigurationException e) {
            System.err.println("Error con la configuración del DocumentBuilderFactory o al crear el DocumentBuilder " + e.getMessage());
        }
        return doc;
    }

    //metodo que devuelve un objeto Document y se encarga de leer el contenido del xml
    public Document leer(){
        File file;
        DocumentBuilderFactory factory; //proporciona una forma de crear instancias de DocumentBuilder
        DocumentBuilder builder; //construye el objeto Document
        Document doc = null;

        try{
            file = new File(ruta);
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            doc = builder.parse(file); // para manipular el xml

        } catch (ParserConfigurationException e) {
            System.err.println("Error con la configuración del DocumentBuilderFactory o al crear el DocumentBuilder " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error con el fichero " + e.getMessage());
        } catch (SAXException e) {
            System.err.println("Error con el contenido del xml " + e.getMessage());
        }
        return doc;
    }

    //metodo para escribir xml utilizando el Document que proviene del metodo leer o del metodo archivo
    public void escribir(Document doc, String tabulacion){
        TransformerFactory transformerFactory;
        Transformer transformer; // se usa para convertir un Document en un archivo xml
        DOMSource source; //modelo estandar para representar documentos xml como estructura de arbol en memoria
        StreamResult result;

        try{

            transformerFactory = TransformerFactory.newInstance();
            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, tabulacion); //para que dar formato al xml, se activa o desactiva
            source = new DOMSource(doc);
            result = new StreamResult(new File(ruta)); //destino de la escritura
            transformer.transform(source,result);

        } catch (TransformerConfigurationException e) {
            System.err.println("Error con la configuracion del Transformer " + e.getMessage());
        } catch (TransformerException e) {
            System.err.println("Error con la fuente DOMSource o con el destino StreamResult " + e.getMessage());
        }
    }

    //metodo para escribir xml utilizando el Document que proviene del metodo leer
    public void escribirXML(Document doc, String destino){
        TransformerFactory transformerFactory;
        Transformer transformer; // se usa para convertir un Document en un archivo xml
        DOMSource source; //modelo estandar para representar documentos xml como estructura de arbol en memoria
        StreamResult result;

        try{

            transformerFactory = TransformerFactory.newInstance();
            transformer = transformerFactory.newTransformer();
            source = new DOMSource(doc);
            result = new StreamResult(new File(destino)); //destino de la escritura
            transformer.transform(source,result);

        } catch (TransformerConfigurationException e) {
            System.err.println("Error con la configuracion del Transformer " + e.getMessage());
        } catch (TransformerException e) {
            System.err.println("Error con la fuente DOMSource o con el destino StreamResult " + e.getMessage());
        }
    }

}
