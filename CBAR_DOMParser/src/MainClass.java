import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainClass {

    public static void main(String[] args) {
        DOMCustomParser parser = null;
        Currencies currencies;
        Print print;
        try {
                parser = new DOMCustomParser("https://www.cbar.az/currencies/" +
                        LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + ".xml");

            parser.Parse();
            currencies = parser.getCurrency();
            print = new Print(currencies);
            print.printDesc();
            print.printMetals();
            print.printCurrencies();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }catch (XMLStreamException e) {
            e.printStackTrace();
        }

    }

}

