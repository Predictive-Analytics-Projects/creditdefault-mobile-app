package edu.northwestern.loanstar.Utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Values {
    @SerializedName("bad_loans_avoided")
    @Expose
    private Double badLoansAvoided;
    @SerializedName("good_loans_lost")
    @Expose
    private Double goodLoansLost;
    @SerializedName("net_impact")
    @Expose
    private Double netImpact;

    public Values() {
    }

    public Values(Double badLoansAvoided, Double goodLoansLost, Double netImpact) {
        super();
        this.badLoansAvoided = badLoansAvoided;
        this.goodLoansLost = goodLoansLost;
        this.netImpact = netImpact;
    }

    public Double getBadLoansAvoided() {
        return badLoansAvoided;
    }

    private void setBadLoansAvoided(Double badLoansAvoided) {
        this.badLoansAvoided = badLoansAvoided;
    }

    public Values withBadLoansAvoided(Double badLoansAvoided) {
        this.badLoansAvoided = badLoansAvoided;
        return this;
    }

    public Double getGoodLoansLost() {
        return goodLoansLost;
    }

    private void setGoodLoansLost(Double goodLoansLost) {
        this.goodLoansLost = goodLoansLost;
    }

    public Values withGoodLoansLost(Double goodLoansLost) {
        this.goodLoansLost = goodLoansLost;
        return this;
    }

    public Double getNetImpact() {
        return netImpact;
    }

    private void setNetImpact(Double netImpact) {
        this.netImpact = netImpact;
    }

    public Values withNetImpact(Double netImpact) {
        this.netImpact = netImpact;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("badLoansAvoided", badLoansAvoided).append("goodLoansLost", goodLoansLost).append("netImpact", netImpact).toString();
    }
}