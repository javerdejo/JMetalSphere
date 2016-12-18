package es.uma.lcc.neo.javerdejo;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.singleobjective.geneticalgorithm.GeneticAlgorithmBuilder;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import org.uma.jmetal.operator.impl.crossover.SBXCrossover;
import org.uma.jmetal.operator.impl.mutation.PolynomialMutation;
import org.uma.jmetal.operator.impl.selection.BinaryTournamentSelection;
import org.uma.jmetal.problem.DoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.AlgorithmRunner;
import org.uma.jmetal.util.JMetalException;
import org.uma.jmetal.util.JMetalLogger;
import org.uma.jmetal.util.comparator.RankingComparator;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.uma.jmetal.runner.AbstractAlgorithmRunner.printFinalSolutionSet;

// Programa en JMetal que resuelve el problema de la esfera
public class SphereAlgorithm {
    public static void main(String[] args) throws JMetalException, FileNotFoundException {

        // Crea un objeto de tipo algoritmo
        Algorithm<DoubleSolution> algorithm;

        // Crea un objeto de tipo problema que hace referencia a la clase que se ha creado
        // para resolver el problema de la esfera
        DoubleProblem problem = new SphereProblem(5);

        // Utilizo cruza de tipo SBX
        CrossoverOperator<DoubleSolution> crossover = new SBXCrossover(0.9, 20.0) ;
        // Utilizo mutación polinomial
        MutationOperator<DoubleSolution> mutation  = new PolynomialMutation(1.0 / problem.getNumberOfVariables(), 20.0) ;
        // Utilizo selección por torneo binario
        SelectionOperator<List<DoubleSolution>, DoubleSolution> selection = new BinaryTournamentSelection<DoubleSolution>(new RankingComparator<DoubleSolution>());

        // Uso un algoritmo genético de población estacionaria "SSGA"
        algorithm = new GeneticAlgorithmBuilder<DoubleSolution>(problem, crossover, mutation)
                .setSelectionOperator(selection)
                .setMaxEvaluations(25000)
                .setPopulationSize(100)
                .setVariant(GeneticAlgorithmBuilder.GeneticAlgorithmVariant.STEADY_STATE)
                .build();

        // Ejecuto el algoritmo
        AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(algorithm)
                .execute();

        // Obtengo el resultado obtenido por el algoritmo
        DoubleSolution solution = algorithm.getResult();

        // Asigno la solución a una población para poder usar al función printFinalSolutionSet
        List<DoubleSolution> population = new ArrayList<DoubleSolution>(1);
        population.add(solution);

        // Muestro el tiempo que tardó el algoritmo
        long computingTime = algorithmRunner.getComputingTime();
        JMetalLogger.logger.info("Total execution time: " + computingTime + "ms");

        // Creo los archivos FUN y VAR con los resultados obtenidos
        printFinalSolutionSet(population);

    }
}
