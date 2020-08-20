package com.afos.solver.lpsolver55;

import com.afos.solver.SolverService;
import lpsolve.*;
import org.springframework.stereotype.Service;

@Service("solver")
public class LPSolverServiceImpl implements SolverService {
    @Override
    public boolean execute() {
        try {
            // Create a problem with 3 variables and 0 constraints (initially)
            int varNmb = 3;
            LpSolve solver = LpSolve.makeLp(0, varNmb);

            //VJ: sve nizove (constraint and goal) trebq inicijqlizovati sa elementom vise
            // i prvi element uvek treba da ima vrednost 0 a ostale elemente shiftovati za jedno mesto

            // add constraints for ingredients
            double[] c_x1 = {0,1,0,0}; //Oats
            solver.addConstraint(c_x1,LpSolve.GE,30);
            solver.addConstraint(c_x1,LpSolve.LE,100);
            double[] c_x2 = {0,0,1,0};//CornMaize
            solver.addConstraint(c_x2,LpSolve.GE,10);
            solver.addConstraint(c_x2,LpSolve.LE,100);
            double[] c_x3 = {0,0,0,1};//FishMeal
            solver.addConstraint(c_x3,LpSolve.GE,20);
            solver.addConstraint(c_x3,LpSolve.LE,80);

            // add constraints for nutrients
            double[] c_y1 = {0,87,87,92}; //DryMatter
            solver.addConstraint(c_y1,LpSolve.GE,60*100);
            solver.addConstraint(c_y1,LpSolve.LE,100*100);
            double[] c_y2 = {0,10.5,9,59}; //CrudeProtein
            solver.addConstraint(c_y2,LpSolve.GE,20*100);
            solver.addConstraint(c_y2,LpSolve.LE,100*100);
            double[] c_y3 = {0,5,4,9}; //Fat
            solver.addConstraint(c_y3,LpSolve.GE,0*100);
            solver.addConstraint(c_y3,LpSolve.LE,20*100);

            //main constraint (ingredients total results (in [%]) must be equal to 100%):
            double[] c_z = {0,1,1,1};
            solver.addConstraint(c_z,LpSolve.EQ,100);

            // set objective function (ingredients prices)
            double[] goal = {0,9.5,94.3,5.05};
            solver.setObjFn(goal);

            //solver.printLp();

            // solve the problem
            solver.solve();

            if(solver.getStatus() != 0)
            {
                System.err.println("The problem does not have an optimal solution!");
                return false;
            }

            // print solution
            System.out.println("Value of objective function: " + solver.getObjective());
            double[] var = solver.getPtrVariables();
            for (int i = 0; i < var.length; i++) {
                System.out.println("Value of var[" + i + "] = " + var[i]);
            }

            double[] shadowPrices = new double[3];
            double[] temp = new double[3];
            solver.getSensitivityObj(shadowPrices, temp);

            // delete the problem and free memory
            solver.deleteLp();
            return true;
        }
        catch (LpSolveException e) {
            e.printStackTrace();
            return false;
        }
    }
}
