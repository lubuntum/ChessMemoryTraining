package com.example.chessmemory;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.chessmemory.databinding.ChessBoardBinding;

import java.util.LinkedList;

public class ChessGameFragment extends Fragment {
    private ChessBoardBinding binding;
    private LinkedList<ImageView> squares = new LinkedList<>();
    public static ChessGameFragment getInstance(){
        ChessGameFragment fragment = new ChessGameFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ChessBoardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chessBoardInit(8,8,true);
    }
    private void chessBoardInit(int rowCount, int columnCount, boolean startColor){
        for(int row = 0; row < rowCount;row++){
            LinearLayout rowContainer = rowGenerate(columnCount, startColor);
            binding.chessBoard.addView(rowContainer);
            startColor = !startColor;
        }
    }
    private LinearLayout rowGenerate(int squaresCount, boolean isWhite){
        LinearLayout layout = new LinearLayout(getContext());
        layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        int whiteColor = getResources().getColor(R.color.white);
        int blackColor = getResources().getColor(com.beardedhen.androidbootstrap.R.color.bootstrap_gray_lighter);
        for(int squareIndex = 0; squareIndex < squaresCount;squareIndex++){
            ImageView square = new ImageView(getContext());
            square.setLayoutParams(new LinearLayout.LayoutParams(scalePixelsByScreen(25), scalePixelsByScreen(25)));
            square.setScaleType(ImageView.ScaleType.FIT_CENTER);
            if (isWhite) square.setBackgroundColor(whiteColor);
            else square.setBackgroundColor(blackColor);
            isWhite = !isWhite;
            squares.add(square);
            layout.addView(square);
        }
        return layout;
    }
    public int scalePixelsByScreen(int pixels){
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int)(pixels * scale + 0.5);
    }


}
