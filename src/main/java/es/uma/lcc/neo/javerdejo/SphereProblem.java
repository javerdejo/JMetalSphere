package es.uma.lcc.neo.javerdejo;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.solution.impl.DefaultDoubleSolution;

import java.util.ArrayList;
import java.util.List;

public class SphereProblem extends AbstractDoubleProblem {

    public SphereProblem(Integer numberOfVariables) {
        setNumberOfVariables(numberOfVariables);
        setNumberOfObjectives(1);
        setName("Sphere Problem");

        List<Double> lowerLimit = new ArrayList<Double>();
        List<Double> upperLimit = new ArrayList<Double>();

        for (int i = 0; i < getNumberOfVariables(); i++) {
            lowerLimit.add(-10.0);
            upperLimit.add(10.0);
        }

        setLowerLimit(lowerLimit);
        setUpperLimit(upperLimit);
    }

    public void evaluate(DoubleSolution solution) {
        double[] x = new double[getNumberOfVariables()];
        double fx = 0;

        for (int i = 0; i < solution.getNumberOfVariables(); i++) {
            x[i] = solution.getVariableValue(i);
        }

        for (int var = 0; var < solution.getNumberOfVariables(); var++) {
            fx += x[var] * x[var];
        }

        solution.setObjective(0, -1.0 * fx);
    }

    public DoubleSolution createSolution() {
        return new DefaultDoubleSolution(this);
    }

}
