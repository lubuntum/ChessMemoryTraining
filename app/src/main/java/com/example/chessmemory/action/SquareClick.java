package com.example.chessmemory.action;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.content.res.AppCompatResources;

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
                if(OptionsMenuClick.pickedFigure != 0)
                    ((ImageView)view).setImageDrawable(AppCompatResources.getDrawable(context, OptionsMenuClick.pickedFigure));
                else ((ImageView) view).setImageDrawable(null);
                if(view.equals(previousSquare)) {
                    previousSquare = null;
                    return;
                }
                view.setBackgroundColor(clickColor);
            }
            else {
                if (OptionsMenuClick.pickedFigure != 0)
                    ((ImageView) view).setImageDrawable(AppCompatResources.getDrawable(context, OptionsMenuClick.pickedFigure));
                else ((ImageView) view).setImageDrawable(null);
            }
            view.setBackgroundColor(clickColor);
            previousSquare =  view;
            //view.setPadding(10,10,10,10);
        };
    }
    public void bondAllSquares(List<ImageView> squares){
        squares.forEach((s)->s.setOnClickListener(squareListener));
    }
    public void unBoundAllSquares(List<ImageView> squares){
        squares.forEach((s)->s.setOnClickListener(null));
    }

}
