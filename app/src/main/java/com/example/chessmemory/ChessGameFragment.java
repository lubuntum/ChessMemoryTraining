package com.example.chessmemory;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.chessmemory.action.OptionsMenuClick;
import com.example.chessmemory.action.SquareClick;
import com.example.chessmemory.databinding.ChessBoardBinding;
import com.example.chessmemory.dialogs.SettingsDialog;
import com.example.chessmemory.figuredistribution.FigureDistributor;
import com.example.chessmemory.gamesettings.GameBoardInfo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ChessGameFragment extends Fragment {
    private ChessBoardBinding binding;
    private LinkedList<ImageView> squares = new LinkedList<>();
    private SquareClick squareClick;
    private OptionsMenuClick optionsMenuClick;
    FigureDistributor figureDistributor;
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
        chessBoardInit(8,8,true);
        figureDistributorInit();
        boardSquareClickListenerInit();

        settingsBtnInit();
    }
    private void chessBoardInit(int rowCount, int columnCount, boolean startColor){
        /*Сгенерировать строку для черных*/
        binding.chessBoard.addView(optionsRowGenerate(true));
        binding.chessBoard.addView(rowCharGenerate(8, 'a', true));
        for(int row = 0; row < rowCount;row++){
            LinearLayout rowContainer = rowGenerate(columnCount, row+1, startColor);
            binding.chessBoard.addView(rowContainer);
            startColor = !startColor;
        }
        binding.chessBoard.addView(rowCharGenerate(8, 'a', false));
        binding.chessBoard.addView(optionsRowGenerate(false));
        /*Сгенерировать строку для белых фигур*/
    }
    /*Метод генерирующий строчку для шахматной доски*/
    private LinearLayout rowGenerate(int squaresCount, int rowNumber, boolean isWhite){
        LinearLayout layout = new LinearLayout(getContext());
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.addView(rowNumberGenerate(rowNumber));

        int whiteColor = getResources().getColor(com.beardedhen.androidbootstrap.R.color.bootstrap_gray_lighter);
        int blackColor = getResources().getColor(com.beardedhen.androidbootstrap.R.color.bootstrap_gray_light);
        for(int squareIndex = 0; squareIndex < squaresCount;squareIndex++){
            ImageView square = new ImageView(getContext());
            square.setLayoutParams(new LinearLayout.LayoutParams(scalePixelsByScreen(35), scalePixelsByScreen(35)));
            square.setScaleType(ImageView.ScaleType.FIT_CENTER);
            if (isWhite) {
                square.setBackgroundColor(whiteColor);
                square.setTag(GameBoardInfo.ChessColor.WHITE);
            }
            else {
                square.setBackgroundColor(blackColor);
                square.setTag(GameBoardInfo.ChessColor.BLACK);
            }
            isWhite = !isWhite;
            square.setOnLongClickListener((view)->{
                Toast.makeText(getContext(), "Click", Toast.LENGTH_LONG).show();
                return false;
            });
            squares.add(square);
            layout.addView(square);
        }
        layout.addView(rowNumberGenerate(rowNumber));
        return layout;
    }
    /*Метод вставляющий вначало и конец строки ее номер*/
    private TextView rowNumberGenerate(int rowNumber){
        TextView rowNumberText = new TextView(getContext());
        LinearLayout.LayoutParams layoutParams =  new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        rowNumberText.setPadding(scalePixelsByScreen(10),0,scalePixelsByScreen(10),0);
        rowNumberText.setLayoutParams(layoutParams);
        rowNumberText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

        rowNumberText.setText(String.valueOf(rowNumber));

        return rowNumberText;
    }
    /*Метод для генерации символов сверху и снизу доски*/
    private LinearLayout rowCharGenerate(int charCount, char startChar, boolean isTop){
        LinearLayout layout = new LinearLayout(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if(isTop)
            layoutParams.setMargins(scalePixelsByScreen(43), 0, 0,0);
        else layoutParams.setMargins(scalePixelsByScreen(43), scalePixelsByScreen(5), 0,0);
        layout.setLayoutParams(layoutParams);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        for(int i = (int)startChar; i < (int)startChar+charCount; i++){
            TextView charTextView = new TextView(getContext());
            LinearLayout.LayoutParams textLayoutParams =  new LinearLayout.LayoutParams(
                    scalePixelsByScreen(35), scalePixelsByScreen(35));
            textLayoutParams.gravity = Gravity.CENTER;
            charTextView.setLayoutParams(textLayoutParams);
            charTextView.setPadding(0, 0,0, 0);
            charTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            charTextView.setText(String.valueOf( (char)i) );
            layout.addView(charTextView);
        }
        return layout;

    }
    public int scalePixelsByScreen(int pixels){
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int)(pixels * scale + 0.5);
    }
    /*Генерация строки для выбора фигур, используется при расстановке*/
    private LinearLayout optionsRowGenerate(boolean isWhite){
        LinearLayout optionsRow = new LinearLayout(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        optionsRow.setLayoutParams(layoutParams);
        optionsRow.setOrientation(LinearLayout.HORIZONTAL);
        if(isWhite)
            fullFillOptionsRow(optionsRow, GameBoardInfo.whiteFiguresRes);
        else fullFillOptionsRow(optionsRow, GameBoardInfo.blackFiguresRes);
        return optionsRow;
    }
    /*Метод для заполнения строки с выбором фигур*/
    public void fullFillOptionsRow(LinearLayout optionsRow, int[] drawableRes){
        List<ImageView> optionsSquares = new ArrayList<>();
        for(int figure: drawableRes){
            ImageView optionSquare = new ImageView(getContext());
            optionSquare.setLayoutParams(new LinearLayout.LayoutParams(
                    scalePixelsByScreen(35), scalePixelsByScreen(35)
            ));
            optionSquare.setScaleType(ImageView.ScaleType.FIT_CENTER);
            optionSquare.setImageDrawable(getContext().getResources().getDrawable(figure));
            optionSquare.setTag(figure);

            optionsRow.addView(optionSquare);
            optionsSquares.add(optionSquare);
        }
        optionsSquaresClickListenerInit(optionsSquares);
    }
    private void figureDistributorInit(){
        this.figureDistributor = new FigureDistributor(getContext(), 8, null);
        figureDistributor.distributeFigure(squares,8,8);
    }
    private void boardSquareClickListenerInit(){
        squareClick = new SquareClick(getContext());
        squareClick.bondAllSquares(squares);
    }
    private void optionsSquaresClickListenerInit(List<ImageView> options){
        optionsMenuClick = new OptionsMenuClick(getContext());
        optionsMenuClick.bondAllOptions(options);
    }

    public void settingsBtnInit(){
        View.OnClickListener listener = (view)->{
            SettingsDialog settingsDialog = SettingsDialog.getInstance();
            settingsDialog.show(getParentFragmentManager(), "settings_fragment");
        };
        binding.settingsBtn.setOnClickListener(listener);
    }


}
