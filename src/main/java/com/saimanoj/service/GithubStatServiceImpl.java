package com.saimanoj.service;

import com.saimanoj.model.Stats;
import com.saimanoj.repository.StatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by saimanu.manoj on 29-04-2017.
 */
@Component(value = "GithubStatService")
public class GithubStatServiceImpl implements GithubStatService {

    @Autowired
    private StatsRepository statsRepository;
    @Override
    public void saveCommitDetails(Stats stats) {
     statsRepository.save(stats);
    }

    @Override
    public void purgeCommitDetails() {
        statsRepository.deleteAll();
    }
}
