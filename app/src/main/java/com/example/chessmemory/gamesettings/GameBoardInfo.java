package com.example.chessmemory.gamesettings;

import android.widget.LinearLayout;

import androidx.annotation.DrawableRes;

import com.example.chessmemory.R;

public class GameBoardInfo {
    public static int boardWidth = 8;
    public static  int boardHeight = 8;
    public static int difficult = 8;
    public enum ChessColor {
        WHITE,
        BLACK
    }
    @DrawableRes
    public static int [] blackFiguresRes = {
            R.drawable.black_pawn,R.drawable.black_tower,
            R.drawable.black_bishop, R.drawable.black_horse,
            R.drawable.black_queen, R.drawable.black_king
    };
    @DrawableRes
    public static int [] whiteFiguresRes = {
            R.drawable.white_pawn, R.drawable.white_tower,
            R.drawable.white_bishop, R.drawable.white_horse,
            R.drawable.white_queen, R.drawable.white_king
    };

}
