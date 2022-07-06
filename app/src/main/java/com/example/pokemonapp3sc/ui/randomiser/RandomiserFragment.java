package com.example.pokemonapp3sc.ui.randomiser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pokemonapp3sc.databinding.FragmentRandomiserBinding;

public class RandomiserFragment extends Fragment {

    private FragmentRandomiserBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RandomiserViewModel RandomiserViewModel =
                new ViewModelProvider(this).get(RandomiserViewModel.class);

        binding = FragmentRandomiserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textRandomiser;
        RandomiserViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}