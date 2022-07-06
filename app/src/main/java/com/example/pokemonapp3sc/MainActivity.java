package com.example.pokemonapp3sc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pokemonapp3sc.results.Data;
import com.example.pokemonapp3sc.retrofit.RetrofitClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.pokemonapp3sc.databinding.ActivityMainBinding;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private TextView pokemonNameText;
    private Button randomButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
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

        setContentView(R.layout.fragment_randomiser);
        pokemonNameText = findViewById(R.id.pokemonNameText);
        randomButton = findViewById(R.id.randomiserButton);
        randomButton.setOnClickListener((v) -> {
            getRandomPokemon();
        });
    }

    private void getRandomPokemon() {
        Random random = new Random();
        int value = random.nextInt(1 + 15) + 1;
        getPokemon(value);
    }

    private void getPokemon(int id) {
        Call<Data> call = RetrofitClient.getInstance().getMyApi().getPokemon();
        System.out.print(call);
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                Data data = response.body();
                String[] pokemonNames = new String[data.getResults().size()];

                for (int i = 0; i < data.getResults().size(); i++) {
                    pokemonNames[i] = data.getResults().get(i).getName();
                    System.out.println("all the boys " + pokemonNames[i]);
                }

                //pokemonNameText.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, oneHeroes));
                pokemonNameText.setText(pokemonNames[id]);
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
    }
}