package com.example.lab7.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import android.view.MenuItem;

import com.example.lab7.logic.GameLogic;
import com.example.lab7.R;

public class GameActivity extends AppCompatActivity {
    private ActionBar actionBar;
    private EditText guessTextField;
    private TextView guessResult;
    private Button guessButton;
    private TextView attemptsCounter;
    private GameLogic game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        init();
        checkGuessPossibility();
        showAttempts();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onGuessClick(View v){
        switch (game.makeGuess(guessTextField.getText().toString())){
            case "win":
                guessResult.setText(R.string.guessResultWinLabel);
                guessButton.setEnabled(false);
                guessTextField.setEnabled(false);
                break;
            case "bigger":
                guessResult.setText(R.string.guessResultBigLabel);
                break;
            case "smaller":
                guessResult.setText(R.string.guessResultSmallLabel);
                break;
            case "lose":
                guessResult.setText(R.string.guessResultLoseLabel);
                guessButton.setEnabled(false);
                guessTextField.setEnabled(false);
                break;
        }
        showAttempts();
    }

    private void init(){
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        String[] rules = getIntent().getExtras().getStringArray("rules");
        game = new GameLogic(rules[0], rules[1], rules[2], rules[3]);
        guessButton = findViewById(R.id.GuessButton);
        guessTextField = findViewById(R.id.guessTextField);
        guessResult = findViewById(R.id.guessResultLabel);
        attemptsCounter = findViewById(R.id.attemptsCounterLabel);
        guessTextField.addTextChangedListener(new MyTextWatcher());
    }

    private void showAttempts(){
        if(game.getAttempts()>=0){
            attemptsCounter.setText(getString(R.string.attemptsCounterLabel) + game.getAttempts());
        }
    }

    private void checkGuessPossibility(){
        if(guessTextField.getText().toString().isEmpty()){
            guessButton.setEnabled(false);
        }else{
            guessButton.setEnabled(true);
        }
    }

    public class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            checkGuessPossibility();
        }
    }
}