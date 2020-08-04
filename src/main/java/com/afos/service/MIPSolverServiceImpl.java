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

       return  false;
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
            logger.error("MIP Solver ended with error: ",ex.toString());
            return false;
        }

        logger.info("MIP Solver ended");
        return true;
    }
}
