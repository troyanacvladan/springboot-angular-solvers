package com.afos.app.service;

import com.afos.app.service.api.DataModelService;
import com.afos.app.bean.DataModel;
import org.springframework.stereotype.Service;

@Service("dataModelService")
public class DataModelServiceImpl implements DataModelService {

    @Override
    public DataModel getDataModel() {
        return MockLPSolverAFOSv1Data();
    }

    private DataModel MockLPSolverAFOSv1Data(){
        //mock AFOSv1 Cat formula:
        int numVars = 3;
        int numConstraints = 3;
        double[] bounds = new double[]{
                100, 100, 100};
        double[][] constraintCoeffs = new double[][]{
                //dodavanje ogranicenja sirovina
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}

        };
        double[] objCoeffs = new double[]{
                7, 8, 2, 9, 6};

        DataModel dataModel = new DataModel();
        dataModel.setNumVars(numVars);
        dataModel.setNumConstraints(numConstraints);
        dataModel.setBounds(bounds);
        dataModel.setConstraintCoeffs(constraintCoeffs);
        dataModel.setObjCoeffs(objCoeffs);

        return dataModel;
    }

    private DataModel MockMIPSolverTestData(){
        //mock test data:
        int numVars = 5;
        int numConstraints = 4;
        double[] bounds = new double[]{
                250, 285, 211, 315};
        double[][] constraintCoeffs = new double[][]{
                {5, 7, 9, 2, 1},
                {18, 4, -9, 10, 12},
                {4, 7, 3, 8, 5},
                {5, 13, 16, 3, -7},
        };
        double[] objCoeffs = new double[]{
                7, 8, 2, 9, 6};

        DataModel dataModel = new DataModel();
        dataModel.setNumVars(numVars);
        dataModel.setNumConstraints(numConstraints);
        dataModel.setBounds(bounds);
        dataModel.setConstraintCoeffs(constraintCoeffs);
        dataModel.setObjCoeffs(objCoeffs);

        return dataModel;
    }
}
