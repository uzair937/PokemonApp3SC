package com.example.pokemonapp3sc.ui.randomiser;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RandomiserViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public RandomiserViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the random Pokemon fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}