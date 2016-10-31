package CustomFile.ReaderWriter;

import CustomFile.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
import java.util.ArrayList;
import java.util.Date;


public class XMLWriterReader implements IWriterReader {
    String fileName;

    public XMLWriterReader(String fileName) {
        this.fileName = fileName + ".xml";
    }

    @Override
    public ArrayList<FileItem> read() {
        String dataLine, dateLine;
        ArrayList<FileItem> file = new ArrayList<>();
        File inputFile = new File(fileName);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputFile);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("fileItem");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    dataLine = element
                            .getElementsByTagName("data")
                            .item(0)
                            .getTextContent();
                    dateLine = element
                            .getElementsByTagName("date")
                            .item(0)
                            .getTextContent();
                    file.add(new FileItem(dataLine, new Date(Long.parseLong(dateLine))));
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    @Override
    public void write(CustomFile file) {
        try {
            ArrayList<FileItem> fileItemArrayList = file.getData();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element rootElement = document.createElement("customFile");
            document.appendChild(rootElement);
            for (FileItem item : fileItemArrayList) {
                Element fileItem = document.createElement("fileItem");
                rootElement.appendChild(fileItem);
                Element data = document.createElement("data");
                data.appendChild(document.createTextNode(item.getData()));
                fileItem.appendChild(data);
                Element date = document.createElement("date");
                date.appendChild(document.createTextNode(item.getDate().getTime() + ""));
                fileItem.appendChild(date);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(fileName));
            transformer.transform(source, result);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }
}
