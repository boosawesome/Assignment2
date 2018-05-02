package SymReg;

import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.IChromosome;
import org.jgap.impl.IntegerGene;

import java.util.ArrayList;
import java.util.List;

public class MathsFitnessFunction extends FitnessFunction{
    Point[] input;

    public MathsFitnessFunction(Point[] input){
        this.input = input;
    }

    @Override
    protected double evaluate(IChromosome iChromosome) {
        double currentX, currentY, error;
        List<Op> eq = new ArrayList();

        for(int i = 0; i < iChromosome.size(); i++){
            Gene gene = iChromosome.getGene(i);
            eq.add((Op) gene.getAllele());
        }

        for(Op o: eq){

        }
        return 0;
    }
}
