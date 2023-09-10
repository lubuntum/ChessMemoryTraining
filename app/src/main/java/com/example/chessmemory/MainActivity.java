package com.example.chessmemory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;

import com.example.chessmemory.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    public MutableLiveData<Boolean> loadStatus = new MutableLiveData<>();
    private final Runnable loadRnb = new Runnable() {
        @Override
        public void run() {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_fragment,ChessGameFragment.getInstance(), "chess_game")
                    .commit();
            loadStatus.postValue(true);
        }
    };
    private final Observer<Boolean> showBoardObserver = new Observer<Boolean>() {
        @Override
        public void onChanged(Boolean isLoad) {
            if(binding == null || binding.mainFragment == null) return;
            if(isLoad) {
                binding.loadBar.setVisibility(View.GONE);
                binding.mainFragment.setVisibility(View.VISIBLE);
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadStatus.observe(this, showBoardObserver);
        startChessSession();


    }
    public void startChessSession(){
        Thread thread = new Thread(loadRnb);
        thread.start();
    }
}