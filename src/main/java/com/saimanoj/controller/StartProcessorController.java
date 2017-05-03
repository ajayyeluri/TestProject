package com.saimanoj.controller;

import com.saimanoj.processor.StatProcessor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

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
    @RequestMapping(value = "/startprocessor/{repoOwner}/{repoName}/{since}")
    public void startProcessor1(@PathVariable("repoName") String repoName,
                                @PathVariable("repoOwner") String repoOwner,
                                @PathVariable("since") String since){
        logger.info("Staring to load commit ");
        statProcessor.setRepoName(repoName);
        statProcessor.setRepoOwner(repoOwner);
        statProcessor.setCommitSince(since);
        logger.info("repoName " + repoName +
                " repoOwner " + repoOwner +
                "  since " + since);
        ((Thread)statProcessor).start();

    }

}
