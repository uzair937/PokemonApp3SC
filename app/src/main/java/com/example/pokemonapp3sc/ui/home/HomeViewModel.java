package com.example.pokemonapp3sc.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("All Pokemon - Navigate to another page then come back :)");
    }

    public LiveData<String> getText() {
        return mText;
    }
}