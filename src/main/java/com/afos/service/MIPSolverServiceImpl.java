package com.afos.service;

import com.afos.bean.DataModel;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class MIPSolverServiceImpl implements SolverService {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    DataModelService dataModelService;

    static {
        System.loadLibrary("jniortools");
    }

    @Override
    public boolean execute() {

       return  executeLPWithMultiplierSolverAFOSv1Example();
    }

    // MIP CBC example with integer results
    private boolean executeMIPSolverAFOSv1Example(){
        System.out.println("***MIP calculation for AFOS v1 Cat formula***");

        // Create the linear solver with the CBC backend.
        MPSolver solver = new MPSolver(
                "SimpleMipProgram", MPSolver.OptimizationProblemType.CBC_MIXED_INTEGER_PROGRAMMING);

        double infinity = java.lang.Double.POSITIVE_INFINITY;

        try {

            double batchSize = 1000; //Plant batch size in kg
            double x1Multiplier = 51;

            // 1. Create the variables.
            // There are ingredients percentage variable (continuous non-negative variables):
            MPVariable x1 = solver.makeIntVar((30*batchSize)/100, (100*batchSize)/100, "Oats");//min and max value
            MPVariable x2 = solver.makeIntVar((10*batchSize)/100, (100*batchSize)/100, "CornMaize");
            MPVariable x3 = solver.makeIntVar((20*batchSize)/100, (80*batchSize)/100, "FishMeal");

            //2. Define the constraints.
            //There are MIP comnstraint:
            MPConstraint c01 = solver.makeConstraint((30*batchSize)/100, (100*batchSize)/100, "mip x1");
            c01.setCoefficient(x1, x1Multiplier);

            //There are nutrients percentage constraints:
            MPConstraint c1 = solver.makeConstraint(batchSize*60, 100*batchSize, "DryMatter");//min and max value
            c1.setCoefficient(x1, 87*x1Multiplier);
            c1.setCoefficient(x2, 87);
            c1.setCoefficient(x3, 92);
            MPConstraint c2 = solver.makeConstraint(batchSize*20, 100*batchSize, "CrudeProtein");
            c2.setCoefficient(x1, 10.5*x1Multiplier);
            c2.setCoefficient(x2, 9);
            c2.setCoefficient(x3, 59);
            MPConstraint c3 = solver.makeConstraint(batchSize*0, 20*batchSize, "Fat");
            c3.setCoefficient(x1, 5*x1Multiplier);
            c3.setCoefficient(x2, 4);
            c3.setCoefficient(x3, 9);
            //main constraint (ingredients total results (in [%]) must be equal to 100%):
            MPConstraint c4 = solver.makeConstraint(100, 100, "MainConstraint");
            c4.setCoefficient(x1, x1Multiplier);
            c4.setCoefficient(x2, 1);
            c4.setCoefficient(x3, 1);


            //3. Define the objective function.
            //There are ingredients price per unit and their total price should be minimize:
            MPObjective objective = solver.objective();
            objective.setCoefficient(x1, 9.5*x1Multiplier); //price per ingredient unit
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
            System.out.println(x1.name() + " = " + LPSolverServiceImpl.round(((x1.solutionValue()*100)/batchSize)*x1Multiplier,2) + " [%]");
            System.out.println(x2.name() + " = " + LPSolverServiceImpl.round((x2.solutionValue()*100)/batchSize,2) + " [%]");
            System.out.println(x3.name() + " = " + LPSolverServiceImpl.round((x3.solutionValue()*100)/batchSize,2) + " [%]");

            // The objective value of the solution.
            System.out.println("Total price = " + LPSolverServiceImpl.round(solver.objective().value()/100,2));



        }catch (Exception ex){
            return false;
        }

        return true;
    }

    // MIP CBC example with integer results
    private boolean executeLPWithMultiplierSolverAFOSv1Example(){
        System.out.println("***LP calculation (with mulipliers) for AFOS v1 Cat formula***");

        // Create the linear solver with the CBC backend.
        MPSolver solver = new MPSolver(
                "SimpleMipProgram", MPSolver.OptimizationProblemType.GLOP_LINEAR_PROGRAMMING);

        try {

            double batchSize = 1000; //Plant batch size in kg
            double x1Multiplier = 51;

            // 1. Create the variables.
            // There are ingredients percentage variable (continuous non-negative variables):
          //  MPVariable x1 = solver.makeNumVar((30*batchSize)/100, (100*batchSize)/100, "Oats");//min and max value
         //   MPVariable x2 = solver.makeNumVar((10*batchSize)/100, (100*batchSize)/100, "CornMaize");
         //   MPVariable x3 = solver.makeNumVar((20*batchSize)/100, (80*batchSize)/100, "FishMeal");
            MPVariable x1 = solver.makeNumVar(30, 100, "Oats");//min and max value
            MPVariable x2 = solver.makeNumVar(10, 100, "CornMaize");
            MPVariable x3 = solver.makeNumVar(20, 80, "FishMeal");

            //2. Define the constraints.
            //There are MIP comnstraint:
            MPConstraint c01 = solver.makeConstraint((30*batchSize)/100, (100*batchSize)/100, "mip x1");
            c01.setCoefficient(x1, x1Multiplier);
            MPConstraint c02 = solver.makeConstraint((10*batchSize)/100, (100*batchSize)/100, "mip x2");
            c02.setCoefficient(x2, 1);
            MPConstraint c03 = solver.makeConstraint((20*batchSize)/100, (80*batchSize)/100, "mip x2");
            c03.setCoefficient(x3, 1);

            //There are nutrients percentage constraints:
            MPConstraint c1 = solver.makeConstraint(batchSize*60, 100*batchSize, "DryMatter");//min and max value
            c1.setCoefficient(x1, 87*x1Multiplier);
            c1.setCoefficient(x2, 87);
            c1.setCoefficient(x3, 92);
            MPConstraint c2 = solver.makeConstraint(batchSize*20, 100*batchSize, "CrudeProtein");
            c2.setCoefficient(x1, 10.5*x1Multiplier);
            c2.setCoefficient(x2, 9);
            c2.setCoefficient(x3, 59);
            MPConstraint c3 = solver.makeConstraint(batchSize*0, 20*batchSize, "Fat");
            c3.setCoefficient(x1, 5*x1Multiplier);
            c3.setCoefficient(x2, 4);
            c3.setCoefficient(x3, 9);
            //main constraint (ingredients total results (in [%]) must be equal to 100%):
            MPConstraint c4 = solver.makeConstraint(batchSize, batchSize, "MainConstraint");
            c4.setCoefficient(x1, x1Multiplier);
            c4.setCoefficient(x2, 1);
            c4.setCoefficient(x3, 1);


            //3. Define the objective function.
            //There are ingredients price per unit and their total price should be minimize:
            MPObjective objective = solver.objective();
            objective.setCoefficient(x1, 9.5*x1Multiplier); //price per ingredient unit
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
            System.out.println(x1.name() + " = " + LPSolverServiceImpl.round(((x1.solutionValue()*100)/batchSize)*x1Multiplier,2) + " [%]");
            System.out.println(x2.name() + " = " + LPSolverServiceImpl.round((x2.solutionValue()*100)/batchSize,2) + " [%]");
            System.out.println(x3.name() + " = " + LPSolverServiceImpl.round((x3.solutionValue()*100)/batchSize,2) + " [%]");

            // The objective value of the solution.
            System.out.println("Total price = " + LPSolverServiceImpl.round(solver.objective().value()/100,2));



        }catch (Exception ex){
            return false;
        }

        return true;
    }


    private boolean executeMIPSolverTestExample(){
        logger.info("MIP Solver started");

        final DataModel dataModel = dataModelService.getDataModel();
        if(dataModel == null){
            logger.error("DataModel object is null.");
            return false;
        }

        try {

            // Create the linear solver with the CBC backend.
            MPSolver solver = new MPSolver(
                    "SimpleMipProgram", MPSolver.OptimizationProblemType.CBC_MIXED_INTEGER_PROGRAMMING);

            double infinity = java.lang.Double.POSITIVE_INFINITY;
            MPVariable[] x = new MPVariable[dataModel.getNumVars()];
            for (int j = 0; j < dataModel.getNumVars(); ++j) {
                x[j] = solver.makeIntVar(0.0, infinity, String.format("x_%d",j));
            }
            System.out.println("Number of variables = " + solver.numVariables());

            // Create the constraints.
            for (int i = 0; i < dataModel.getNumConstraints(); ++i) {
                MPConstraint constraint = solver.makeConstraint(0, dataModel.getBounds()[i], "");
                for (int j = 0; j < dataModel.getNumVars(); ++j) {
                    constraint.setCoefficient(x[j], dataModel.getConstraintCoeffs()[i][j]);
                }
            }
            System.out.println("Number of constraints = " + solver.numConstraints());

            MPObjective objective = solver.objective();
            for (int j = 0; j < dataModel.getNumVars(); ++j) {
                objective.setCoefficient(x[j], dataModel.getObjCoeffs()[j]);
            }
            objective.setMaximization();

            final MPSolver.ResultStatus resultStatus = solver.solve();

            // Check that the problem has an optimal solution.
            if (resultStatus == MPSolver.ResultStatus.OPTIMAL) {
                System.out.println("Objective value = " + objective.value());
                for (int j = 0; j < dataModel.getNumVars(); ++j) {
                    System.out.println("x[" + j + "] = " + x[j].solutionValue());
                }
                System.out.println();
                System.out.println("Problem solved in " + solver.wallTime() + " milliseconds");
                System.out.println("Problem solved in " + solver.iterations() + " iterations");
                System.out.println("Problem solved in " + solver.nodes() + " branch-and-bound nodes");
            } else {
                System.err.println("The problem does not have an optimal solution.");
            }
        }
        catch (Exception ex){
            logger.error("MIP Solver ended with error: "+ex.toString());
            return false;
        }

        logger.info("MIP Solver ended");
        return true;
    }
}
