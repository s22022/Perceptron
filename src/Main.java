public class Main {

    public static void main(String[] args) {
	// write your code here

        DataReader dataReader = new DataReader("data/iristest.csv","\"setosa\"","\"versicolor\"");
        DataReader dataReader2 = new DataReader("data/iristrain.csv","\"setosa\"","\"versicolor\"");

        Classifier classifier = new Classifier(dataReader.getDataList(),dataReader2.getDataList(),dataReader2.getMapValues(),0.8);
        classifier.learn();
        classifier.showResult();



    }
}
