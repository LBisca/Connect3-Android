package com.lucas.connect3;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 2 means unplayed
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    boolean gameIsActive = true;

    // 0 = Player1, 1 = Player2
    int activePlayer = 0;

    int[][] winningPositions = {{0, 1, 2},
                                {3, 4, 5},
                                {6, 7, 8},
                                {0, 3, 6},
                                {1, 4, 7},
                                {2, 5, 8},
                                {0, 4, 8},
                                {2, 4, 6}};


    public void dropin(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        Log.i("Tag", counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive == true) {

            counter.setTranslationY(-1000f);

            gameState[tappedCounter] = activePlayer;

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.x);

                counter.animate().translationYBy(1000f).setDuration(300);

                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.o);

                counter.animate().translationYBy(1000f).setDuration(300);

                activePlayer = 0;

            }

            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {

                    String winner = "O";
                    gameIsActive = false;

                    if (gameState[winningPosition[0]] == 0) {
                        winner = "X";
                    }

                    LinearLayout mylayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    TextView message = (TextView) findViewById(R.id.innerMessage);

                    message.setText(winner + " has won!");
                    mylayout.setVisibility(View.VISIBLE);

                } else {

                    boolean gameIsOver = true;

                    for(int counterState : gameState) {

                        if (counterState == 2)  gameIsOver = false;

                    }

                    if (gameIsOver) {

                        LinearLayout mylayout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        TextView message = (TextView) findViewById(R.id.innerMessage);

                        message.setText("Draw");
                        mylayout.setVisibility(View.VISIBLE);

                    }
                }
            }


        }
    }

    public void reset(View view) {

        GridLayout gridLayout = (GridLayout) findViewById(R.id.layout);
        LinearLayout mylayout = (LinearLayout) findViewById(R.id.playAgainLayout);

        mylayout.setVisibility(View.GONE);

        gameIsActive = true;

        activePlayer = 0;

        for (int i = 0; gameState.length > i; i++) {

            gameState[i] = 2;

        }

        for (int i = 0; i < gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
