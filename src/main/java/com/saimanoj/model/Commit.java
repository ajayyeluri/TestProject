package com.saimanoj.model;

import java.util.ArrayList;

/**
 * Created by saimanu.manoj on 28-04-2017.
 */
public class Commit {

    Stats stats;


    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    String sha, url ;


    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
