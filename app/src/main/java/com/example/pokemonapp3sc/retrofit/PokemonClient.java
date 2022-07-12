package com.example.pokemonapp3sc.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonClient {
    private static PokemonClient instance = null;
    private PokemonAPI pokemonApi;

    public PokemonClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PokemonAPI.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        pokemonApi = retrofit.create(PokemonAPI.class);
        //gets JSON data from the API
    }

    public static synchronized PokemonClient getInstance() {
        if (instance == null) {
            instance = new PokemonClient(); //instance of this class
        }
        return instance;
    }


    public PokemonAPI getMyApi() {
        return pokemonApi;
    } //returns api
}
