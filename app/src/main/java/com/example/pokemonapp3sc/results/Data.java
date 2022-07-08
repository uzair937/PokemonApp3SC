package com.example.pokemonapp3sc.results;

import com.example.pokemonapp3sc.entities.Pokemon;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("count")
    private Integer count;
    @SerializedName("previous")
    private Object previous;
    @SerializedName("results")
    private List<Pokemon> results = null;
    @SerializedName("next")
    private String next;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Object getPrevious() {
        return previous;
    }

    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    public List<Pokemon> getResults() {
        return results;
    }

    public void setResults(List<Pokemon> results) {
        this.results = results;
    }
    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

}
