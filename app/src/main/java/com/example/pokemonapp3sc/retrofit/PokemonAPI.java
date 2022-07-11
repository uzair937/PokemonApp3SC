package com.example.pokemonapp3sc.retrofit;

import com.example.pokemonapp3sc.results.PokemonData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokemonAPI {
    String URL = "https://pokeapi.co/api/v2/";

    @GET("pokemon")
    Call<PokemonData> getPokemon();
}