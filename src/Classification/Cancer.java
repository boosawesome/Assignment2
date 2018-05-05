package Classification;

import java.util.Arrays;

public class Cancer {

    private static final int BENIGN = 2;
    private static final int MALIGNANT = 4;

    public final int id;
    public final int[] features;
    public final int label;


    public Cancer(int id, int[] features, int label) {
        if (insanityCheck(features, label)) {
            throw new IllegalArgumentException(("Wrong values Label: " + label + " | Features: " + Arrays.toString(features)));
        }

        this.id = id;
        this.features = features;
        this.label = label;
    }

    private boolean insanityCheck(int[] features, int label) {
        return features.length != 9
                || Arrays.stream(features).anyMatch(value -> value == 0 || value < -1 || value > 10)
                || (label != BENIGN && label != MALIGNANT);
    }

    public boolean isBenign(){
        return label == BENIGN;
    }

    public boolean isMalignant(){
        return label == MALIGNANT;
    }

    public String toString(){
        return "";
    }

}
