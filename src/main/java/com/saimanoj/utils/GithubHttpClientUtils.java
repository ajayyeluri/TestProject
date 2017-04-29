package com.saimanoj.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saimanoj.model.Commit;
import com.saimanoj.model.Stats;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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

    public Stats getCommitsDetails(String OwnerName , String RepoName){
        GithubHttpClientUtils clientUtils = new GithubHttpClientUtils();
        List<Commit> shaList =  clientUtils.getCommits(OwnerName , RepoName);
        Iterator<Commit> iterator = shaList.iterator();
        while(iterator.hasNext()) {
            String url = "https://api.github.com/repos/" + OwnerName + "/" + RepoName + "/commits/";
            url = url + iterator.next().getSha();
            RestTemplate restTemplate = new RestTemplate();
            Stats stats = restTemplate
                    .getForObject( url, Stats.class);
            int adds = stats.getAdds();
            int deletes = stats.getDeletes();
            int totalChanges = stats.getTotalChanges();
            String sha = stats.getSha();
            Date commitDate = stats.getCommitDate();
        }
       return null;
    }
    public Stats getDetail(Commit commit, String sha){
       String url = commit.getUrl()+"/"+commit.getSha();
        return null;
    }

   /* public Stats getCommitDetails(Commit commit){
        String url = commit.getUrl();

    Iterator<Commit> iterator = shaList.iterator();
    while(iterator.hasNext()){
        url= url+iterator.next().getSha();
        RestTemplate restTemplate = new RestTemplate();
       ResponseEntity<String> restResponse =
               restTemplate.getForEntity(url , String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(restResponse.getBody());
        JsonNode name = root.path("name");

    }
    Stats stats = new Stats();
    stats.setAdds();
    stats.setDeletes();
    stats.setTotalChanges();
    stats.setCommitDate();
        return stats ;
    }--> */
}
