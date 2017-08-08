package com.alvion.importer.models;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class ClientStatistic {

    private Long clientId;
    private Long spentTotal;
    private Boolean isBig;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getSpentTotal() {
        return spentTotal;
    }

    public void setSpentTotal(Long spentTotal) {
        this.spentTotal = spentTotal;
    }

    public Boolean getIsBig() {
        return isBig;
    }

    public void setIsBig(Boolean isBig) {
        this.isBig = isBig;
    }

    @Override
    public String toString() {
        return "ClientStatistic{" +
                "clientId=" + clientId +
                ", spentTotal=" + spentTotal +
                ", isBig=" + isBig +
                '}';
    }
}
