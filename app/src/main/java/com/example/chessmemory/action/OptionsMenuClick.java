package com.example.chessmemory.action;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class OptionsMenuClick {
    public Context context;
    public View.OnClickListener listener;
    public static int pickedFigure;
    public OptionsMenuClick(Context context){
        this.context = context;
        listener = (v)->{
            if(v.getTag() == null) return;
            int figureRes = (int)v.getTag();
            Toast.makeText(context, String.valueOf(figureRes), Toast.LENGTH_SHORT).show();
        };
    }
    public void bondAllOptions(List<ImageView> optionSquares){
        if(listener == null) return;
        optionSquares.forEach((s)-> s.setOnClickListener(listener));
    }

}
