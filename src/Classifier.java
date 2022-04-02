import java.util.List;

public class Classifier {

    List<Data> testList, trainList;
    private double alpha;
    private double[] weights;
    private double theta;

    public Classifier(List<Data> testList, List<Data> trainList, double alpha) {
        this.testList = testList;
        this.trainList = trainList;
        this.alpha = alpha;
    }

    public void setRandomWeightsTheta() { //przyjmowanie na początku losowych wartośći
        weights = new double[testList.get(0).length];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = (Math.random() * 2) - 1;//[-1,1]
        }
        theta = (Math.random() * 2) - 1;
    }


    public int getY(Data data){ // watość wyjścia y
        double net=0; // iloczyn skalarny
        for(int i =0;i<data.getAttributes().length;i++){
            net+=data.getAttributes()[i]*weights[i];
        }
        return (net>=this.theta? 1:0);
    }

    public void learn(){


    }


}
