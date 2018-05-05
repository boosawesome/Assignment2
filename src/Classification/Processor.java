package Classification;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Processor {

    private static final String FILE = "breast-cancer-wisconsin.data";
    private static final float SPLIT_PROPORTION = 0.8f;

    public static List<Cancer> readFile() {
        List<Cancer> returnable = new ArrayList<>();

        try {
            Scanner scan = new Scanner(new File(FILE));
            while (scan.hasNext()) {
                while (scan.hasNextLine()) {
                    List<String> values = new ArrayList<>(Arrays.asList(scan.nextLine().split(",")));
                    int id = Integer.parseInt(values.remove(0));
                    int label = Integer.parseInt(values.remove(values.size() - 1));
                    int[] features = values.stream()
                            .mapToInt(value -> Integer.parseInt(value.equals("?") ? "-1" : value))
                            .toArray();
                    returnable.add(new Cancer(id, features, label));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return returnable;
    }

    public static CancerList split(List<Cancer> patients) {
        List<Cancer> benign = new ArrayList<>();
        List<Cancer> malign = new ArrayList<>();
        List<Cancer> train = new ArrayList<>();
        List<Cancer> test = new ArrayList<>();

        for (Cancer c : patients) {
            if (c.isBenign()) {
                benign.add(c);
            } else if (c.isMalignant()) {
                malign.add(c);
            }
        }

        Collections.shuffle(benign);
        Collections.shuffle(malign);

        for (int i = 0; i < benign.size(); i++) {
            train.add(benign.get(i));
            i++;
            if (i < benign.size()) test.add(benign.get(i));
        }

        for (int x = 0; x < malign.size(); x++) {
            train.add(malign.get(x));
            x++;
            if (x < malign.size()) test.add(malign.get(x));
        }

        Collections.shuffle(train);
        Collections.shuffle(test);

        return new CancerList(train, test);
    }

}

class CancerList {
    List<Cancer> x, y;

    CancerList(List<Cancer> x, List<Cancer> y) {
        this.x = x;
        this.y = y;
    }

    List<Cancer> getX() {
        return x;
    }

    List<Cancer> getY() {
        return y;
    }
}
