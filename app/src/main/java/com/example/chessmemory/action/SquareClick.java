package com.example.chessmemory.action;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.example.chessmemory.ChessGameFragment;
import com.example.chessmemory.R;

import java.util.List;

public class SquareClick {
    public Context context;
    public View previousSquare = null;
    public View.OnClickListener squareListener;
    public SquareClick(Context context){
        this.context = context;
        this.squareListener = view -> {
            if(previousSquare != null){
                if(previousSquare.getTag() == ChessGameFragment.ChessColor.WHITE)
                    previousSquare
                            .setBackgroundColor(context.getResources().getColor(com.beardedhen.androidbootstrap.R.color.bootstrap_gray_lighter));
                else previousSquare.setBackgroundColor(context.getResources().getColor(com.beardedhen.androidbootstrap.R.color.bootstrap_gray_light));

                view.setBackgroundColor(context.getResources().getColor(R.color.highlighted));
            }
            view.setBackgroundColor(context.getResources().getColor(R.color.highlighted));
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
