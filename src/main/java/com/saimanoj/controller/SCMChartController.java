package com.saimanoj.controller;


import com.saimanoj.controller.dto.BarMonthData;
import com.saimanoj.controller.dto.PieData;
import com.saimanoj.model.Stats;
import com.saimanoj.repository.StatsRepository;
import com.saimanoj.service.GithubStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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

    @RequestMapping(path = "/bar/day",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Stats> getBarChartData() {

        Iterable<Stats> stats = statsRepository.findAll();
        List<Stats> statsList = new ArrayList<Stats>();
        for ( Stats stat : stats) {
            statsList.add(stat);
        }
        return statsList ;

    }


    @RequestMapping(path = "/bar/month",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<BarMonthData> getBarMonthChartData() {

        Iterable<Stats> stats = statsRepository.findAll();
        Map<String, BarMonthData> yearMonth = new HashMap<String, BarMonthData>();
        for ( Stats stat : stats) {
            String[] dateString = stat.getDateString().split("-");
            String currentDateString = dateString[0]+dateString[1];
            BarMonthData barMonthData = new BarMonthData();
            if (yearMonth.containsKey(currentDateString))
                barMonthData = yearMonth.get(currentDateString);

            barMonthData.addtoAdditions(stat.getAdditions());
            barMonthData.addtoDeletions(stat.getDeletions());
            barMonthData.setYearMonth(currentDateString);
            yearMonth.put(currentDateString, barMonthData);

        }

        return yearMonth.values();

    }
}
