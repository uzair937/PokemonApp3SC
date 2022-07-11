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
import com.example.pokemonapp3sc.results.PokemonData;
import com.example.pokemonapp3sc.retrofit.PokemonClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Pokemon> pokemonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        populatePokemon();
    }

    private void populatePokemon() {
        pokemonList = new ArrayList<>();
        Call<PokemonData> call = PokemonClient.getInstance().getMyApi().getPokemon();
        call.enqueue(new Callback<PokemonData>() {
            @Override
            public void onResponse(Call<PokemonData> call, Response<PokemonData> response) {
                PokemonData data = response.body();
                for (int i = 0; i < (data != null ? data.getResults().size() : 0); i++) {
                    Pokemon pokemon = new Pokemon();
                    pokemon.setName(data.getResults().get(i).getName());
                    pokemon.setUrl(data.getResults().get(i).getUrl());
                    //pokemon.setSprite(data.getResults().get(i).getSprite());
                    pokemonList.add(pokemon);
                }
            }

            @Override
            public void onFailure(Call<PokemonData> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occurred", Toast.LENGTH_LONG).show();
            }
        });
    }
}