import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // write your code here

        DataReader dataReader = new DataReader("data/iristest.csv", "\"setosa\"", "\"versicolor\"");
        DataReader dataReader2 = new DataReader("data/iristrain.csv", "\"setosa\"", "\"versicolor\"");
        Classifier classifier = new Classifier(dataReader.getDataList(), dataReader2.getDataList(), dataReader2.getMapValues(), 0.4);
        classifier.learn();
        classifier.showResult();


        System.out.println("---------------User input-------------------");
        String input = "";

        while (true) {
            System.out.println("Podaj atrybuty wektoru po przecinku np-x1,x2,x3,x4...");
            input = scanner.nextLine();
            if (input.equals("exit")) break;
            try {
                double[] attributes = Arrays.stream(input.split(",")).mapToDouble(Double::parseDouble).toArray();
                Data data = new Data("?", attributes);
                classifier.showResult(data);
            } catch (Exception e) {
                System.out.println("Zły input");
            }


        }
    }
}
