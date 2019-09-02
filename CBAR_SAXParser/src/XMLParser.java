import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class XMLParser extends DefaultHandler {

    private String name;
    private Double value;
    private String ValType;
    private boolean isName;
    private boolean isValue;
    private boolean isMaterial;
    private boolean isCurrency;

    private Currencies currency=new Currencies();


    @Override
    public void startDocument() {
        }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (qName){
            case "ValType":
                ValType=attributes.getValue("Type");
                if(ValType.equals("Bank metalları")) {
                    isMaterial=true;
                }else{
                    isCurrency=true;
                }
                break;
            case "Name":
                isName=true;
                break;
            case "Value":
                isValue=true;
                break;
            case "ValCurs":
                currency.setDesc(attributes.getValue("Description"));
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if(isName){
            name=new String(ch,start,length);
        }else if(isValue){
            value=Double.valueOf(new String(ch,start,length));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName){
            case "Name":
                isName=false;
                break;
            case "Value":
                isValue=false;
                break;
            case "ValType":
                if(isMaterial){
                    isMaterial=false;
                }else if(isCurrency){
                    isCurrency=false;
                }
                break;
            case "Valute":
                if(isMaterial){
                    currency.setMapMaterial(name,value);
                    name=null;
                    value=null;
                }else if(isCurrency){
                    currency.setMapCurrency(name,value);
                    name=null;
                    value=null;
                }
        }
    }

    @Override
    public void endDocument()  {
    }

    public Currencies getCurrency() {
        return currency;
    }
}
