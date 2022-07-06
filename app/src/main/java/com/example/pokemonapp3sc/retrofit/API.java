package com.example.pokemonapp3sc.retrofit;

import com.example.pokemonapp3sc.results.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    String URL = "https://pokeapi.co/api/v2/";

    @GET("pokemon")
    Call<Data> getPokemon();
}
