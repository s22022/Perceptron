import java.util.Arrays;

public class Data {

    private double[] attributes;
    private String type;
    public Data(String type, double[] attributes) {
        this.type = type;
        this.attributes = attributes;
    }


    public double[] getAttributes() {
        return attributes;
    }

    public String getType() {
        return type;
    }

    public String toString() {
        return getType() + Arrays.toString(getAttributes());
    }

    public int getAttrLength(){
        return this.attributes.length;
    }

}
