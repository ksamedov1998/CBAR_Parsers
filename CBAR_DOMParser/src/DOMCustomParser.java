import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class DOMCustomParser {
    private String name;
    private Double value;
    private String url;
    private Currencies currency= new Currencies();

    public DOMCustomParser(String URI) throws IOException, XMLStreamException {
        url=URI;

    }

    public void Parse() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
        DocumentBuilder builder=factory.newDocumentBuilder();
        Document document = builder.parse(url);
        document.normalizeDocument();

        Element root= document.getDocumentElement();
        Element desc= (Element) document.getElementsByTagName("ValCurs").item(0);
        currency.setDesc(desc.getAttribute("Description"));

        NodeList valType=document.getElementsByTagName("ValType");
        for(int i=0;i<valType.getLength();i++){
                Element valTypeElement=(Element) valType.item(i);
            if(valTypeElement.getAttribute("Type").equals("Bank metalları")){

                NodeList valutes= valTypeElement.getElementsByTagName("Valute");
                    for(int j=0;j<valutes.getLength();j++){
                        Element valute= (Element) valutes.item(j);
                       name=((Element) valute.getElementsByTagName("Name").item(0)).getTextContent();
                       value=Double.parseDouble(((Element) valute.getElementsByTagName("Value").item(0)).getTextContent());
                        currency.setMapMetals(name,value);
                    }
            }else if(valTypeElement.getAttribute("Type").equals("Xarici valyutalar")){
                NodeList valutes= valTypeElement.getElementsByTagName("Valute");
                for(int j=0;j<valutes.getLength();j++){
                    Element valute= (Element) valutes.item(j);
                    name=((Element) valute.getElementsByTagName("Name").item(0)).getTextContent();
                    value=Double.parseDouble(((Element) valute.getElementsByTagName("Value").item(0)).getTextContent());
                    currency.setMapCurrency(name,value);
            }


            }
    }

    }

    public Currencies getCurrency() {
        return currency;
    }
}
