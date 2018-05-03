package SymReg;

import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.IChromosome;

import java.util.ArrayList;
import java.util.List;

public class MathsFitnessFunction extends FitnessFunction {
    Point[] input;

    public MathsFitnessFunction(Point[] input) {
        this.input = input;
    }

    @Override
    protected double evaluate(IChromosome iChromosome) {
        Gene[] genes = iChromosome.getGenes();
        Op[] eq = new Op[genes.length];
        for (int i = 0; i < genes.length; i++) {
            eq[i] = (Op) genes[i].getAllele();
        }

        double sum = 0;
        for (int x = 0; x < input.length; x++) {
            Point p = input[x];
            sum = runEq(p, eq);
            sum = sum / input.length;
        }

        return sum;
    }

    public double runEq(Point p, Op[] eq) {

        double sum = 0;
        double error;
        double tmp;
        for (int i = 0; i < eq.length; i++) {
            tmp = eq[i].function(p.getX());

            sum = sum + tmp;
        }
        error = p.getY() - sum;
        return error * error;
    }
}
