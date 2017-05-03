import com.saimanoj.model.Commit;

import com.saimanoj.utils.GithubHttpClientUtils;
import org.junit.Test;

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

        List<Commit> list =  test.getCommits("shrimanoz" , "TestProject", null);
        for(int i=0;i<list.size();i++){
            Commit commit = list.get(i);
            System.out.println("The Url is :"+commit.getUrl()+" "+ "The Sha Value is :" +commit.getSha());
        }
    }

//    @Test
//    public void getCommitDetails(){
//        Commit commit1 = new Commit();
//        commit1.setUrl("https://api.github.com/repos/shrimanoz/TestProject/commits/a55c35e6b867502e9802b15a048681b4d1761958");
//        String testResult  = test.getCommitDetailsString(commit1);
//        System.out.println(
//                test.getStats(testResult)
//                        .toString());
//        System.out.println(testResult);
//    }

}
