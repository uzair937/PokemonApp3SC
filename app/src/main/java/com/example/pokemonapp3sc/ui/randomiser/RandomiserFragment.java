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

import java.util.Random;

public class RandomiserFragment extends Fragment {

    private FragmentRandomiserBinding binding;
    private TextView pokemonNameText;
    private Button randomButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RandomiserViewModel RandomiserViewModel =
                new ViewModelProvider(this).get(RandomiserViewModel.class);

        binding = FragmentRandomiserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textRandomiser;
        RandomiserViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        pokemonNameText = binding.pokemonNameText;
        randomButton = binding.randomiserButton;
        randomButton.setOnClickListener((v) -> {
            getRandomPokemon();
        });

        return root;
    }

    private void getRandomPokemon() {
        int random = new Random().nextInt(0 + MainActivity.pokemonList.size());
        pokemonNameText.setText(MainActivity.pokemonList.get(random).getName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}