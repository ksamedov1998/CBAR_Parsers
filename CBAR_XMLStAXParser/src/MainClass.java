import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainClass {
    public static void main(String[] args) {
        try {
            STAX_parser parser= new STAX_parser("https://www.cbar.az/currencies/"+ LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))+".xml");
            Currencies currencies=parser.getCurrency();
            Print print=new Print(currencies);
            print.printDesc();
            print.printMetals();
            print.printCurrencies();
        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
