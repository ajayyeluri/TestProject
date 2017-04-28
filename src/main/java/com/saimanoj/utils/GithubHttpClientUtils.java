package com.saimanoj.utils;

import com.saimanoj.model.Commit;
import com.saimanoj.model.Stats;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;
import java.util.List;

/**
 * Class to call github api
 *
 */
public class GithubHttpClientUtils {

    private String OwnerName;
    private String RepoName;
    private List<Commit> shaList;

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
        List<Commit> shaList =  rateResponse.getBody();
        this.shaList = shaList;
        return shaList;

    }

    public Stats getCommitDetails(Commit commit){
        String url = "https://api.github.com/repos/"+OwnerName+"/"+RepoName+"/commits";
    Iterator<Commit> iterator = shaList.iterator();
    while(iterator.hasNext()){
        url= url+iterator.next();
        RestTemplate restTemplate = new RestTemplate();
       // ResponseEntity rateResponse = restTemplate.getForEntity()

    }
        return null ;
    }
}
