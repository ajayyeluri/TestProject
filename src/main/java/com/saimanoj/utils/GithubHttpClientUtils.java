package com.saimanoj.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saimanoj.model.Commit;
import com.saimanoj.model.Stats;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Class to call github api
 *
 */
public class GithubHttpClientUtils {

    private String OwnerName;
    private String RepoName;


    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String ownerName) {
        OwnerName = ownerName;
    }

    public String getRepoName() {
        return RepoName;
    }

    public void setRepoName(String repoName) {
        RepoName = repoName;
    }

    Log logger = LogFactory.getLog(GithubHttpClientUtils.class);
    /**
     * Get a list of all commits for a repo
     * @param OwnerName
     * @param RepoName
     * @return
     */
    public List<Commit> getCommits(String OwnerName , String RepoName){
         this.OwnerName = OwnerName;
         this.RepoName = RepoName;
     // Build URL
        String url = "https://api.github.com/repos/"+OwnerName+"/"+RepoName+"/commits";

     //Get Json Data
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<Commit>> rateResponse =
                restTemplate.exchange(url,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Commit>>() {
                        });
        return rateResponse.getBody();

    }

    /**
     * Get the commit details associted to a individual commit
     * @param commit
     * @return
     */
    public Stats getCommitDetails(Commit commit){
        return getStats(
                getCommitDetailsString(commit));
    }

    protected String getCommitDetailsString(Commit commit){
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(commit.getUrl(), String.class);
       // commit.getStats().setSha(commit.getUrl());
        //commit.getStats().setDate();
        return result;
    }

    protected  Stats getStats(String commitDetail){
        ObjectMapper mapper = new ObjectMapper();
        Stats stats = new Stats();
        logger.debug("recieved commit detail- "+commitDetail);
        try {
            JsonNode jsonNode = mapper.readTree(commitDetail);
            String sha=jsonNode.path("sha").asText();
            String url =jsonNode.path("url").asText();
            int total = (jsonNode.path("stats")).path("total").asInt();
            int additions = jsonNode.path("stats").path("additions").asInt();
            int deletions = jsonNode.path("stats").path("deletions").asInt();
            String commitDate = jsonNode.path("commit").path("committer").path("date").asText();

            stats.setSha(sha);
            stats.setUrl(url);
            stats.setAdditions(additions);
            stats.setDeletions(deletions);
            stats.setTotal(total);
        }
        catch(Exception e){
            logger.error("error coverting commit detail to Stats object " +commitDetail ,e);
        }
        return stats;
    }
}

