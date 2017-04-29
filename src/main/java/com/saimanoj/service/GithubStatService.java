package com.saimanoj.service;

import com.saimanoj.model.Commit;
import com.saimanoj.model.Stats;

/**
 * Created by saimanu.manoj on 29-04-2017.
 */
public interface GithubStatService {

    public void saveCommitDetails(Stats stats);
}
