package com.saimanoj.processor;

import com.saimanoj.model.Commit;
import com.saimanoj.model.Stats;
import com.saimanoj.service.GithubStatService;
import com.saimanoj.utils.GithubHttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by saimanu.manoj on 29-04-2017.
 */
@Service
public class StatProcessor {
    @Value("repo.Owner")
    String repoOwner;
    @Value("repo.Name")
    String repoName;
    @Autowired
    GithubStatService githubStatService;

    public void loadStats(){
        GithubHttpClientUtils githubHttpClientUtils = new GithubHttpClientUtils();
        List<Commit> commitList = githubHttpClientUtils.getCommits(repoOwner , repoName);
        for(int i=0;i<commitList.size(); i++){
           Stats stats = githubHttpClientUtils.getCommitDetails(commitList.get(i));
           stats.setRepoName(repoName);
           stats.setRepoOwner(repoOwner);
           githubStatService.saveCommitDetails(stats);
        }


    }
}
