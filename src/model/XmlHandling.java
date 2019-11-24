package model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.File;

import static java.lang.Thread.sleep;

public class XmlHandling {

    private Validator validator;
    private File xsdFile;
    private File xmlFile;
    private Source xmlSource;
    private Document doc;

    public XmlHandling() {
        xsdFile = new File("xsd.xml");
        Schema schema;
        SchemaFactory schemaFactory;

        try {
            schemaFactory = SchemaFactory
                    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            schema = schemaFactory.newSchema(xsdFile);
            validator = schema.newValidator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void processXml(File file) {
        xmlFile = file;
        xmlSource = new StreamSource(file);
        try {
            validator.validate(xmlSource);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            Element root = doc.getDocumentElement();
            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();
            String exp = "/actions/action/order[2]/clientId";
            String str = xpath.evaluate(exp, root);

            System.out.println("traitement en cours...");
            sleep(3000);


            System.out.println(xmlSource.getSystemId() + " is valid");
            System.out.println("client id" + str);
        } catch (Exception e) {
            System.out.println(xmlSource.getSystemId() + " is NOT valid reason:" + e);
        }
    }

    public boolean validate() {
        return false;
    }
}
