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

import com.example.pokemonapp3sc.R;
import com.example.pokemonapp3sc.adapters.ImageAdapter;
import com.example.pokemonapp3sc.databinding.FragmentHomeBinding;

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

        ArrayList<Integer> icons = new ArrayList< >(Arrays.asList(
                R.drawable.pokemon_logo_png_1428
        ));
        pokemonGrid = binding.gridView;
        pokemonGrid.setAdapter(new ImageAdapter(icons, getActivity().getApplicationContext()));
        pokemonGrid.setOnItemClickListener((parent, view, position, id) -> {
            int pos = icons.get(position);
            showDialogBox(pos);
        });
        return root;
    }

    private void showDialogBox(final int item_pos){
        final Dialog dialog = new Dialog(getContext());

        dialog.setContentView(R.layout.grid_dialog);

        //Getting custom dialog views
        TextView Image_name = dialog.findViewById(R.id.txt_Image_name);
        ImageView Image = dialog.findViewById(R.id.img);
        Button btn_Close = dialog.findViewById(R.id.btn_close);

        String title = getResources().getResourceName(item_pos);

        //extracting name
        int index = title.indexOf("/");
        String name = title.substring(index+1);
        Image_name.setText(name);

        Image.setImageResource(item_pos);

        btn_Close.setOnClickListener(v -> dialog.dismiss());

        dialog.show();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}