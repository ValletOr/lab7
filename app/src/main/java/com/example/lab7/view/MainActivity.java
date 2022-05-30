package com.example.lab7.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.lab7.R;

public class MainActivity extends AppCompatActivity {
    private EditText minNumTextField;
    private EditText maxNumTextField;
    private EditText attemptsTextField;
    private EditText numberTextField;
    private Button startButton;
    private ImageButton arrowButton;
    private LinearLayout customRulesLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        checkStartPossibility();
    }
    public void onStartClick(View v) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("rules", readRulesData());
        startActivity(intent);
    }

    private void init(){
        minNumTextField = findViewById(R.id.minNumTextField);
        maxNumTextField = findViewById(R.id.maxNumTextField);
        attemptsTextField = findViewById(R.id.attemptsTextField);
        numberTextField = findViewById(R.id.numberTextField);
        startButton = findViewById(R.id.startButton);
        arrowButton = findViewById(R.id.arrowButton);
        customRulesLayout = findViewById(R.id.customRulesLayout);
        minNumTextField.addTextChangedListener(new MyTextWatcher());
        maxNumTextField.addTextChangedListener(new MyTextWatcher());
        arrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customRulesLayout.getVisibility() == View.VISIBLE){
                    customRulesLayout.setVisibility(View.GONE);
                    arrowButton.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }else{
                    customRulesLayout.setVisibility(View.VISIBLE);
                    arrowButton.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                }
            }
        });
    }

    private String[] readRulesData(){
        String[] outMessage = new String[4];
        outMessage[0] = minNumTextField.getText().toString();
        outMessage[1] = maxNumTextField.getText().toString();
        outMessage[2] = attemptsTextField.getText().toString();
        outMessage[3] = numberTextField.getText().toString();
        return outMessage;
    }



    private void checkStartPossibility(){
        if((!minNumTextField.getText().toString().isEmpty())&&(!maxNumTextField.getText().toString().isEmpty())){
            if(Integer.parseInt(minNumTextField.getText().toString())<Integer.parseInt(maxNumTextField.getText().toString())){
                startButton.setEnabled(true);
            }else{
                startButton.setEnabled(false);
            }
        }else{
            startButton.setEnabled(false);
        }
    }
    public class MyTextWatcher implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            checkStartPossibility();
        }
    }
}