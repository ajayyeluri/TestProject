package com.saimanoj.model;

import java.util.Date;

/**
 * Created by saimanu.manoj on 28-04-2017.
 */
public class Stats {

    private String sha;
    private int adds;
    private int deletes;
    private int totalChanges;
    private Date commitDate;

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public int getAdds() {
        return adds;
    }

    public void setAdds(int adds) {
        this.adds = adds;
    }

    public int getDeletes() {
        return deletes;
    }

    public void setDeletes(int deletes) {
        this.deletes = deletes;
    }

    public int getTotalChanges() {
        return totalChanges;
    }

    public void setTotalChanges(int totalChanges) {
        this.totalChanges = totalChanges;
    }

    public Date getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(Date commitDate) {
        this.commitDate = commitDate;
    }
}
