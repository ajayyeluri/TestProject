package com.saimanoj.controller.dto;

public class BarMonthData {

    int additons ;
    int deletions ;
    String yearMonth ;

    public void addtoAdditions( int i ) {
        additons+=i;
    }

    public void addtoDeletions( int i ) {
        deletions+=i;
    }

    public int getAdditons() {
        return additons;
    }

    public void setAdditons(int additons) {
        this.additons = additons;
    }

    public int getDeletions() {
        return deletions;
    }

    public void setDeletions(int deletions) {
        this.deletions = deletions;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }
}
