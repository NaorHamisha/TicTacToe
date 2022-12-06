package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // region final variables

    private static final int X_PLAYER = 0;
    private static final int O_PLAYER = 1;
    private static final int EMPTY = 2;
    private static final int CELLS_COUNT = 9;
    private static final int[][] WIN_POSITIONS = {{0, 1, 2},
                                                  {3, 4, 5},
                                                  {6, 7, 8},
                                                  {0, 3, 6},
                                                  {1, 4, 7},
                                                  {2, 5, 8},
                                                  {0, 4, 8},
                                                  {2, 4, 6}};

    // endregion

    // region Fields

    private int counter;
    private int[] gameState;
    private int activePlayer;
    private Button restButton;
    private boolean gameActive;
    private ImageView gameStatusImage;

    // endregion

    // region Public Methods

    public void cellPressed(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString()) - 1;

        if (!gameActive || gameState[tappedImage] != EMPTY) {
            return;
        }

        counter++;
        gameState[tappedImage] = activePlayer;

        if (activePlayer == X_PLAYER) {
            img.setImageResource(R.drawable.x);
            activePlayer = O_PLAYER;
            gameStatusImage.setImageResource(R.drawable.oplay);
        } else {
            img.setImageResource(R.drawable.o);
            activePlayer = X_PLAYER;
            gameStatusImage.setImageResource(R.drawable.xplay);
        }

        for (int[] winPosition : WIN_POSITIONS) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                gameState[winPosition[1]] == gameState[winPosition[2]] && gameState[winPosition[0]] != EMPTY) {
                int winner;
                gameActive = false;
                if (gameState[winPosition[0]] == X_PLAYER) {
                    winner =  R.drawable.xwin;
                } else {
                    winner = R.drawable.owin;
                }
                gameStatusImage.setImageResource(winner);
                restButton.setVisibility(View.VISIBLE);

                return;
            }
        }

        if (counter == CELLS_COUNT) {
            gameActive = false;
            gameStatusImage.setImageResource(R.drawable.nowin);
            restButton.setVisibility(View.VISIBLE);
        }
    }

    // endregion

    //  region Private Methods

    private void gameReset(View view) {
        gameActive = true;
        activePlayer = X_PLAYER;
        counter = 0;

        Arrays.fill(gameState, EMPTY);

        ((ImageView) findViewById(R.id.imageView)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.imageView9)).setImageResource(R.drawable.empty);
        ((ImageView) findViewById(R.id.imageView10)).setImageResource(R.drawable.empty);

        gameStatusImage.setImageResource(R.drawable.xplay);
    }

    // endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counter = 0;
        gameActive = true;
        activePlayer = X_PLAYER;
        gameState = new int[CELLS_COUNT];
        Arrays.fill(gameState, EMPTY);
        gameStatusImage = findViewById(R.id.gameStatusLabel_btn);
        restButton = findViewById(R.id.reset_btn);

        restButton.setVisibility((View.GONE));
        restButton.setOnClickListener(view -> {
            gameReset(view);
            restButton.setVisibility((View.GONE));
        });
    }
}