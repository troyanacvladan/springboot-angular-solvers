package com.afos.app.controller;

import com.afos.solver.SolverService;
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
        //solver.execute();
        return "Welcome to Spring Boot app!";
    }

}
