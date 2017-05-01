package com.saimanoj.controller;


import com.saimanoj.controller.dto.PieData;
import com.saimanoj.model.Stats;
import com.saimanoj.repository.StatsRepository;
import com.saimanoj.service.GithubStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/chart")

public class SCMChartController {

    @Autowired
    StatsRepository statsRepository;

    @RequestMapping(path = "/pie",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PieData getPieChartData() {

        Iterable<Stats> stats = statsRepository.findAll();
        PieData pieData = new PieData();
        for ( Stats stat : stats) {
            pieData.setAdds(pieData.getAdds() + stat.getAdditions());
            pieData.setDeletes(pieData.getDeletes() + stat.getDeletions());
        }
        return pieData;
    }

}