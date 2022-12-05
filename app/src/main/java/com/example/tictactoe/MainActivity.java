package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static final int X = 0;
    private static final int O = 1;
    private static final int empty = 2;

    private static Button restButton;
    private static ImageView gameStatusImage;

    private static int counter = 0;
    private static boolean gameActive = true;

    private static int activePlayer = X;
    private static int[] gameState = {empty, empty, empty, empty, empty, empty, empty, empty, empty};

    private static int[][] winPositions = {{0, 1, 2},
                                           {3, 4, 5},
                                           {6, 7, 8},
                                           {0, 3, 6},
                                           {1, 4, 7},
                                           {2, 5, 8},
                                           {0, 4, 8},
                                           {2, 4, 6}};

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString()) - 1;

        if (gameState[tappedImage] == empty && gameActive) {
            counter++;

            if (counter == 9) {
                gameActive = false;
            }

            gameState[tappedImage] = activePlayer;

            if (activePlayer == X) {
                img.setImageResource(R.drawable.x);
                activePlayer = O;
                gameStatusImage.setImageResource(R.drawable.oplay);
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = X;
                gameStatusImage.setImageResource(R.drawable.xplay);
            }
        }

        int flag = 0;

        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                gameState[winPosition[1]] == gameState[winPosition[2]] && gameState[winPosition[0]] != 2) {
                flag = 1;

                int winner;

                gameActive = false;
                if (gameState[winPosition[0]] == 0) {
                    winner =  R.drawable.xwin;
                } else {
                    winner = R.drawable.owin;
                }
                gameStatusImage.setImageResource(winner);
                restButton.setVisibility(View.VISIBLE);
            }
        }

        if (counter == 9 && flag == 0) {
            gameStatusImage.setImageResource(R.drawable.nowin);
            restButton.setVisibility(View.VISIBLE);
        }
    }

    public void gameReset(View view) {
        gameActive = true;
        activePlayer = X;
        counter = 0;

        Arrays.fill(gameState, 2);

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameStatusImage = findViewById(R.id.gameStatusLabel_btn);
        restButton = findViewById(R.id.reset_btn);

        restButton.setVisibility((View.GONE));

        restButton.setOnClickListener(view -> {
            gameReset(view);
            restButton.setVisibility((View.GONE));
        });
    }
}