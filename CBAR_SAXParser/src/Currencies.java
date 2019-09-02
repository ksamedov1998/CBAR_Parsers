import java.util.Map;
import java.util.TreeMap;

public class Currencies {
    private Map<String,Double> mapCurrency= new TreeMap<>();
    private Map<String,Double> mapMetals=new TreeMap<>();
    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Map<String, Double> getMapCurrency() {
        return mapCurrency;
    }

    public void setMapCurrency(String name,Double value) {
        mapCurrency.put(name,value);
    }

    public Map<String, Double> getMapMaterial() {
        return mapMetals;
    }

    public void setMapMaterial(String name,Double value) {
        mapMetals.put(name,value);
    }

    @Override
    public String toString() {
        return "Currencies{" +
                "mapForeign=" + mapCurrency +
                ", mapMaterial=" + mapMetals +
                '}';
    }
}
