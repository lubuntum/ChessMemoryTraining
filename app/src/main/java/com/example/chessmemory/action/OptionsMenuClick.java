package com.example.chessmemory.action;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.chessmemory.R;

import java.util.List;

public class OptionsMenuClick {
    public Context context;
    public View.OnClickListener listener;
    public static int pickedFigure = 0;
    public View previousOption = null;
    public OptionsMenuClick(Context context){
        this.context = context;
        listener = (view)->{
            if(view.getTag() == null) return;
            if(view.equals(previousOption)){
                previousOption.setBackgroundColor(context.getColor(R.color.white));
                pickedFigure = 0;
                previousOption = null;
                return;
            }
            if(previousOption != null)
                previousOption.setBackgroundColor(context.getColor(R.color.white));
            view.setBackgroundColor(context.getColor(com.beardedhen.androidbootstrap.R.color.bootstrap_gray_lighter));
            pickedFigure = (int)view.getTag();
            previousOption = view;
        };
    }
    public void bondAllOptions(List<ImageView> optionSquares){
        if(listener == null) return;
        optionSquares.forEach((s)-> s.setOnClickListener(listener));
    }

}
