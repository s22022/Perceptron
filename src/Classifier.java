import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Classifier {

    private List<Data> testList, trainList;

    private double alpha;
    private double[] weights;
    private double theta;
    private HashMap<String, Integer> mapValues;

    public Classifier(List<Data> testList, List<Data> trainList, HashMap<String, Integer> mapValues, double alpha) {
        this.mapValues = mapValues;
        this.testList = testList;
        this.trainList = trainList;
        this.alpha = alpha;
        setRandomWeightsTheta();

    }

    public void setRandomWeightsTheta() { //przyjmowanie na początku losowych wartośći
        weights = new double[testList.get(0).getAttrLength()];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = (Math.random() * 2) - 1;//[-1,1]
        }
        theta = (Math.random() * 2) - 1;
    }


    public int getY(Data data) { // watość wyjścia y
        double net = 0; // iloczyn skalarny
        for (int i = 0; i < data.getAttrLength(); i++) {
            net += data.getAttributes()[i] * weights[i];
        }
        return (net >= this.theta ? 1 : 0);
    }

    //1.Pętla dla wszystkich wierszy w tranList
    //2.Pętla dla każdej

    public void learn() {


        //proces uczenia, dobranie prawidłowych wartośći wag i thety
        for (int i = 0; i < trainList.size(); i++) {


            for (Data data : trainList) {
                int yOutput = getY(data); //y= faktyczna decyzja
                //d - wyjście prawidłowe
                int correctOutput = 0;

                for (Map.Entry<String, Integer> entry : mapValues.entrySet()) {
                    if (entry.getKey().equals(data.getType())) correctOutput = entry.getValue();
                }

                int error = correctOutput - yOutput;


                for (int j = 0; j < data.getAttrLength(); j++) {
                    weights[j] += error * alpha * data.getAttributes()[j];
                }
                theta += error * alpha;
            }

        }
    }


    public void showResult() {
        int correctOutput;
        double size = testList.size();
        double predicted = 0;

        for (Data data : testList) {
            correctOutput = getY(data);

            String correctType = "";//(w testowanym przypadku nazwa kwatka)
            for (Map.Entry<String, Integer> entry : mapValues.entrySet()) {
                if (correctOutput == entry.getValue()) correctType = entry.getKey();
            }

            if (data.getType().equals(correctType)) predicted++;

            System.out.println(data + " classified type-> " + correctType);
        }
        System.out.println("Accuracy = " + predicted / size);
    }

    public void showResult(Data data) {
        int correctOutput;
        correctOutput = getY(data);

        String correctType = "";//(w testowanym przypadku nazwa kwatka)

        for (Map.Entry<String, Integer> entry : mapValues.entrySet()) {
            if (correctOutput == entry.getValue()) correctType = entry.getKey();
        }

        System.out.println(data + " classified type-> " + correctType);
    }

}
