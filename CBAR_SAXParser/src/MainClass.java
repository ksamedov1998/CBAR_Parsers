import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


public class MainClass {
    public static void main(String[] args) throws ParserConfigurationException,SAXException {
        SAXParserFactory SaxParser= SAXParserFactory.newInstance();
        XMLParser p=null;
        SAXParser parser=null;
        try {
             parser= SaxParser.newSAXParser();
             p= new XMLParser();
             parser.parse("https://www.cbar.az/currencies/"+LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))+".xml",p); } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Print print=new Print(p.getCurrency());
        print.printDesc();
        print.printMetals();
        print.printCurrencies();

    }
}
