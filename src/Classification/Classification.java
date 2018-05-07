package Classification;

import org.apache.log4j.PropertyConfigurator;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.impl.GPGenotype;
import org.jgap.InvalidConfigurationException;
import org.jgap.gp.GPProblem;
import org.jgap.gp.impl.DefaultGPFitnessEvaluator;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.terminal.Variable;

import java.util.List;

public class Classification {

    private static final int POPULATION = 800;
    private static final int EVOLUTIONS = 600;

    /**
     *
     * @throws InvalidConfigurationException
     */
    private Classification() throws InvalidConfigurationException {
        PropertyConfigurator.configure("log4j.properties");

        CancerList cl = Processor.split(Processor.readFile());
        List<Cancer> trainingSet = cl.getX();
        List<Cancer> testSet = cl.getY();

        GPConfiguration config = new GPConfiguration();
        config.setMaxCrossoverDepth(10);
        config.setMaxInitDepth(5);

        config.setGPFitnessEvaluator(new DefaultGPFitnessEvaluator());
        config.setPopulationSize(POPULATION);
        config.setFitnessFunction(new ClassFitnessFunction(trainingSet));
        config.setStrictProgramCreation(true);

        GPProblem problem = new ClassProblem(config);
        GPGenotype gp = problem.create();
        gp.setVerboseOutput(true);

        gp.evolve(EVOLUTIONS);

        IGPProgram bestProg = gp.getAllTimeBest();
        gp.outputSolution(bestProg);

        float accTraining = accuracy(bestProg, trainingSet);
        float accTest = accuracy(bestProg, testSet);

        System.out.println("**********************************");
        System.out.println("Training Set Accuracy: " + accTraining * 100);
        System.out.println("Test Set Accuracy: " + accTest * 100);

    }

    /**
     *
     * @param igpProgram
     * @param set
     * @return
     */
    private float accuracy(IGPProgram igpProgram, List<Cancer> set) {
        Variable F1 = igpProgram.getGPConfiguration().getVariable(ClassProblem.F1);
        Variable F2 = igpProgram.getGPConfiguration().getVariable(ClassProblem.F2);
        Variable F3 = igpProgram.getGPConfiguration().getVariable(ClassProblem.F3);
        Variable F4 = igpProgram.getGPConfiguration().getVariable(ClassProblem.F4);
        Variable F5 = igpProgram.getGPConfiguration().getVariable(ClassProblem.F5);
        Variable F6 = igpProgram.getGPConfiguration().getVariable(ClassProblem.F6);
        Variable F7 = igpProgram.getGPConfiguration().getVariable(ClassProblem.F7);
        Variable F8 = igpProgram.getGPConfiguration().getVariable(ClassProblem.F8);
        Variable F9 = igpProgram.getGPConfiguration().getVariable(ClassProblem.F9);

        int totalNum = set.size();

        float numCorrect = 0.0f;

        for (Cancer c : set) {

            F1.set((float) c.features[0]);
            F2.set((float) c.features[1]);
            F3.set((float) c.features[2]);
            F4.set((float) c.features[3]);
            F5.set((float) c.features[4]);
            F6.set((float) c.features[5]);
            F7.set((float) c.features[6]);
            F8.set((float) c.features[7]);
            F9.set((float) c.features[8]);


            double result = igpProgram.execute_float(0, null);


            if ((result >= 0 && c.isMalignant())

                    || (result < 0 && c.isBenign())) {

                numCorrect++;

            }

        }

        return numCorrect / totalNum;

    }

    /**
     *
     * @param args
     * @throws InvalidConfigurationException
     */
    public static void main(String[] args) throws InvalidConfigurationException {
        new Classification();
    }
}
