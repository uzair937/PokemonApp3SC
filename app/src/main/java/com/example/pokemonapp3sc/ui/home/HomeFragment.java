package com.example.pokemonapp3sc.ui.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pokemonapp3sc.MainActivity;
import com.example.pokemonapp3sc.R;
import com.example.pokemonapp3sc.adapters.ImageAdapter;
import com.example.pokemonapp3sc.adapters.TextViewAdapter;
import com.example.pokemonapp3sc.databinding.FragmentHomeBinding;
import com.example.pokemonapp3sc.entities.Pokemon;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private GridView pokemonGrid;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

//        ArrayList<Integer> icons = new ArrayList< >(Arrays.asList(
//                R.drawable.pokemon_logo_png_1428
//        ));

        pokemonGrid = binding.gridView;
        String[] textValues = new String[Pokemon.pokemonList.size()];
        for (int i = 0; i < Pokemon.pokemonList.size(); i++) {
            textValues[i] = Pokemon.pokemonList.get(i).getName();
        }
        //pokemonGrid.setAdapter(new ImageAdapter(icons, getActivity().getApplicationContext()));
        pokemonGrid.setAdapter(new TextViewAdapter(getContext(), textValues));
        pokemonGrid.setOnItemClickListener((parent, view, position, id) -> {
            //int pos = icons.get(position);

            showDialogBox(position);
        });
        return root;
    }

    private void showDialogBox(final int position){
        final Dialog dialog = new Dialog(getContext());

        dialog.setContentView(R.layout.grid_dialog);

        //Getting custom dialog views
        TextView pokemonName = dialog.findViewById(R.id.txt_name);
        //ImageView Image = dialog.findViewById(R.id.img);
        Button btn_Close = dialog.findViewById(R.id.btn_close);

        String title = Pokemon.pokemonList.get(position).getName();

        //extracting name
        int index = title.indexOf("/");
        String name = title.substring(index+1);
        pokemonName.setText(name);

        //Image.setImageResource(item_pos);

        btn_Close.setOnClickListener(v -> dialog.dismiss());

        dialog.show();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}