package com.example.pokemonapp3sc.results;

import com.example.pokemonapp3sc.entities.Sprite;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SpriteData {
    @SerializedName("sprites")
    private List<Sprite> results = null;

    public List<Sprite> getResults() {
        return results;
    }

    public void setResults(List<Sprite> results) {
        this.results = results;
    }
}
