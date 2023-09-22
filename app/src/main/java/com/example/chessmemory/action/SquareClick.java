package com.example.chessmemory.action;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;
import android.widget.ImageView;

import com.example.chessmemory.ChessGameFragment;
import com.example.chessmemory.R;
import com.example.chessmemory.gamesettings.GameBoardInfo;

import java.util.List;

public class SquareClick {
    public int clickColor;
    public int whiteColor;
    public int blackColor;
    public Context context;
    public View previousSquare = null;
    public View.OnClickListener squareListener;
    public SquareClick(Context context){
        this.context = context;
        clickColor = context.getResources().getColor(R.color.highlighted);
        whiteColor = context.getResources().getColor(com.beardedhen.androidbootstrap.R.color.bootstrap_gray_lighter);
        blackColor = context.getResources().getColor(com.beardedhen.androidbootstrap.R.color.bootstrap_gray_light);
        this.squareListener = view -> {
            if(previousSquare != null){
                if(previousSquare.getTag() == GameBoardInfo.ChessColor.WHITE)
                    previousSquare
                            .setBackgroundColor(whiteColor);
                else previousSquare.setBackgroundColor(blackColor);
                if(view.equals(previousSquare)) {
                    previousSquare = null;
                    return;
                }
                view.setBackgroundColor(clickColor);
            }

            view.setBackgroundColor(clickColor);
            previousSquare =  view;
            //view.setPadding(10,10,10,10);
        };
    }
    public void bondAllSquares(List<ImageView> squares){
        for(ImageView square: squares)
            square.setOnClickListener(squareListener);
    }
    public void unbondAllSquares(List<ImageView> squares){
        for(ImageView square: squares)
            square.setOnClickListener(null);
    }

}
