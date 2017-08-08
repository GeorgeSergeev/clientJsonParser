package com.alvion.importer.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class Client {

    private Long clientId;

    private List<Subscriber> subscribers = new ArrayList<>();

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", subscribers size=" + subscribers.size() +
                '}';
    }
}