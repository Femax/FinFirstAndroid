package ru.fedosov.opengifityhack.client.model;

import android.text.BoringLayout;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Максим on 16.09.2017.
 */
public class Portfolio {
    @SerializedName("accountType")
    private Boolean type;
    @SerializedName("portfelName")
    private String name;
    @SerializedName("startBalance")
    private String realBalance;

    public Portfolio(Boolean type, String name, String realBalance) {
        this.type = type;
        this.name = name;
        this.realBalance = realBalance;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRealBalance(String realBalance) {
        this.realBalance = realBalance;
    }

    public Boolean getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getRealBalance() {
        return realBalance;
    }
}
