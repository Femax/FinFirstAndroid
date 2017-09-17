package ru.fedosov.opengifityhack.client.model;

import com.google.gson.annotations.SerializedName;

public class UsdCurrency {
    @SerializedName("currency")
    private Double value;

    public UsdCurrency(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
