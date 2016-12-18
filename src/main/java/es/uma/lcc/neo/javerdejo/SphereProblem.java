package es.uma.lcc.neo.javerdejo;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.solution.impl.DefaultDoubleSolution;

import java.util.ArrayList;
import java.util.List;

// Crea una clase de tipo "Problem" que resuelve el problema de la esfera
public class SphereProblem extends AbstractDoubleProblem {

    // Constructor que recive el número de variables (dimensiones) a usar
    SphereProblem(Integer numberOfVariables) {
        setNumberOfVariables(numberOfVariables);    // número de variables (dimensiones)
        setNumberOfObjectives(1);                   // Problema monoobjetivo
        setName("Sphere Problem");                  // Nombre del problema

        List<Double> lowerLimit = new ArrayList<Double>();
        List<Double> upperLimit = new ArrayList<Double>();

        // Asigno los limites superior e inferior a cada variable
        for (int i = 0; i < getNumberOfVariables(); i++) {
            lowerLimit.add(-10.0);  // NOTA: En función del problema
            upperLimit.add(10.0);
        }

        setLowerLimit(lowerLimit);
        setUpperLimit(upperLimit);
    }

    // Función de fitness
    public void evaluate(DoubleSolution solution) {
        // Almacena de forma local los valores para cada una de las variables
        // del problema "individuo"
        double[] x = new double[getNumberOfVariables()];

        // Almacena el valor de la función de fitness
        double fx = 0;

        // Guardo los valores de las variables del individuo en un arreglo local
        for (int i = 0; i < solution.getNumberOfVariables(); i++) {
            x[i] = solution.getVariableValue(i);
        }

        // Calculo el valor de fitness (x^2)
        for (int var = 0; var < solution.getNumberOfVariables(); var++) {
            fx += x[var] * x[var];
        }

        // Asigno el valor de la función de fitness al primer objetivo ya que se trata
        // de un problema mono-objetico
        solution.setObjective(0, -1.0 * fx);
    }

    // Esta función es utilizada por el algoritmo para crear la población inicial
    public DoubleSolution createSolution() {
        return new DefaultDoubleSolution(this);
    }

}
