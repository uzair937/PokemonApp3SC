package com.example.pokemonapp3sc.ui.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pokemonapp3sc.MainActivity;
import com.example.pokemonapp3sc.databinding.FragmentSearchBinding;
import com.example.pokemonapp3sc.entities.Pokemon;

import java.util.Timer;
import java.util.TimerTask;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;
    private EditText searchText;
    private TextView searchResult;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SearchViewModel searchViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSearch;
        searchViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        searchResult = binding.searchResult;
        searchText = binding.searchText;

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        searchText.addTextChangedListener( //checks what the textview contains
                new TextWatcher() { //AJAX type live search
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    private Timer timer = new Timer(); //does the search every 500 ms
                    private final long DELAY = 500; // milliseconds

                    @Override
                    public void afterTextChanged(final Editable s) {
                        timer.cancel();
                        timer = new Timer();
                        timer.schedule(
                                new TimerTask() {
                                    @Override
                                    public void run() {
                                        getActivity().runOnUiThread(() -> {
                                            if (Pokemon.pokemonList.contains(s.toString())) {
                                                searchResult.setText("true"); //if the pokemon is in the list it'll show as true
                                            } else {
                                                searchResult.setText("false");
                                            }
                                        });
                                    }
                                },
                                DELAY
                        );
                    }
                }
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}