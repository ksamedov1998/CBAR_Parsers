import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class STAX_parser {
    private String name;
    private Double value;
    private String ValType;
    private String elementName;
    private String text;
    private boolean isName;
    private boolean isValue;
    private boolean isMaterial;
    private boolean isCurrency;
    private Currencies currency= new Currencies();

    public Currencies getCurrency() {
        return currency;
    }

    public STAX_parser(String URI) throws MalformedURLException, IOException, XMLStreamException {
        URL url=new URL(URI);
        XMLInputFactory factory= XMLInputFactory.newInstance();
        InputStream in = url.openStream();
        XMLStreamReader reader= factory.createXMLStreamReader(in);

        while(reader.hasNext()){
            int event= reader.next();
            switch (event){
                case XMLEvent.START_DOCUMENT:
                    break;
                case XMLEvent.START_ELEMENT:
                    elementName=reader.getLocalName();
                    if(elementName.equals("ValType")){
                        ValType=reader.getAttributeValue("","Type");
                        if(ValType.equals("Bank metalları")) {
                            isMaterial=true;
                        }else{
                            isCurrency=true;
                        }
                    }else if(elementName.equals("Name")){
                        isName=true;
                    }else if(elementName.equals("Value")){
                        isValue=true;
                    }else if(elementName.equals("ValCurs")){
                        currency.setDesc(reader.getAttributeValue("","Description"));
                    }
                    break;
                case XMLEvent.CHARACTERS:
                    text=reader.getText();
                    if(isName){
                     name=reader.getText();
                    }else if (isValue){
                        value=Double.parseDouble(reader.getText());
                    }
                    break;
                case XMLEvent.END_ELEMENT:
                    elementName=reader.getLocalName();
                    if(elementName.equals("Name")){
                        isName=false;
                    }else if (elementName.equals("Value")){
                        isValue=false;
                    }else if(elementName.equals("Valute")){
                        if(isMaterial){
                            currency.setMapMetals(name,value);
                        }else if(isCurrency){
                            currency.setMapCurrency(name,value);
                        }
                        name=null;
                        value=null;
                    }else if(elementName.equals("ValType")){
                        isCurrency=isMaterial=false;
                    }
                    break;
                case XMLEvent.END_DOCUMENT:
                    break;
            }
        }


    }
}
