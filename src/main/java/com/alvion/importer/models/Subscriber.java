package com.alvion.importer.models;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class Subscriber {

    private Long id;
    private Long spent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSpent() {
        return spent;
    }

    public void setSpent(Long spent) {
        this.spent = spent;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "id=" + id +
                ", spent=" + spent +
                '}';
    }
}