package ru.fedosov.opengifityhack.client.model;

import android.text.BoringLayout;

/**
 * Created by Максим on 16.09.2017.
 */
public class Portfolio {
    private Boolean type;
    private String name;
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
