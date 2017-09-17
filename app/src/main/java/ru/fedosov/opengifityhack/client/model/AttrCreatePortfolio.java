package ru.fedosov.opengifityhack.client.model;

/**
 * Created by Максим on 17.09.2017.
 */

public class AttrCreatePortfolio {
    private String id;
    private Boolean accountType;
    private String currency;
    private Integer period;
    private Double risk;
    private String portfelName;

    public Boolean getAccountType() {
        return accountType;
    }

    public void setAccountType(Boolean accountType) {
        this.accountType = accountType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Double getRisk() {
        return risk;
    }

    public void setRisk(Double risk) {
        this.risk = risk;
    }

    public String getPortfelName() {
        return portfelName;
    }

    public void setPortfelName(String portfelName) {
        this.portfelName = portfelName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
