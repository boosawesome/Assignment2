package Classification;

import org.jgap.gp.GPFitnessFunction;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.terminal.Variable;

import java.util.List;

public class ClassFitnessFunction extends GPFitnessFunction {

    private List<Cancer> trainingSet;

    /**
     *
     * @param trainingSet
     */
    ClassFitnessFunction(List<Cancer> trainingSet) {
        this.trainingSet = trainingSet;


    }

    /**
     *
     * @param igpProgram
     * @return
     */
    @Override
    protected double evaluate(IGPProgram igpProgram) {
        Variable f1 = igpProgram.getGPConfiguration().getVariable(ClassProblem.F1);
        Variable f2 = igpProgram.getGPConfiguration().getVariable(ClassProblem.F2);
        Variable f3 = igpProgram.getGPConfiguration().getVariable(ClassProblem.F3);
        Variable f4 = igpProgram.getGPConfiguration().getVariable(ClassProblem.F4);
        Variable f5 = igpProgram.getGPConfiguration().getVariable(ClassProblem.F5);
        Variable f6 = igpProgram.getGPConfiguration().getVariable(ClassProblem.F6);
        Variable f7 = igpProgram.getGPConfiguration().getVariable(ClassProblem.F7);
        Variable f8 = igpProgram.getGPConfiguration().getVariable(ClassProblem.F8);
        Variable f9 = igpProgram.getGPConfiguration().getVariable(ClassProblem.F9);

        float truePositive = 0;
        float falsePositive = 0;
        float falseNegative = 0;

        for (Cancer c : trainingSet) {
            f1.set((float) c.features[0]);
            f2.set((float) c.features[1]);
            f3.set((float) c.features[2]);
            f4.set((float) c.features[3]);
            f5.set((float) c.features[4]);
            f6.set((float) c.features[5]);
            f7.set((float) c.features[6]);
            f8.set((float) c.features[7]);
            f9.set((float) c.features[8]);

            double result = igpProgram.execute_float(0, null);

            if (Double.isNaN(result)) {
                return result;
            }

            if (result >= 0 && c.isMalignant()) {
                truePositive++;
            } else if (result >= 0 && c.isBenign()) {
                falsePositive++;
            } else if (result < 0 && c.isMalignant()) {
                falseNegative++;
            }
        }

        float precision = truePositive / (truePositive + falsePositive);
        float recall = truePositive / (truePositive + falseNegative);

        precision = Float.isNaN(precision) ? 0 : precision;
        recall = Float.isNaN(recall) ? 0 : recall;

        return 100 * (2f * precision * recall) / (precision + recall);
    }
}
