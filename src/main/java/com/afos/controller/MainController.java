package com.afos.controller;

import com.afos.service.SolverService;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    SolverService solver;

    @RequestMapping("/")
    String index(){
        logger.info("Method started");
        solver.execute();
        return "Welcome to Spring Boot app!";
    }

}
