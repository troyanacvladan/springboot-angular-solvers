package com.afos.app.bean;

public class DataModel {

    int numVars;
    int numConstraints;
    double[] bounds;
    double[][] constraintCoeffs;
    double[] objCoeffs;

    public double[][] getConstraintCoeffs() {
        return constraintCoeffs;
    }

    public void setConstraintCoeffs(double[][] constraintCoeffs) {
        this.constraintCoeffs = constraintCoeffs;
    }

    public double[] getBounds() {
        return bounds;
    }

    public void setBounds(double[] bounds) {
        this.bounds = bounds;
    }

    public double[] getObjCoeffs() {
        return objCoeffs;
    }

    public void setObjCoeffs(double[] objCoeffs) {
        this.objCoeffs = objCoeffs;
    }

    public int getNumVars() {
        return numVars;
    }

    public void setNumVars(int numVars) {
        this.numVars = numVars;
    }

    public int getNumConstraints() {
        return numConstraints;
    }

    public void setNumConstraints(int numConstraints) {
        this.numConstraints = numConstraints;
    }


}
