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

public class SphereAlgorithm {
    public static void main(String[] args) throws JMetalException, FileNotFoundException {

        Algorithm<DoubleSolution> algorithm;
        DoubleProblem problem = new SphereProblem(5);

        CrossoverOperator<DoubleSolution> crossover = new SBXCrossover(0.9, 20.0) ;
        MutationOperator<DoubleSolution> mutation  = new PolynomialMutation(1.0 / problem.getNumberOfVariables(), 20.0) ;
        SelectionOperator<List<DoubleSolution>, DoubleSolution> selection = new BinaryTournamentSelection<DoubleSolution>(new RankingComparator<DoubleSolution>());

        algorithm = new GeneticAlgorithmBuilder<DoubleSolution>(problem, crossover, mutation)
                .setSelectionOperator(selection)
                .setMaxEvaluations(25000)
                .setPopulationSize(100)
                .setVariant(GeneticAlgorithmBuilder.GeneticAlgorithmVariant.STEADY_STATE)
                .build();

        AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(algorithm)
                .execute();

        DoubleSolution solution = algorithm.getResult();
        List<DoubleSolution> population = new ArrayList<DoubleSolution>(1);
        population.add(solution);

        long computingTime = algorithmRunner.getComputingTime();
        JMetalLogger.logger.info("Total execution time: " + computingTime + "ms");

        printFinalSolutionSet(population);

    }
}
