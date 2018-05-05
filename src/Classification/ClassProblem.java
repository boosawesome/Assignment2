package Classification;

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

public class ClassProblem extends GPProblem {
    public static final String F1 = "1CT";
    public static final String F2 = "2UCZ";
    public static final String F3 = "3UCS";
    public static final String F4 = "4MA";
    public static final String F5 = "5SECZ";
    public static final String F6 = "6BN";
    public static final String F7 = "7BC";
    public static final String F8 = "8NN";
    public static final String F9 = "9M";

    private static final float MIN_TERMINAL = 0f;
    private static final float MAX_TERMINAL = 10f;
    private static final int MAX_NODES = 40;


    public ClassProblem(GPConfiguration config) throws InvalidConfigurationException {
        super(config);
    }

    @Override
    public GPGenotype create() throws InvalidConfigurationException {
        GPConfiguration config = getGPConfiguration();

        Class f = CommandGene.FloatClass;

        Class[] types = {f};
        Class[][] argTypes = {{}};

        CommandGene[][] nodes = {
            {
                Variable.create(config, F1, f),
                        Variable.create(config, F2, f),
                        Variable.create(config, F3, f),
                        Variable.create(config, F4, f),
                        Variable.create(config, F5, f),
                        Variable.create(config, F6, f),
                        Variable.create(config, F7, f),
                        Variable.create(config, F8, f),
                        Variable.create(config, F9, f),


                        new Terminal(config, f, MIN_TERMINAL, MAX_TERMINAL, false),
                        new Add(config, f),
                        new Subtract(config, f),
                        new Multiply(config, f),
                        new Divide(config, f)
            }
        } ;

        return GPGenotype.randomInitialGenotype(config, types, argTypes, nodes, MAX_NODES, true);
    }
}
