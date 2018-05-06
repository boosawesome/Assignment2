package SymReg;

import org.jgap.*;
import org.jgap.gp.GPFitnessFunction;
import org.jgap.gp.GPProblem;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.impl.DeltaGPFitnessEvaluator;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class SymReg {

    private static final int POPULATION = 600;
    private static final int ALLOWED_EVOLUTIONS = 400;

    private static final String FILE = "regression.txt";


    /**
     *
     * @param s - file name
     * @return Fitness Function
     */
    private GPFitnessFunction readFile(String s){//scans data in from text file

        List<Float> inputs = new ArrayList<>();
        List<Float> outputs = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File(s));
            while(scan.hasNext()){
                inputs.add((float) scan.nextDouble());
                outputs.add((float) scan.nextDouble());
                scan.nextLine();
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new MathsFitnessFunction(inputs, outputs);

    }

    /**
     *
     * @throws InvalidConfigurationException
     */
    private SymReg() throws InvalidConfigurationException {// builds and then runs program
        PropertyConfigurator.configure("log4j.properties");

        GPFitnessFunction function = readFile(FILE);
        GPConfiguration config = new GPConfiguration();

        config.setMaxCrossoverDepth(10);
        config.setMaxInitDepth(5);

        config.setGPFitnessEvaluator(new DeltaGPFitnessEvaluator());
        config.setPopulationSize(POPULATION);
        config.setFitnessFunction(function);
        config.setStrictProgramCreation(true);

        GPProblem problem = new MathsProblem(config);
        GPGenotype gp = problem.create();
        gp.setVerboseOutput(true);

        gp.evolve(ALLOWED_EVOLUTIONS);

        IGPProgram bestProg = gp.getAllTimeBest();
        gp.outputSolution(bestProg);
    }


    /**
     *
     * @param args command line arguments
     * @throws InvalidConfigurationException
     */
    public static void main(String args[]) throws InvalidConfigurationException {
        new SymReg();
    }

}
