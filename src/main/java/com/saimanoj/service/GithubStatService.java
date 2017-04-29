package com.saimanoj.service;

import com.saimanoj.model.Commit;
import com.saimanoj.model.Stats;

/**
 * Created by saimanu.manoj on 29-04-2017.
 */
public interface GithubStatService {

    /**
     * Save a commit stats
     * @param stats
     */
    public void saveCommitDetails(Stats stats);

    /**
     * Removes all purge details
     */
    public void purgeCommitDetails ();

}
