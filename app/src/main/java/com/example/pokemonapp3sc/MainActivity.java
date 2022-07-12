package com.example.pokemonapp3sc;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.pokemonapp3sc.databinding.ActivityMainBinding;
import com.example.pokemonapp3sc.entities.Pokemon;
import com.example.pokemonapp3sc.retrofit.PokemonClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static MainActivity instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        populatePokemon(); //populates the list of Pokemon
        //populateAbilities(); //this doesn't work. no idea why, if populatePokemon is complete why would pokemonList be empty?

        super.onCreate(savedInstanceState);
        com.example.pokemonapp3sc.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_search, R.id.navigation_randomiser)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    public static synchronized MainActivity getInstance() {
        if (instance == null) {
            instance = new MainActivity(); //instance of this class
        }
        return instance;
    }

    private void populatePokemon() {
        Pokemon.pokemonList = new ArrayList<>(); //initialising the ArrayList
        Call<JsonObject> call = PokemonClient.getInstance().getMyApi().getPokemon(); //getting first 20 Pokemon from pokeapi.co
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject data = response.body(); //response is sent to a json object
                JsonArray results = data.getAsJsonArray("results"); //results section is parsed
                for (int i = 0; i < (results != null ? results.size() : 0); i++) {
                    Pokemon pokemon = new Pokemon(); //new pokemon object
                    pokemon.setName(results.get(i).getAsJsonObject().get("name").getAsString()); //sets the name
                    pokemon.setUrl(results.get(i).getAsJsonObject().get("url").getAsString()); //sets the url
                    Pokemon.pokemonList.add(pokemon); //adds the pokemon to the list
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occurred", Toast.LENGTH_LONG).show(); //error handling
            }
        });
    }

    public void populateAbilities() {
        for (int j = 0; j < Pokemon.pokemonList.size(); j++) {
            Pokemon.pokemonList.get(j).setAbilities(getAbilities(Pokemon.pokemonList.get(j).getName())); //populates the abilities for each pokemon
        }
    }

    public JsonObject getAbilities(String pokemonId) {
        final JsonObject[] jsonObjects = new JsonObject[1]; //android studio made me do this
        Call<JsonObject> call = PokemonClient.getInstance().getMyApi().getPokemon(pokemonId); //gets the specific pokemon's data page
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject data = response.body(); //json object
                JsonArray results = data.getAsJsonArray("abilities"); //gets the abilities section
                jsonObjects[0] = results.get(0).getAsJsonObject(); //returns the abilities section as a json object
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occurred", Toast.LENGTH_LONG).show(); //error handling
            }
        });
        return jsonObjects[0]; //return the json object
    }
}