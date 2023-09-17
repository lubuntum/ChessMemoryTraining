package com.example.chessmemory.figuredistribution;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.content.res.AppCompatResources;

import com.example.chessmemory.R;

import java.sql.SQLData;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

public class FigureDistributor {
    private final Context context;
    private int difficult;
    private Map<Integer, Integer> figures ;
    private final Random random = new Random();

    public FigureDistributor(Context context, int difficult, Map<Integer, Integer> figures){
        this.context = context;
        this.difficult = difficult;
        if(figures != null) this.figures = figures;
        else defaultFiguresInit();
    }
    public void distributeFigure(LinkedList<ImageView> squares, int rowCount, int columnCount){
        /*репрезентирует незанятые квадраты для генерации фигур true => не занят*/
        boolean[] busySquares = busySquaresInit(rowCount, columnCount);
        for(int count = 0; count < difficult;count++){
            if(figures.size() == 0) break;
            //Получаем все незанятые ячейки на доске и получаем одну из них
            int freeIndex = getRandomFreeIndex(busySquares);
            //Если по какой то причине не осталось свободных, то выходим из цикла
            if(freeIndex == -1) break;
            //Получаем случайную фигуру из всех доступных
            int figureResource = getRandomFigureResource();
            //Если все фигуры были выбраны, то выходим также
            //Если difficult больше чем кол-во доступных фигур
            if(figureResource == -1) break;
            //Устанавливаем на наше случайное поле случайную фигур
            squares.get(freeIndex).setImageDrawable(AppCompatResources.getDrawable(context, figureResource));
        }
    }
    private int getRandomFigureResource(){
        if(figures.size() == 0) return -1;
        Map.Entry<Integer, Integer>[] entries = figures.entrySet().toArray(new Map.Entry[0]);
        Map.Entry<Integer, Integer> randomEntry = entries[random.nextInt(entries.length)];
        randomEntry.setValue(randomEntry.getValue()-1);
        //фигур не осталось value => кол-во фигур, key => тип фигуры
        if(randomEntry.getValue() == 0) figures.remove(randomEntry.getKey());
        return randomEntry.getKey();
    }
    /*Получем случайный индекс свободного поля и ставим что он уже не свободный*/
    private int getRandomFreeIndex(boolean [] busySquares){
        LinkedList<Integer> freeIndexes = new LinkedList<>();
        for(int i = 0; i < busySquares.length;i++)
            if(busySquares[i]) freeIndexes.add(i);
        if(freeIndexes.size() == 0) return -1;
        int freeIndex = freeIndexes.get(random.nextInt(freeIndexes.size()));
        busySquares[freeIndex] = false;
        return freeIndex;
    }
    private boolean[] busySquaresInit(int rowCount, int columnCount){
        boolean[] busySquares = new boolean[rowCount*columnCount];
        for(int i = 0; i < rowCount*columnCount;i++)
            busySquares[i] = true;
        return busySquares;
    }
    public void defaultFiguresInit(){
        figures = new HashMap<>();
        figures.put(R.drawable.black_pawn, 8);
        figures.put(R.drawable.black_tower,2);
        figures.put(R.drawable.black_horse,2);
        figures.put(R.drawable.black_bishop,2);
        figures.put(R.drawable.black_queen,1);
        figures.put(R.drawable.black_king,1);

        figures.put(R.drawable.white_pawn, 8);
        figures.put(R.drawable.white_tower,2);
        figures.put(R.drawable.white_horse,2);
        figures.put(R.drawable.white_bishop,2);
        figures.put(R.drawable.white_queen,1);
        figures.put(R.drawable.white_king,1);
    }

    public int getDifficult() {
        return difficult;
    }

    public void setDifficult(int difficult) {
        this.difficult = difficult;
    }

    public Map<Integer, Integer> getFigures() {
        return figures;
    }

    public void setFigures(Map<Integer, Integer> figures) {
        this.figures = figures;
    }
}
