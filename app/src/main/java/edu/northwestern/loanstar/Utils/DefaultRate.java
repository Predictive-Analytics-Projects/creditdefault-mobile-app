package edu.northwestern.loanstar.Utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class DefaultRate {
    @SerializedName("rate")
    @Expose
    private String rate;
    @SerializedName("values")
    @Expose
    private List<Values> values = new ArrayList<>();

    public DefaultRate() {
    }

    public DefaultRate(String rate, List<Values> values) {
        super();
        this.rate = rate;
        this.values = values;
    }

    public String getRate() {
        return rate;
    }

    private void setRate(String rate) {
        this.rate = rate;
    }

    public DefaultRate withRate(String rate) {
        this.rate = rate;
        return this;
    }

    public List<Values> getValues() {
        return values;
    }

    private void setValues(List<Values> values) {
        this.values = values;
    }

    public DefaultRate withValues(List<Values> values) {
        this.values = values;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("rate", rate).append("values", values).toString();
    }
}