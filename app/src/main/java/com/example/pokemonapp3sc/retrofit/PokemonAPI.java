package com.example.pokemonapp3sc.retrofit;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonAPI {
    String URL = "https://pokeapi.co/api/v2/";

    @GET("pokemon/") //gets the pokemon page from the url
    Call<JsonObject> getPokemon();
    @GET("pokemon/{id}") //goes to a specific pokemon page
    Call<JsonObject> getPokemon(@Path("id") String pokemonId);
}