package Classification;

import java.util.Arrays;

public class Cancer {

    private static final int BENIGN = 2;
    private static final int MALIGNANT = 4;

    private final int id; //Not actually required, I just prefer to store it still
    public final int[] features;
    private final int label;

    /**
     *
     * @param id
     * @param features
     * @param label
     */
    Cancer(int id, int[] features, int label) {
        if (insanityCheck(features, label)) {
            throw new IllegalArgumentException(("Wrong values Label: " + label + " | Features: " + Arrays.toString(features)));
        }

        this.id = id;
        this.features = features;
        this.label = label;
    }

    /**
     *
     * @param features
     * @param label
     * @return
     */
    private boolean insanityCheck(int[] features, int label) {
        return features.length != 9
                || Arrays.stream(features).anyMatch(value -> value == 0 || value < -1 || value > 10)
                || (label != BENIGN && label != MALIGNANT);
    }

    /**
     *
     * @return
     */
    public boolean isBenign(){
        return label == BENIGN;
    }

    /**
     *
     * @return
     */
    public boolean isMalignant(){
        return label == MALIGNANT;
    }

    /**
     *
     * @return
     */
    public String toString(){
        return "";
    }

}
