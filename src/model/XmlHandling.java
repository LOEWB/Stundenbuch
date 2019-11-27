package model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.time.LocalDateTime;

import static java.lang.Thread.sleep;

public class XmlHandling {

    private Validator validator;
    private File xsdFile;
    private File xmlFile;
    private Source xmlSource;
    private Document doc;
    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private Element root;

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

            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            doc = builder.parse(xmlFile);
            root = doc.getDocumentElement();
            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();
            processXpath(root);

            String exp = "/actions/action/order[2]/clientId";
            String str = xpath.evaluate(exp, root);

            System.out.println("traitement en cours...");
            sleep(3000);


            System.out.println(xmlSource.getSystemId() + " is valid");
            //System.out.println("client id" + str);
        } catch (Exception e) {
            System.out.println(xmlSource.getSystemId() + " is NOT valid reason:" + e);
        }
    }

    private void processXpath(Element e) {
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        NodeList orderList;
        Double idTicket;
        try {
            //n = ((NodeList) xpath.evaluate("/actions", e, XPathConstants.NODESET)).item(0);
            //NodeList x = (NodeList) xpath.evaluate("/", e, XPathConstants.NODESET);
            idTicket = (Double) xpath.evaluate("/actions/@id", e, XPathConstants.NUMBER);
            System.out.println("id : " + idTicket);

            //order
            orderList = (NodeList) xpath.evaluate("//order", e, XPathConstants.NODESET);

            NodeList articleList;
            Double clientId;
            Double articleId;
            OrderTicket orderTicket;
            for(int i = 0; i < orderList.getLength(); i++) {
                clientId = (Double) xpath.evaluate("//order["+(i+1)+"]/clientId", e, XPathConstants.NUMBER);
                articleList = (NodeList) xpath.evaluate("//order["+(i+1)+"]/articleId", e, XPathConstants.NODESET);
                System.out.println("client : " + clientId.intValue());
                //loop on articles
                for(int j = 0; j < articleList.getLength(); j++) {
                    articleId = (Double) xpath.evaluate("//order["+(i+1)+"]/articleId["+(j+1)+"]", e, XPathConstants.NUMBER);
                    System.out.println("    article : " + articleId.intValue());
                    orderTicket = new OrderTicket(idTicket.intValue(), LocalDateTime.now(), clientId.intValue(), articleId.intValue());
                }
            }


            //supply



//            for(int i = 0; i < actionList.getLength(); i++) {
//                tmp = ((NodeList) xpath.evaluate("/action["+i+1+"]", actionList.item(i), XPathConstants.NODESET));
//                //nature = tmp.item(1).getTextContent();
//                System.out.println("nature " + tmp.getLength());
//            }
//            System.out.println(actionList.getLength() + " actions ");

        } catch (XPathExpressionException ex) {
            ex.printStackTrace();
        }
    }
}
