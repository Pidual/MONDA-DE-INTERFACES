package persitence;

import models.Word;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileOperationXML {

    private Document doc;
    private DocumentBuilder builder;

    public FileOperationXML(String file) {
        doc = getDocument(file);
    }

    private Document getDocument(String file) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document document = null;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new File("src/resources/Diccionario.XML"));
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return document;
    }

    public ArrayList<String> getArrayListXML() {
        ArrayList<String> wordAdSynonimsList = new ArrayList<>();
        NodeList listaPalabras = doc.getElementsByTagName("word");
        for (int i = 0; i < listaPalabras.getLength(); i++) {
            Node nodo = listaPalabras.item(i);
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nodo;
                NodeList childs = element.getChildNodes();

                for (int j = 0; j < childs.getLength(); j++) {
                    Node hijo = childs.item(j);
                    if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                        Element eHijo = (Element) hijo;
                        wordAdSynonimsList.add(hijo.getTextContent());
                    }
                }
                wordAdSynonimsList.add("");
            }
        }
        return wordAdSynonimsList;
    }

    public void saveTextFile(ArrayList<Word> wordList) {

        DOMImplementation implementation = builder.getDOMImplementation();
        Document document = implementation.createDocument(null, "words", null);
        document.setXmlVersion("1.0");

        for (Word value : wordList) {
            Element word = document.createElement("word"); //Open word;
            Element synonym = document.createElement("synonym");//Opens syn
            Text text = document.createTextNode(value.getWord());
            System.out.println(value.getWord());
            synonym.appendChild(text);
            word.appendChild(synonym);

            for (int j = 0; j < value.getSynonymCount(); j++) {
                Element trueSynonym = document.createElement("synonym"); //Opens synonym
                Text textSynonym = document.createTextNode(value.getSynonym(j));
                System.out.println(value.getSynonym(j));
                trueSynonym.appendChild(textSynonym);//Agrega el txt
                word.appendChild(trueSynonym); //Closes synonym
            }
            document.getDocumentElement().appendChild(word); //Closes word
        }


        //Writes down the document
        Source source = new DOMSource(document);
        Result result = new StreamResult(new File("src/resources/Diccionario.XML"));
        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

}
