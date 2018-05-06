package SymReg;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.GPProblem;
import org.jgap.gp.function.Add;
import org.jgap.gp.function.Divide;
import org.jgap.gp.function.Multiply;
import org.jgap.gp.function.Subtract;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;
import org.jgap.gp.terminal.Terminal;
import org.jgap.gp.terminal.Variable;

public class MathsProblem extends GPProblem {

    public static final String NAME = "X";

    private static final float MAX_TERMINAL = 0.0f;
    private static final float MIN_TERMINAL = 2.0f;
    private static final int MAX_NODES = 40;

    /**
     *
     * @param config Configuration
     * @throws InvalidConfigurationException
     */
    MathsProblem(GPConfiguration config) throws InvalidConfigurationException {
        super(config);
    }

    /**
     *
     * @return Genotype
     * @throws InvalidConfigurationException
     */
    @Override
    public GPGenotype create() throws InvalidConfigurationException {
        GPConfiguration config = getGPConfiguration();

        Class c = CommandGene.FloatClass;

        Class[] types = {c};

        Class[][] argTypes = {{}};

        CommandGene[][] nodes = {
                {
                        Variable.create(config, NAME, c),
                        new Terminal(config, c, MIN_TERMINAL, MAX_TERMINAL, true),
                        new Add(config, c),
                        new Subtract(config, c),
                        new Multiply(config, c),
                        new Divide(config, c)
                }
        };
        return GPGenotype.randomInitialGenotype(config, types, argTypes, nodes, MAX_NODES, true);
    }
}
