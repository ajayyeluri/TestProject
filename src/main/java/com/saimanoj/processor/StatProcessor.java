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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by saimanu.manoj on 29-04-2017.
 * Api reference
 * https://developer.github.com/v3/repos/commits/
 */
@Service
public class StatProcessor extends  Thread {
    @Value("${repo.Owner:shrimanoz}")
    String repoOwner;
    @Value("${repo.Name:TestProject}")
    String repoName;
    @Autowired
    GithubStatService githubStatService;

    // Format YYYY-MM-DD
    @Value("${repo.commits.since:2017-01-02}")
    String commitSince=null;

    static final String REPO_COMMITS_SINCE="since";

    public String getRepoOwner() {
        return repoOwner;
    }

    public void setRepoOwner(String repoOwner) {
        this.repoOwner = repoOwner;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getCommitSince() {
        return commitSince;
    }

    public void setCommitSince(String commitSince) {
        this.commitSince = commitSince;
    }


    Log logger = LogFactory.getLog(StatProcessor.class);
    public void reloadStats(){
        Map<String, String> commitApiParams = new HashMap<String, String>();
        if ( commitSince != null ){
            commitApiParams.put(REPO_COMMITS_SINCE, commitSince+"T00:00:00Z");
        }
       logger.debug("Beginning to purge Database");
        githubStatService.purgeCommitDetails();
        logger.debug("Database purge completed");
        logger.info("Getting the info for " + repoName + " for owner " + repoOwner);
        GithubHttpClientUtils githubHttpClientUtils = new GithubHttpClientUtils();
        List<Commit> commitList = githubHttpClientUtils.
                getCommits(repoOwner, repoName, commitApiParams);
        logger.debug("iteration for list of commits starts");
        for(int i=0;i<commitList.size(); i++){
           Stats stats = githubHttpClientUtils.
                   getCommitDetails(commitList.get(i));
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
