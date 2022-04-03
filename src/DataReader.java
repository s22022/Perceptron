import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DataReader {
    private ArrayList<Data> dataList;
    private HashMap<String,Integer> mapValues = new HashMap<>(); // hashmapa to przechiwywania wartości wyjściowej


    public DataReader(String path,String key1,String key2) {
        dataList = new ArrayList<>();

        try {
            String newLine;
            BufferedReader reader = new BufferedReader(new FileReader(path));
            reader.readLine(); // skip first line

            while ((newLine = reader.readLine()) != null) {
                String[] data = newLine.split(",");
                double[] attr = Arrays.stream(data).filter(DataReader::isNumeric).mapToDouble(Double::parseDouble).toArray();
                if(data[data.length-1].equals(key1) ||data[data.length-1].equals(key2) ) {
                    dataList.add(new Data(data[data.length - 1], attr));
                    mapValues.put(data[data.length - 1], null);
                }

            }
            int id=0;
            for(Map.Entry<String,Integer> entry : mapValues.entrySet()){
                entry.setValue(id++);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public List<Data> getDataList() {
        return this.dataList;
    }

    public  HashMap<String,Integer> getMapValues(){
        return this.mapValues;
    }
}
