package model;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;

public class XmlHandling {

    private Validator validator;
    private File xsdFile;
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
        xmlSource = new StreamSource(file);
        try {
            validator.validate(xmlSource);
            System.out.println(xmlSource.getSystemId() + " is valid");
        } catch (Exception e) {
            System.out.println(xmlSource.getSystemId() + " is NOT valid reason:" + e);
        }
    }

    public boolean validate() {
        return false;
    }
}
