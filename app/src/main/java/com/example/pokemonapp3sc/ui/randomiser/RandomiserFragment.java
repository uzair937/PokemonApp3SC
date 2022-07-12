package com.example.pokemonapp3sc.ui.randomiser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pokemonapp3sc.MainActivity;
import com.example.pokemonapp3sc.databinding.FragmentRandomiserBinding;
import com.example.pokemonapp3sc.entities.Pokemon;

import java.util.Random;

public class RandomiserFragment extends Fragment {

    private FragmentRandomiserBinding binding;
    private TextView pokemonNameText;
    private TextView pokemonAbilityText;
    private Button randomButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RandomiserViewModel RandomiserViewModel =
                new ViewModelProvider(this).get(RandomiserViewModel.class);

        binding = FragmentRandomiserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textRandomiser;
        RandomiserViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        //bindings for all the UI elements
        pokemonNameText = binding.pokemonNameText;
        pokemonAbilityText = binding.pokemonAbilityText;
        randomButton = binding.randomiserButton;
        randomButton.setOnClickListener((v) -> {
            getRandomPokemon();
        });

        return root;
    }

    private void getRandomPokemon() { //returns a random pokemon
        int random = new Random().nextInt(0 + Pokemon.pokemonList.size());
        Pokemon pokemon = Pokemon.pokemonList.get(random);
        pokemonNameText.setText(pokemon.getName()); //sets the name
        //pokemonAbilityText.setText(pokemon.getAbilities().get("name").getAsString()); //sets the ability name
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}