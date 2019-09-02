import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class Print {
    private Currencies currencies;
    public Print(Currencies currencie) {
        this.currencies=currencie;
    }

    public void printDesc(){
        System.out.println(currencies.getDesc());
    }
    public void printMetals(){
        Set<String> setofNames=currencies.getMapMetals().keySet();
        List<String> listofName=new ArrayList<>();
        for(String name:setofNames){
            listofName.add(name);
        }
        Collection<Double> listofValues=currencies.getMapMetals().values();
        Double[] arrofValues=listofValues.toArray(new Double[0]);
        System.out.println("-".repeat(70)+"\nBank metals");
        for(int i=0;i<listofName.size();i++){
            System.out.println("-".repeat(70));
            System.out.println(listofName.get(i)+" ".repeat(10)+":"+" ".repeat(10)+arrofValues[i]);
        }
        System.out.println();
    }
    public void printCurrencies(){
        Set<String> setofNames=currencies.getMapCurrency().keySet();
        List<String> listofName=new ArrayList<>();
        for(String name:setofNames){
            listofName.add(name);
        }
        Collection<Double> listofValues=currencies.getMapCurrency().values();
        Double[] arrofValues=listofValues.toArray(new Double[0]);
        System.out.println("-".repeat(70)+"\nCurrencies");
        for(int i=0;i<listofName.size();i++){
            System.out.println("-".repeat(70));
            System.out.println(listofName.get(i)+" ".repeat(10)+":"+" ".repeat(10)+arrofValues[i]);
        }
        System.out.println();
    }
}
