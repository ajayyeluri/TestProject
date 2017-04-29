package com.saimanoj.processor;

import com.saimanoj.model.Commit;
import com.saimanoj.model.Stats;
import com.saimanoj.service.GithubStatService;
import com.saimanoj.utils.GithubHttpClientUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by saimanu.manoj on 29-04-2017.
 */
@Service
public class StatProcessor extends  Thread {
    @Value("${repo.Owner:shrimanoz}")
    String repoOwner;
    @Value("${repo.Name:TestProject}")
    String repoName;
    @Autowired
    GithubStatService githubStatService;

    Log logger = LogFactory.getLog(StatProcessor.class);
    public void reloadStats(){
       logger.debug("Begining to purge Database");
        githubStatService.purgeCommitDetails();
        logger.debug("Database purge completed");
        GithubHttpClientUtils githubHttpClientUtils = new GithubHttpClientUtils();
        List<Commit> commitList = githubHttpClientUtils.getCommits(repoOwner , repoName);
        logger.debug("itteration for list of commits starts");
        for(int i=0;i<commitList.size(); i++){
           Stats stats = githubHttpClientUtils.getCommitDetails(commitList.get(i));
           stats.setRepoName(repoName);
           stats.setRepoOwner(repoOwner);
           githubStatService.saveCommitDetails(stats);
           logger.info("added stats  "+stats.toString());

        }

    }

    @Override
    public void run() {
        reloadStats();
        super.run();

    }
}
