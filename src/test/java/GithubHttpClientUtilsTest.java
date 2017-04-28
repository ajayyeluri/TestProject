import com.saimanoj.model.Commit;

import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by saimanu.manoj on 28-04-2017.
 */


public class GithubHttpClientUtilsTest {


    //test case for getCommits method
    @Test
    public void getCommitsTest(){
        String OwnerName = "shrimanoz";
        String RepoName = "TestProject";
        //Checking for Url
        String url = "https://api.github.com/repos/"+OwnerName+"/"+RepoName+"/commits";
        // Get Json Response
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<Commit>> rateResponse =
                restTemplate.exchange(url,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Commit>>() {
                        });

        assertNotNull( rateResponse.getBody());

    }

}
