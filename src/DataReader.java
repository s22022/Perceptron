import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataReader {
    private ArrayList<Data> dataList;

    public DataReader(String path) {
        dataList = new ArrayList<>();
        try {
            String newLine;
            BufferedReader reader = new BufferedReader(new FileReader(path));
            reader.readLine(); // skip first line

            while ((newLine = reader.readLine()) != null) {
                String[] data = newLine.split(",");
                double[] attr = Arrays.stream(data).filter(DataReader::isNumeric).mapToDouble(Double::parseDouble).toArray();
                dataList.add(new Data(data[data.length - 1], attr));

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


}
