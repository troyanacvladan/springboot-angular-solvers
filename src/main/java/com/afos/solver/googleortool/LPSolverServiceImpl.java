package com.afos.solver.googleortool;

import com.afos.solver.SolverService;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;



public class LPSolverServiceImpl implements SolverService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    static {
        System.loadLibrary("jniortools");
    }

    @Override
    public boolean execute() {
        return executeLPSolverAFOSv1Example();
    }

    private boolean executeLPSolverAFOSv1Example(){
        System.out.println("***LP calculation for AFOS v1 Cat formula***");

        MPSolver solver = new MPSolver(
                "LinearProgrammingExample", MPSolver.OptimizationProblemType.GLOP_LINEAR_PROGRAMMING);

        try {

            double x1Min = 30;
            double x1Max = 100;
            double x2Min = 10;
            double x2Max = 100;
            double x3Min = 20;
            double x3Max = 80;


            // 1. Create the variables.
            // There are ingredients percentage variable (continuous non-negative variables):
            MPVariable x1 = solver.makeNumVar(x1Min, x1Max, "Oats");//min and max value
            MPVariable x2 = solver.makeNumVar(x2Min, x2Max, "CornMaize");
            MPVariable x3 = solver.makeNumVar(x3Min, x3Max, "FishMeal");

            //2. Define the constraints.
            //There are nutrients percentage constraints:
            MPConstraint c1 = solver.makeConstraint(100*60, 100*100, "DryMatter");//min and max value multiplier with 100% to get absolute values
            c1.setCoefficient(x1, 87);
            c1.setCoefficient(x2, 87);
            c1.setCoefficient(x3, 92);
            MPConstraint c2 = solver.makeConstraint(100*20, 100*100, "CrudeProtein");
            c2.setCoefficient(x1, 10.5);
            c2.setCoefficient(x2, 9);
            c2.setCoefficient(x3, 59);
            MPConstraint c3 = solver.makeConstraint(100*0, 20*100, "Fat");
            c3.setCoefficient(x1, 5);
            c3.setCoefficient(x2, 4);
            c3.setCoefficient(x3, 9);
            //main constraint (ingredients total results (in [%]) must be equal to 100%):
            MPConstraint c4 = solver.makeConstraint(100, 100, "MainConstraint");
            c4.setCoefficient(x1, 1);
            c4.setCoefficient(x2, 1);
            c4.setCoefficient(x3, 1);


            //3. Define the objective function.
            //There are ingredients price per unit and their total price should be minimize:
            MPObjective objective = solver.objective();
            objective.setCoefficient(x1, 9.5); //price per ingredient unit
            objective.setCoefficient(x2, 94.3);
            objective.setCoefficient(x3, 5.05);
            objective.setMinimization();

            //4. Invoke the solver
            final MPSolver.ResultStatus resultStatus = solver.solve();
            // Check that the problem has an optimal solution.
            if (resultStatus != MPSolver.ResultStatus.OPTIMAL) {
                System.err.println("The problem does not have an optimal solution!");
                return false;
            }

            // The value of each variable in the solution.
            System.out.println("Solution (ingredients results):");
            System.out.println(x1.name() + " = " + round(x1.solutionValue(),2) + " [%]");
            System.out.println(x2.name() + " = " + round(x2.solutionValue(),2) + " [%]");
            System.out.println(x3.name() + " = " + round(x3.solutionValue(),2) + " [%]");

            // The objective value of the solution.
            System.out.println("Total price = " + round(solver.objective().value()/100,2));



        }catch (Exception ex){
            return false;
        }

        return true;
    }

    private boolean executeLPSolverAFOSv1Example2(){
        System.out.println("***LP calculation for AFOS v1 Cat formula***");

        MPSolver solver = new MPSolver(
                "LinearProgrammingExample", MPSolver.OptimizationProblemType.GLOP_LINEAR_PROGRAMMING);

        try {
            // 1. Create the variables.
            // There are ingredients percentage variable (continuous non-negative variables):
            MPVariable x1 = solver.makeNumVar(0, 100, "Oats");
            MPVariable x2 = solver.makeNumVar(0, 100, "CornMaize");
            MPVariable x3 = solver.makeNumVar(0, 100, "FishMeal");

            //2. Define the constraints.
            //There are ingredients constraints:
            MPConstraint c01 = solver.makeConstraint(30, 100, "c01"); ;//min and max value
            c01.setCoefficient(x1, 1);
            c01.setCoefficient(x2, 0);
            c01.setCoefficient(x3, 0);
            MPConstraint c02 = solver.makeConstraint(10, 100, "c02");
            c02.setCoefficient(x1, 0);
            c02.setCoefficient(x2, 1);
            c02.setCoefficient(x3, 0);
            MPConstraint c03 = solver.makeConstraint(20, 80, "c03");
            c03.setCoefficient(x1, 0);
            c03.setCoefficient(x2, 0);
            c03.setCoefficient(x3, 1);
            //There are nutrients percentage constraints:
            MPConstraint c1 = solver.makeConstraint(100*60, 100*100, "DryMatter");//min and max value multiplier with 100% to get absolute values
            c1.setCoefficient(x1, 87);
            c1.setCoefficient(x2, 87);
            c1.setCoefficient(x3, 92);
            MPConstraint c2 = solver.makeConstraint(100*20, 100*100, "CrudeProtein");
            c2.setCoefficient(x1, 10.5);
            c2.setCoefficient(x2, 9);
            c2.setCoefficient(x3, 59);
            MPConstraint c3 = solver.makeConstraint(100*0, 20*100, "Fat");
            c3.setCoefficient(x1, 5);
            c3.setCoefficient(x2, 4);
            c3.setCoefficient(x3, 9);
            //main constraint (ingredients total results (in [%]) must be equal to 100%):
            MPConstraint c4 = solver.makeConstraint(100, 100, "MainConstraint");
            c4.setCoefficient(x1, 1);
            c4.setCoefficient(x2, 1);
            c4.setCoefficient(x3, 1);


            //3. Define the objective function.
            //There are ingredients price per unit and their total price should be minimize:
            MPObjective objective = solver.objective();
            objective.setCoefficient(x1, 9.5); //price per ingredient unit
            objective.setCoefficient(x2, 94.3);
            objective.setCoefficient(x3, 5.05);
            objective.setMinimization();

            //4. Invoke the solver
            final MPSolver.ResultStatus resultStatus = solver.solve();
            // Check that the problem has an optimal solution.
            if (resultStatus != MPSolver.ResultStatus.OPTIMAL) {
                System.err.println("The problem does not have an optimal solution!");
                return false;
            }

            // The value of each variable in the solution.
            System.out.println("Solution (ingredients results):");
            System.out.println(x1.name() + " = " + round(x1.solutionValue(),2) + " [%]");
            System.out.println(x2.name() + " = " + round(x2.solutionValue(),2) + " [%]");
            System.out.println(x3.name() + " = " + round(x3.solutionValue(),2) + " [%]");

            // The objective value of the solution.
            System.out.println("Total price = " + round(solver.objective().value()/100,2));



        }catch (Exception ex){
            return false;
        }

        return true;
    }

    private boolean executeTestLPSolverexample(){
        MPSolver solver = new MPSolver(
                "LinearProgrammingExample", MPSolver.OptimizationProblemType.GLOP_LINEAR_PROGRAMMING);

        double infinity = java.lang.Double.POSITIVE_INFINITY;
        // x and y are continuous non-negative variables.
        MPVariable x = solver.makeNumVar(0.0, infinity, "x");
        MPVariable y = solver.makeNumVar(0.0, infinity, "y");
        System.out.println("Number of variables = " + solver.numVariables());

        // x + 2*y <= 14.
        MPConstraint c0 = solver.makeConstraint(-infinity, 14.0, "c0");
        c0.setCoefficient(x, 1);
        c0.setCoefficient(y, 2);

        // 3*x - y >= 0.
        MPConstraint c1 = solver.makeConstraint(0.0, infinity, "c1");
        c1.setCoefficient(x, 3);
        c1.setCoefficient(y, -1);

        // x - y <= 2.
        MPConstraint c2 = solver.makeConstraint(-infinity, 2.0, "c2");
        c2.setCoefficient(x, 1);
        c2.setCoefficient(y, -1);
        System.out.println("Number of constraints = " + solver.numConstraints());

        // Maximize 3 * x + 4 * y.
        MPObjective objective = solver.objective();
        objective.setCoefficient(x, 3);
        objective.setCoefficient(y, 4);
        objective.setMaximization();

        final MPSolver.ResultStatus resultStatus = solver.solve();
        // Check that the problem has an optimal solution.
        if (resultStatus != MPSolver.ResultStatus.OPTIMAL) {
            System.err.println("The problem does not have an optimal solution!");
            return false;
        }

        // The value of each variable in the solution.
        System.out.println("Solution");
        System.out.println("x = " + x.solutionValue());
        System.out.println("y = " + y.solutionValue());

        // The objective value of the solution.
        System.out.println("Optimal objective value = " + solver.objective().value());
        return true;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
