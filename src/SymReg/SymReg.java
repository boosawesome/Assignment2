package SymReg;

import org.jgap.*;
import org.jgap.impl.DefaultConfiguration;
import sun.security.krb5.Config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SymReg {

    private Point inputs[];
    private FitnessFunction function;
    private Configuration config;

    private static final int POPULATION = 100
    private static final int ALLOWED_EVOLUTIONS = 51;

    private Chromosome chrome;

    public SymReg() throws InvalidConfigurationException {
        readFile("regression.txt");

        function = new MathsFitnessFunction(inputs);
        config = new DefaultConfiguration();

        Configuration.resetProperty(Configuration.PROPERTY_FITEVAL_INST);
        config.setFitnessEvaluator(new DefaultFitnessEvaluator());
        config.getGeneticOperators().clear();

        config.setPopulationSize(POPULATION);
        config.setFitnessFunction(function);

    }

    public Point[] readFile(String s){
        List<Point> l = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File(s));
            while(scan.hasNext()){
             l.add(new Point(scan.nextDouble(), scan.nextDouble()));
             scan.nextLine();
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return (Point[]) l.toArray();
    }

    public static void main(String args[]) throws InvalidConfigurationException {
        new SymReg();
    }

}
