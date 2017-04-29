import ch.qos.logback.core.net.SyslogOutputStream;
import com.saimanoj.model.Commit;

import com.saimanoj.model.Stats;
import com.saimanoj.utils.GithubHttpClientUtils;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by saimanu.manoj on 28-04-2017.
 */


public class GithubHttpClientUtilsTest {

    GithubHttpClientUtils test = new GithubHttpClientUtils();
    Commit commit = new Commit();
    //test case for getCommits method
    @Test
    public void getCommitsTest(){

        List<Commit> list =  test.getCommits("shrimanoz" , "TestProject");
        for(int i=0;i<list.size();i++){
            Commit commit = list.get(i);
            System.out.println("The Url is :"+commit.getUrl()+ "The Sha Value is :" +commit.getSha());
        }

    }
    @Test
    public void getcommitdetails(){
        Stats testStas = test.getCommitsDetails("shrimanoz" , "TestProject" , "c8f978b8486234e84a1d4bb23109bca611a61859" );
        System.out.println(testStas.toString());
    }



}
