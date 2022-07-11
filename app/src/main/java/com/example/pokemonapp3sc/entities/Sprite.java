package com.example.pokemonapp3sc.entities;

import com.google.gson.annotations.SerializedName;

public class Sprite {

    @SerializedName("back_default")
    private String backDefault;
    @SerializedName("front_default")
    private String frontDefault;

    public void setBackDefault(String backDefault) {
        this.backDefault = backDefault;
    }

    public void setFrontDefault(String frontDefault) {
        this.frontDefault = frontDefault;
    }

    public String getBackDefault() {
        return backDefault;
    }

    public String getFrontDefault() {
        return frontDefault;
    }
}
