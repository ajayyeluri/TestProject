package com.saimanoj.controller;

import com.saimanoj.processor.StatProcessor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by saimanu.manoj on 29-04-2017.
 */
@RestController
public class StartProcessorController {

    @Autowired
    StatProcessor statProcessor;
    Log logger = LogFactory.getLog(this.getClass());
    @RequestMapping(value = "/startprocessor")
    public void startProcessor(){
        logger.info("Staring to load commit");
        ((Thread)statProcessor).start();

    }
}
