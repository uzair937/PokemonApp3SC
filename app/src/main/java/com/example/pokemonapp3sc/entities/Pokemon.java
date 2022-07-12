package com.example.pokemonapp3sc.entities;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Pokemon {
    public static ArrayList<Pokemon> pokemonList;
    //all the required fields for json data
    @SerializedName("url")
    private String url;
    @SerializedName("name")
    private String name;
    @SerializedName("abilities")
    private JsonObject abilities;
    @SerializedName("stats")
    private JsonObject stats;
    @SerializedName("height")
    private int height;
    @SerializedName("types")
    private JsonObject types;
    @SerializedName("sprites")
    private JsonObject sprites;

    //getters and setters
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JsonObject getAbilities() {
        return abilities;
    }

    public void setAbilities(JsonObject abilities) {
        this.abilities = abilities;
    }

    public JsonObject getStats() {
        return stats;
    }

    public void setStats(JsonObject stats) {
        this.stats = stats;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public JsonObject getTypes() {
        return types;
    }

    public void setTypes(JsonObject types) {
        this.types = types;
    }

    public JsonObject getSprites() {
        return sprites;
    }

    public void setSprites(JsonObject sprites) {
        this.sprites = sprites;
    }
}
