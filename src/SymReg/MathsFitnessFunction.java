package SymReg;

import org.jgap.gp.GPFitnessFunction;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.terminal.Variable;

import java.util.List;

public class MathsFitnessFunction extends GPFitnessFunction {
    private List<Float> inputs, outputs;

    /**
     *
     * @param inputs x values
     * @param outputs corresponding y values
     */
    MathsFitnessFunction(List<Float> inputs, List<Float> outputs) {
        this.inputs = inputs;
        this.outputs = outputs;
    }

    /**
     *
     * @param igpProgram the program
     * @return error rate
     */
    @Override
    protected double evaluate(IGPProgram igpProgram) {
        Variable var = igpProgram.getGPConfiguration().getVariable(MathsProblem.NAME);
        float error = 0.0f;

        for(int i = 0; i < inputs.size(); i++){
            float x = inputs.get(i);
            float y = outputs.get(i);
            var.set(x);

            float result = igpProgram.execute_float(0, null);
            error += Math.abs(result - y);

            if(Float.isInfinite(error)){
                return Double.MAX_VALUE;
            }
        }

        if(error < 0.001){
            return 0;
        }
        return error;
    }
}
